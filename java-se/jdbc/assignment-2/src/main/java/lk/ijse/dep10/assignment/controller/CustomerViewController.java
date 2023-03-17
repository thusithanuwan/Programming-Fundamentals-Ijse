package lk.ijse.dep10.assignment.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dep10.assignment.db.DBConnection;
import lk.ijse.dep10.assignment.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerViewController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnNewCustomer;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnSave;

    @FXML
    private ListView<String> lstContact;

    @FXML
    private TableView<Customer> tblCustomers;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtSearch;

    public void initialize(){
        tblCustomers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));

        loadCustomer();

    }
    private void loadCustomer(){

    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        ObservableList<String> contactList = lstContact.getItems();
        contactList.add(txtContact.getText());

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnNewCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void btnRemoveOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            String sql = "insert into Customer values (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,Integer.parseInt(txtId.getText()));
            preparedStatement.setString(2,txtCustomerName.getText());
            preparedStatement.setString(3,txtAddress.getText());

            preparedStatement.executeUpdate();

            ObservableList<String> contactList = lstContact.getItems();
            for (String contact : contactList) {
                sql = "insert into Contact values (?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1,Integer.parseInt(txtId.getText()));
                preparedStatement.setString(2, contact);

                preparedStatement.executeUpdate();

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
