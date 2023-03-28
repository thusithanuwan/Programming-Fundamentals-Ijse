package lk.ijse.dep10.report;

import com.github.javafaker.Faker;
import lk.ijse.dep10.report.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GenerateDummyData {
    public static void main(String[] args) {
        Faker faker = new Faker();

        Connection connection = DBConnection.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into Customer (name,address) values (?,?)");

            for (int i = 0; i < 200; i++) {
                preparedStatement.setString(1,faker.name().fullName());
                preparedStatement.setString(2,faker.address().fullAddress());
                preparedStatement.executeUpdate();

            }
            System.out.println("200 Customer has been added");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
