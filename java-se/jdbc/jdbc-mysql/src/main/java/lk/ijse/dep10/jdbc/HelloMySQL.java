package lk.ijse.dep10.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HelloMySQL {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jsbc:mysql://127.0.0.1:3306/dep10", "root", "mysql");
        } catch (SQLException e) {
            System.out.println("Something went wrong!");
            e.printStackTrace();
        }
    }
}
