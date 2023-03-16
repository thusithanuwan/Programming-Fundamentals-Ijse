package lk.ijse.dep10.assignment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.dep10.assignment.db.DBConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        generateTable();
        boolean adminExist = adminExists();

        String url = adminExist ? "/view/LoginView.fxml" : "/view/SignUpView.fxml";

        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource(url))));
        primaryStage.setResizable(false);


        primaryStage.setTitle(adminExist ? "Login" : "Creat Admin Account");
        primaryStage.show();
        primaryStage.centerOnScreen();





    }
    private void generateTable(){
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("show tables");

            if (!resultSet.next()) {
                InputStream is = getClass().getResourceAsStream("/schema.sql");
                BufferedReader bf = new BufferedReader(new InputStreamReader(is));

                StringBuilder dbScript = new StringBuilder();


                String line =null;
                while((line = bf.readLine()) != null){

                    dbScript.append(line + "\n");
                }
                bf.read();
                System.out.println(dbScript);
                statement.execute(dbScript.toString());

            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private boolean adminExists(){
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery("select * from User where role = 'ADMIN'").next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
