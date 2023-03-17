package lk.ijse.dep10.assignment.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.dep10.assignment.db.DBConnection;
import lk.ijse.dep10.assignment.model.User;
import lk.ijse.dep10.assignment.util.PasswordEncoder;

import java.net.URL;
import java.sql.*;

public class LoginViewController {

    public Button btnLogin;
    public PasswordField txtPassword;
    public TextField txtUsername;
    
    public void btnLoginOnAction(ActionEvent event) {
        String userName = txtUsername.getText();
        String password = PasswordEncoder.encode(txtPassword.getText());

        Connection connection = DBConnection.getInstance().getConnection();
        try {
            String sql = "select * from User where username =?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userName);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.next()){

            }else{
                if (PasswordEncoder.matches(password, resultSet.getString("password"))) {
                    txtPassword.getStyleClass().add("invalid");
                    txtPassword.getStyleClass().add("invalid");
                    txtPassword.requestFocus();
                    txtUsername.requestFocus();
                }

                String fullName = resultSet.getString("full_name");
                User.Role role = User.Role.valueOf(resultSet.getString("role"));
                User principal = new User(fullName,userName,password,role);
                System.getProperties().put("principal",principal);
                URL loginView = getClass().getResource("/view/MainView.fxml");
                var loginScene = new Scene(FXMLLoader.load(loginView));
                Stage stage = (Stage) btnLogin.getScene().getWindow();
                stage.setScene(loginScene);
                stage.show();
                stage.centerOnScreen();

            }





        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
