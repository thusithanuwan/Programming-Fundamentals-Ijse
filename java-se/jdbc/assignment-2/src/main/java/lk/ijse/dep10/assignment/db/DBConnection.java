package lk.ijse.dep10.assignment.db;

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
            File file = new File("application.properties");
            FileReader fileReader = new FileReader(file);
            Properties properties = new Properties();
            properties.load(fileReader);
            fileReader.close();

            String host = properties.getProperty("mysql.host", "localhost");
            String port = properties.getProperty("mysql.port", "3306");
            String database = properties.getProperty("mysql.database", "dep10_jdbc2");
            String username = properties.getProperty("mysql.username", "root");
            String password = properties.getProperty("mysql.password", "mysql");

            String url = "jdbc:mysql://" + host + ":" + port + "/" + database +
                    "?createDatabaseIfNotExist=true&allowMultiQueries=true";

            connection = DriverManager.getConnection(url, username, password);


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
