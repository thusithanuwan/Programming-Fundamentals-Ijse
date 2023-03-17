package lk.ijse.dep10.db;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep10.db.db.DBConnection;
import lk.ijse.dep10.db.model.util.Role;

import java.io.IOException;
import java.sql.*;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Connection connection = DBConnection.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM AdminUser");

            while (resultSet.next()) {
                Role role = Role.valueOf(resultSet.getString("role"));
                if (role == Role.ADMIN) {
                    FXMLLoader fxmlLoader2 = new FXMLLoader(this.getClass().getResource("/view/LoginWindow.fxml"));
                    AnchorPane root2 = fxmlLoader2.load();
                    primaryStage.setScene(new Scene(root2));
                    primaryStage.sizeToScene();
                    primaryStage.show();
                    primaryStage.centerOnScreen();
                    return;
                }

            }
            FXMLLoader fxmlLoader1 = new FXMLLoader(this.getClass().getResource("/view/CreateAdminWindow.fxml"));
            AnchorPane root1 = fxmlLoader1.load();
            primaryStage.setScene(new Scene(root1));
            primaryStage.sizeToScene();
            primaryStage.show();
            primaryStage.centerOnScreen();


        } catch (SQLException e) {
           e.printStackTrace();
        }


    }
}
