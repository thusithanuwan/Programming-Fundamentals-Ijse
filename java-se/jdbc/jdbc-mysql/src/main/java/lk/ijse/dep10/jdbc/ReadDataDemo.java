package lk.ijse.dep10.jdbc;

import java.sql.*;

public class ReadDataDemo {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://dep10.lk/dep10", "root", "mysql");

            String sql = "SELECT * FROM Student";
            Statement statement = connection.createStatement();
            ResultSet rst = statement.executeQuery(sql);

//            rst.next(); // Move head to first node.
//
//            int id = rst.getInt(1); // Access with column  index
//            System.out.println(id); // Acces with column name
//            id = rst.getInt("id");
//            System.out.println(id);
//            String firstName = rst.getString(2);
//            System.out.println(firstName);
//            firstName = rst.getString("first_name");
//            System.out.println(firstName);
//
//            rst.next();
//            rst.previous();
//            id = rst.getInt(1);
//            System.out.println(id);

            while (rst.next()){
                int id = rst.getInt("id");
                String firstName = rst.getString("first_name");
                String lastName = rst.getString("last_name");
                String address = rst.getString("address");
                String gender = rst.getString("gender");
                Date dob = rst.getDate("dob");
                System.out.printf("|%5s|%10s|%15s|%15s|%15s|%15s| \n", id,firstName,lastName,address,gender,dob);
            }

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
