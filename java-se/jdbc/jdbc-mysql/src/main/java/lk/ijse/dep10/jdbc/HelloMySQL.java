package lk.ijse.dep10.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloMySQL {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://dep10.lk/dep10", "root", "mysql");
            System.out.println(connection);

            Statement stm = connection.createStatement();  // Wrap connection with Regular Statement
            String insertSQL = "INSERT INTO Student VALUES (5,'Ruwan','Kasun','Jaffna','Male', '1967-09-14')";
            String updateSQL = "UPDATE Student SET address = 'Matara' WHERE id = 5";
            String deleteSQL = "DELETE FROM Student WHERE id = 5";

            int affectedRows = stm.executeUpdate(deleteSQL);
            System.out.println(affectedRows);
            stm.close();


        } catch (SQLException e) {
            System.out.println("Something went wrong!");
            e.printStackTrace();
        }
    }
}
