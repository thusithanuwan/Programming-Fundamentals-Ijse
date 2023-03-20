package lk.ijse.dep10.app.db;

import javafx.scene.control.Alert;

import javax.annotation.processing.Filer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection {
    private static DBConnection instance;
    private final Connection connection;


    private DBConnection() {

        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dep10_students","root","mysql");


        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to connect database");
            System.exit(1);
            throw new RuntimeException();

        }


    }

    public static DBConnection getInstance() {
        return (instance == null) ? instance = new DBConnection() : instance;
    }

    public Connection getConnection() {
        return connection;
    }
}

