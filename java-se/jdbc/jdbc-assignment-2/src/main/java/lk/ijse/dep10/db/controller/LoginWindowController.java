package lk.ijse.dep10.db.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.dep10.db.db.DBConnection;
import lk.ijse.dep10.db.model.util.Role;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginWindowController {

    @FXML
    private Button btnLogIn;

    @FXML
    private PasswordField psdPassword;

    @FXML
    private TextField txtUserName;



    @FXML
    void btnLogInOnAction(ActionEvent event) {
        if (!isValid()) return;
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM AdminUser WHERE user_name = '%s' AND password = '%s'";
            sql = String.format(sql, txtUserName.getText().trim(), psdPassword.getText().trim());

            ResultSet resultSet = statement.executeQuery(sql);

            if (!resultSet.next()) {   // In here pointer is pointer to the First Node
                txtUserName.requestFocus();
                return;
            }


            Stage stage = (Stage) btnLogIn.getScene().getWindow();


            if (Role.valueOf(resultSet.getString("role")) == Role.ADMIN) {
                FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/AdminWindow.fxml"));
                AnchorPane root = fxmlLoader.load();

                AdminWindowController controller = fxmlLoader.getController();
                SimpleStringProperty simpleStringProperty = new SimpleStringProperty(txtUserName.getText());
                controller.initData(simpleStringProperty);


                stage.setScene(new Scene(root));
                stage.show();
                stage.centerOnScreen();
            } else {
                FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/UserWindow.fxml"));
                AnchorPane root = fxmlLoader.load();

                UserWindowController controller = fxmlLoader.getController();
                SimpleStringProperty simpleStringProperty = new SimpleStringProperty(txtUserName.getText());
                controller.initData(simpleStringProperty);


                stage.setScene(new Scene(root));
                stage.show();
                stage.centerOnScreen();

            }


        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }


    }

    private boolean isValid() {
        boolean isValid = true;

        if (psdPassword.getText().isBlank()) {
            isValid = false;
            psdPassword.requestFocus();
        }
        if (txtUserName.getText().isBlank()) {
            isValid = false;
            txtUserName.requestFocus();
        }
        return isValid;
    }

}
