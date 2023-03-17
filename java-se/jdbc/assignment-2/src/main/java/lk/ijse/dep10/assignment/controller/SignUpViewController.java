package lk.ijse.dep10.assignment.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.dep10.assignment.db.DBConnection;
import lk.ijse.dep10.assignment.util.PasswordEncoder;

import javax.management.relation.Role;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

public class SignUpViewController {

    public Button btnAdminAccount;
    public PasswordField txtConfirmPassword;
    public TextField txtFullName;
    public PasswordField txtPassword;
    public TextField txtUsername;

    public void btnAdminAccountOnAction(ActionEvent event) {
        if (!isDataValidation()) {
            return;
        }

        Node[] nodes = {txtUsername, txtConfirmPassword, txtFullName, txtConfirmPassword};
        for (Node node : nodes) {
            node.getStyleClass().remove("invalid");
        }

        Connection connection = DBConnection.getInstance().getConnection();
        try {
            String sql = "insert into User values (?,?,?,'ADMIN')";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,txtUsername.getText());
            preparedStatement.setString(2,txtFullName.getText());
            preparedStatement.setString(3, PasswordEncoder.encode(txtPassword.getText()));

            preparedStatement.executeUpdate();

            URL loginView = getClass().getResource("/view/LoginView.fxml");
            var loginScene = new Scene(FXMLLoader.load(loginView));
            Stage stage = (Stage) btnAdminAccount.getScene().getWindow();
            stage.setScene(loginScene);
            stage.show();
            stage.centerOnScreen();


        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Oops, Something Went Wrong!");
        }

    }

    private boolean isDataValidation() {

        boolean dataValid = true;
        String fullName = txtFullName.getText();
        String userName = txtUsername.getText();
        String password = txtPassword.getText();
        String confirmPassword = txtPassword.getText();

        Pattern regEx4UpperCase = Pattern.compile("[A-Z]");
        Pattern regEx4LowerCase = Pattern.compile("[a-z]");
        Pattern regEx4Digit = Pattern.compile("[0-9]");
        Pattern regEx4Symbols = Pattern.compile("[~!@#$%^&*()_+]");

        if (password.isEmpty() || !confirmPassword.equals(password)) {
            txtConfirmPassword.requestFocus();
            txtConfirmPassword.selectAll();
            txtConfirmPassword.getStyleClass().add("invalid");
            dataValid = false;
        }

        if (!(regEx4UpperCase.matcher(password).find() &&
                regEx4LowerCase.matcher(password).find() &&
                regEx4Digit.matcher(password).find() &&
                regEx4Symbols.matcher(password).find() &&
                password.length() >= 5)) {
            txtPassword.requestFocus();
            txtPassword.selectAll();
            txtPassword.getStyleClass().add("invalid");
            dataValid = false;
        }

        if (!userName.matches("[A-z0-9]{3,}")) {
            txtUsername.requestFocus();
            txtUsername.selectAll();
            txtUsername.getStyleClass().add("invalid");
            dataValid = false;
        }
        if (!fullName.matches("[A-z ]+")) {
            txtFullName.requestFocus();
            txtFullName.selectAll();
            txtFullName.getStyleClass().add("invalid");
            dataValid = false;
        }

        System.out.println(dataValid);
        return dataValid;


    }

}
