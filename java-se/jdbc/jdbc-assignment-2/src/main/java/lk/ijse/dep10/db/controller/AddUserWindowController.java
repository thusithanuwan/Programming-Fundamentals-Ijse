package lk.ijse.dep10.db.controller;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep10.db.db.DBConnection;
import lk.ijse.dep10.db.model.User;
import lk.ijse.dep10.db.model.util.Role;

import java.io.IOException;
import java.sql.*;

public class AddUserWindowController {

    public AnchorPane root;
    public Button btnLogOut;
    @FXML
    private Button btnDelete;

    @FXML
    private Button btnNewUser;

    @FXML
    private Button btnSave;

    @FXML
    private PasswordField psdPassword;

    @FXML
    private TableView<User> tblUsers;

    @FXML
    private TextField txtFullName;

    @FXML
    private TextField txtUserName;

    public void initialize() {

        tblUsers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("fullName"));
        tblUsers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("userName"));
        tblUsers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("password"));
        tblUsers.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("role"));

        Platform.runLater(() -> {
            root.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_ANY), () -> btnNewUser.fire());
            root.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_ANY), () -> btnSave.fire());
            root.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.F1), () -> tblUsers.requestFocus());
        });

        tblUsers.getSelectionModel().selectedItemProperty().addListener((value, previous, current) -> {
            btnDelete.setDisable(current == null);

            if (current == null) {
                return;
            }
            txtFullName.setText(current.getFullName());
            txtUserName.setText(current.getUserName());
            psdPassword.setText(current.getPassword());

        });

        loadUsers();


    }

    private void loadUsers() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM AdminUser");

            while (resultSet.next()) {
                String fullName = resultSet.getString("full_name");
                String userName = resultSet.getString("user_name");
                String password = resultSet.getString("password");
                Role role = Role.valueOf(resultSet.getString("role")); // should equal Database enum and App enum.

                tblUsers.getItems().add(new User(fullName, userName, password, role));
            }

            Platform.runLater(
                    btnNewUser::fire
            );


        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load student.").showAndWait();
            Platform.exit();
        }


    }



    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            System.out.println(connection);
            Statement statement = connection.createStatement();

            User selectedUser = tblUsers.getSelectionModel().getSelectedItem();
            if (selectedUser.getRole() == Role.ADMIN) {
               new Alert(Alert.AlertType.ERROR,"Can not remove Admin!").showAndWait();
                return;
            }


            String sql = "DELETE FROM AdminUser WHERE user_name = '%s'";
            sql = String.format(sql, tblUsers.getSelectionModel().getSelectedItem().getUserName());
            statement.executeUpdate(sql);

            tblUsers.getItems().remove(tblUsers.getSelectionModel().getSelectedItem());

            if (tblUsers.getItems().isEmpty()) {
                btnNewUser.fire();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to delete the student.").show();
        }


    }

    @FXML
    void btnNewUserOnAction(ActionEvent event) {
        txtFullName.clear();
        txtUserName.clear();
        psdPassword.clear();

        txtFullName.requestFocus();

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (!isValid()) {
            return;
        }
        User user = new User(txtFullName.getText(), txtUserName.getText(), psdPassword.getText().trim(), Role.USER);

        User selectedUser = tblUsers.getSelectionModel().getSelectedItem();

        Connection connection = DBConnection.getInstance().getConnection();
        try {

            Statement statement = connection.createStatement();

            if (selectedUser == null) {
                String sql = "INSERT INTO AdminUser VALUES ('%s','%s','%s','%s')";
                sql = String.format(sql, user.getFullName(), user.getUserName(), user.getPassword(), user.getRole());
                statement.executeUpdate(sql);

                tblUsers.getItems().add(user);

            } else {
                String sql = "UPDATE AdminUser SET full_name='%s', user_name='%s', password='%s'," + " role='%s' WHERE user_name='%s'";
                sql = String.format(sql, user.getFullName(), user.getUserName(), user.getPassword(), user.getRole(),selectedUser.getUserName());
                statement.executeUpdate(sql);

                ObservableList<User> userList = tblUsers.getItems();
                int selectedUserIndex = userList.indexOf(selectedUser);
                userList.set(selectedUserIndex, user);
                tblUsers.refresh();
                btnNewUser.fire();

            }


        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to save the User, try again.").showAndWait();
            btnNewUser.fire();

        }


    }

    private boolean isValid() {
        boolean isDataValid = true;

        String fullName = txtFullName.getText();
        String userName = txtUserName.getText();
        String password = psdPassword.getText();

        if (!fullName.matches("[A-z ]+")) { // A-z and spaces
            isDataValid = false;
            txtFullName.requestFocus();
            txtFullName.selectAll();
        }
        if (!userName.matches("[A-z ]+")) { // A-z and spaces
            isDataValid = false;
            txtFullName.requestFocus();
            txtFullName.selectAll();
        }
        if (!password.matches("[A-z 0-9]+")) { // A-z and spaces
            isDataValid = false;
            txtFullName.requestFocus();
            txtFullName.selectAll();
        }
        return isDataValid;


    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/LoginWindow.fxml"));
        AnchorPane root = fxmlLoader.load();
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        stage.setScene(new Scene(root));

        stage.show();
        stage.centerOnScreen();
    }
}
