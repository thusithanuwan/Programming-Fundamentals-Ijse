package lk.ijse.dep10.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import lk.ijse.dep10.client.db.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientViewController {

    public Button btnRepeatableRead1;
    public Button btnRepeatableRead2;
    @FXML
    private Button btnReadRecords1;
    @FXML
    private Button btnReadRecords2;

    @FXML
    private Button btnReadValues;

    @FXML
    private ComboBox<String> cmbLevels;

    public void initialize() {
        btnReadRecords1.setDisable(false);
        btnReadRecords2.setDisable(true);


        cmbLevels.getItems().addAll("READ UNCOMMITTED", "READ COMMITTED", "REPEATABLE READ", "SERIALIZABLE");
        cmbLevels.getSelectionModel().select(1);

        cmbLevels.getSelectionModel().selectedItemProperty().addListener((value, previous, current) -> {
            if (current == null) {
                return;
            }
            Connection connection = DBConnection.getInstance().getConnection();

            try {
                switch (current) {
                    case "READ UNCOMMITTED":
                        connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
                        break;
                    case "READ COMMITTED":
                        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                        break;
                    case "REPEATABLE READ":
                        connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
                        break;
                    case "SERIALIZABLE":
                        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });


    }

    @FXML
    void btnReadRecordsOnAction1(ActionEvent event) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from Customer");
        System.out.println("Reading for the 1st Time");
        System.out.println("______________________________________________________________________");

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            System.out.printf("ID :%s ,Name :%s , Address :%s \n", id, name, address);

        }
        btnReadRecords1.setDisable(true);
        btnReadRecords2.setDisable(false);


    }

    @FXML
    void btnReadRecordsOnAction2(ActionEvent event) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from Customer");
        System.out.println("Reading for the 2nd Time");
        System.out.println("______________________________________________________________________");

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            System.out.printf("ID :%s ,Name :%s , Address :%s \n", id, name, address);

        }
        connection.commit();
        connection.setAutoCommit(true);
        btnReadRecords1.setDisable(false);
        btnReadRecords2.setDisable(true);
    }

    @FXML
    void btnReadValuesOnAction(ActionEvent event) throws SQLException {


        Connection connection = DBConnection.getInstance().getConnection();


        if (!connection.getAutoCommit()) {
            connection.rollback();
            connection.setAutoCommit(true);
            btnReadRecords1.setDisable(false);
            btnReadRecords2.setDisable(true);
        }


        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from Customer where id =1");
        resultSet.next();
        String name = resultSet.getString("name");
        String address = resultSet.getString("address");
        System.out.printf("Name :%s , Address :%s \n", name, address);
        btnRepeatableRead1.setDisable(false);
        btnRepeatableRead2.setDisable(true);
        System.out.println("_____________________________________________________________________________________");


    }

    public void btnRepeatableRead1OnAction(ActionEvent actionEvent) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from Customer where id =1");
        resultSet.next();
        String name = resultSet.getString("name");
        String address = resultSet.getString("address");
        System.out.printf("Repeatable Read1:Name :%s , Address :%s \n", name, address);
        btnRepeatableRead1.setDisable(true);
        btnRepeatableRead2.setDisable(false);

        System.out.println("_____________________________________________________________________________________");


    }

    public void btnRepeatableRead2OnAction(ActionEvent actionEvent) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from Customer where id =1");
        resultSet.next();
        String name = resultSet.getString("name");
        String address = resultSet.getString("address");
        System.out.printf("Repeatable Read2:Name :%s , Address :%s \n", name, address);
        btnRepeatableRead1.setDisable(false);
        btnRepeatableRead2.setDisable(true);

        System.out.println("_____________________________________________________________________________________");
    }
}
