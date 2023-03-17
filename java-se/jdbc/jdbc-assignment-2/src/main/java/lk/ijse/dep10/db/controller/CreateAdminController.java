package lk.ijse.dep10.db.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.dep10.db.db.DBConnection;
import lk.ijse.dep10.db.model.User;
import lk.ijse.dep10.db.model.util.Role;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateAdminController {

    @FXML
    public Button btnCreateAdmin;

    @FXML
    public PasswordField psdConfirmPassword;

    @FXML
    public PasswordField psdPassword;

    @FXML
    public TextField txtFullName;

    @FXML
    public TextField txtUserName;

    @FXML
    void btnCreateAdminOnAction(ActionEvent event) {
        if (!isValid()) return;
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO AdminUser VALUES ('%s','%s','%s','%s')";
            sql = String.format(sql,txtFullName.getText(),txtUserName.getText(),psdPassword.getText(), Role.ADMIN);
            statement.executeUpdate(sql); // add to the Database
            new Alert(Alert.AlertType.INFORMATION, "Successfully added an Admin!").show();

            // Load Log In Window

            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/LoginWindow.fxml"));
            AnchorPane root = fxmlLoader.load();
            Stage stage = (Stage) btnCreateAdmin.getScene().getWindow();
            stage.setScene(new Scene(root));

            stage.show();
            stage.centerOnScreen();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
        }


    }

    private boolean isValid() {
        boolean isValid = true;

        if (!(psdPassword.getText().trim().equals(psdConfirmPassword.getText()) && !psdPassword.getText().isBlank())) {
            isValid = false;
            psdPassword.requestFocus();
            psdPassword.selectAll();
        }
        if (txtUserName.getText().isBlank()) {
            isValid = false;
            txtUserName.requestFocus();
        }
        if (txtFullName.getText().isBlank()) {
            isValid = false;
            txtFullName.requestFocus();
        }
        return isValid;


    }

}
