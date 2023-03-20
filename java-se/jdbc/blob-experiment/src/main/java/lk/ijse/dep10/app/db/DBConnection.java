package lk.ijse.dep10.app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;
    private final Connection connection;

    private DBConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dep10_Items","root","mysql");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DBConnection getInstance() {
        return (instance == null) ? instance = new DBConnection()   : instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
