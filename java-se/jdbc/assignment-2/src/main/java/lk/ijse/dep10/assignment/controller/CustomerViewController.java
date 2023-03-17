package lk.ijse.dep10.assignment.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dep10.assignment.db.DBConnection;
import lk.ijse.dep10.assignment.model.Customer;

import java.sql.*;
import java.util.ArrayList;

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

    public void initialize() {
        tblCustomers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));

        loadCustomer();

//        txtContact.setOnAction(action -> {
//            btnAddOnAction(action);
//        });

        txtContact.setOnAction(this::btnAddOnAction); // Lambda expression -> "Add" Enter key to add contacts

        btnRemove.setDisable(true);
        lstContact.getSelectionModel().selectedItemProperty().addListener((value,previous,current) -> {
            btnRemove.setDisable(current == null);
        });

        tblCustomers.getSelectionModel().selectedItemProperty().addListener((value,previous,current) -> {
            btnDelete.setDisable(current == null);
        });

    }

    private void loadCustomer() {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            String url = "select * from Customer";
            ResultSet resultSet = statement.executeQuery(url);

            PreparedStatement statement1 = connection.prepareStatement("select * from Contact where customer_id =?");  // To improve performance

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                ArrayList<String> contactList = new ArrayList<>();

                statement1.setInt(1, id);


                ResultSet resultSet1 = statement1.executeQuery();
                while (resultSet1.next()) {
                    String contact = resultSet1.getString("contact");
                    contactList.add(contact);
                }

                Customer customer = new Customer(id, name, address, contactList);
                tblCustomers.getItems().add(customer);


            }


        } catch (SQLException e) {
           e.printStackTrace();
           new Alert(Alert.AlertType.ERROR,"Failed to load customer!");
        }

    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

        if (!txtContact.getText().matches("\\d{3}-\\d{7}") || lstContact.getItems().contains(txtContact.getText())) {

            txtContact.getStyleClass().add("invalid");
            txtContact.selectAll();
            txtContact.requestFocus();

        }else {
            txtContact.getStyleClass().remove("invalid");
            ObservableList<String> contactList = lstContact.getItems();
            contactList.add(txtContact.getText().strip());
            txtContact.requestFocus();
            txtContact.clear();
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnNewCustomerOnAction(ActionEvent event) {
        //Auto generate ID
        ObservableList<Customer> customerList = tblCustomers.getItems();
        var newId = customerList.isEmpty() ? 1 :(customerList.get(customerList.size()-1).getId() + 1);
        System.out.println(newId);
        txtId.setText(newId + "");
        System.out.println(txtId.getText().isEmpty());

        txtCustomerName.clear();
        txtAddress.clear();
        lstContact.getItems().clear();
        lstContact.getSelectionModel().clearSelection();
        tblCustomers.getSelectionModel().clearSelection();
        txtCustomerName.requestFocus();

    }

    @FXML
    void btnRemoveOnAction(ActionEvent event) {
        lstContact.getItems().remove(lstContact.getSelectionModel().getSelectedItem());

    }

    @FXML
    void btnSaveOnAction(ActionEvent event)  {


        if (!isDataValid()) {
            return;
        }


        Connection connection = DBConnection.getInstance().getConnection();


        try {
            if (!lstContact.getItems().isEmpty()){  // Check Contact is already in the database
                PreparedStatement preparedStatement = connection.prepareStatement("select * from Contact where contact=?");
                for (String contact : lstContact.getItems()) {
                    preparedStatement.setString(1,contact);
                    if (preparedStatement.executeQuery().next()){
                        new Alert(Alert.AlertType.ERROR,contact + "already exist").show();
                        lstContact.getStyleClass().add("invalid");
                        return;
                    }
                }


            }

            connection.setAutoCommit(false);

            String sql = "insert into Customer values (?,?,?)";
            PreparedStatement preparedStatementCustomer = connection.prepareStatement(sql);
            preparedStatementCustomer.setInt(1, Integer.parseInt(txtId.getText()));
            preparedStatementCustomer.setString(2, txtCustomerName.getText());
            preparedStatementCustomer.setString(3, txtAddress.getText());

            preparedStatementCustomer.executeUpdate();


            if (!lstContact.getItems().isEmpty()){

                ObservableList<String> contactList = lstContact.getItems();
                for (String contact : contactList) {
                    sql = "insert into Contact values (?,?)";
                    PreparedStatement preparedStatementContact = connection.prepareStatement(sql);
                    preparedStatementContact.setInt(1, Integer.parseInt(txtId.getText()));
                    preparedStatementContact.setString(2, contact);

                    preparedStatementContact.executeUpdate();

                }
            }

            connection.commit();

            ObservableList<String > contactList = lstContact.getItems();

            Customer customer = new Customer(Integer.parseInt(txtId.getText()), txtCustomerName.getText(), txtAddress.getText(),
                    new ArrayList<>(contactList));

            tblCustomers.getItems().add(customer);
            tblCustomers.refresh();
            btnNewCustomer.fire();

        } catch (Throwable e) {
            try{
                DBConnection.getInstance().getConnection().rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Failed to save the new customer!").show();

            throw new RuntimeException(e);
        }finally {
            try{
                DBConnection.getInstance().getConnection().setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
    private boolean isDataValid(){

        boolean dataValid = true;

        String name = txtCustomerName.getText();
        String address = txtAddress.getText();

        if (!(address.length() >= 3)) {
            txtAddress.requestFocus();
            txtAddress .selectAll();
            txtAddress.getStyleClass().add("invalid");
            dataValid = false;
        }
        if (!name.matches("[A-z ]+")) {
            txtCustomerName.requestFocus();
            txtCustomerName .selectAll();
            txtCustomerName.getStyleClass().add("invalid");
            dataValid = false;
        }
        return dataValid;


    }

}
