package lk.ijse.dep10.clientB.controller;

import com.github.javafaker.Faker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import lk.ijse.dep10.clientB.db.DBConnection;

import java.sql.*;

public class ClientBViewController {

    @FXML
    private Button btnAddNewRecord;

    @FXML
    private Button btnConfirmTransaction;

    @FXML
    private Button btnRollBackTransaction;

    @FXML
    private Button btnStartTransaction;

    @FXML
    private Button btnUpdateValue;

    private Connection connection;

    public void initialize() {
        btnUpdateValue.setDisable(true);
        btnAddNewRecord.setDisable(true);
        btnConfirmTransaction.setDisable(true);
        btnRollBackTransaction.setDisable(false);
        connection = DBConnection.getInstance().getConnection();
    }

    @FXML
    void btnAddNewRecordOnAction(ActionEvent event) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into Customer (name,address) values(?,?)");
        Faker faker = new Faker();  // came from new dependency
        preparedStatement.setString(1,faker.name().fullName());
        preparedStatement.setString(2,faker.address().cityName());

        preparedStatement.executeUpdate();
        System.out.println("New Customer record has been inserted");
    }

    @FXML
    void btnConfirmTransactionOnAction(ActionEvent event) throws SQLException {
        connection.commit();
        connection.setAutoCommit(true);
        btnUpdateValue.setDisable(true);
        btnAddNewRecord.setDisable(true);
        btnConfirmTransaction.setDisable(true);
        btnRollBackTransaction.setDisable(true);
        btnStartTransaction.setDisable(false);
        System.out.println("Transaction has been committed");

    }

    @FXML
    void btnRollBackTransactionOnAction(ActionEvent event) throws SQLException {
        connection.rollback();
        connection.setAutoCommit(true);
        btnUpdateValue.setDisable(true);
        btnAddNewRecord.setDisable(true);
        btnConfirmTransaction.setDisable(true);
        btnRollBackTransaction.setDisable(true);
        btnStartTransaction.setDisable(false);
        System.out.println("Transaction has been rollback");


    }

    @FXML
    void btnStartTransactionOnAction(ActionEvent event) throws SQLException {
        connection.setAutoCommit(false);
        btnUpdateValue.setDisable(false);
        btnAddNewRecord.setDisable(false);
        btnConfirmTransaction.setDisable(false);
        btnRollBackTransaction.setDisable(false);
        btnStartTransaction.setDisable(true);

    }

    @FXML
    void btnUpdateValueOnAction(ActionEvent event) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update Customer set name=?,address=? where id=1");
        Faker faker = new Faker();
        String name = faker.name().fullName();
        String address = faker.address().cityName();
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,address);
        preparedStatement.executeUpdate();
        System.out.printf("Customer id=1 has been updated with name : %s  , address : %s \n",name,address);

    }

}
