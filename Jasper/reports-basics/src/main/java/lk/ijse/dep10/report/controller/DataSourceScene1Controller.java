package lk.ijse.dep10.report.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dep10.report.db.DBConnection;
import lk.ijse.dep10.report.model.Customer;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DataSourceScene1Controller {

    @FXML
    private Button btnBeanArrayDataSource;

    @FXML
    private Button btnBeanCollectionDataSource;

    @FXML
    private TableView<Customer> tblCustomer;

    @FXML
    private TextArea txtSearch;

    private JasperReport jasperReport;


    public void initialize(){

        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));


        loadCustomer();
        txtSearch.textProperty().addListener((value,previous,current) -> {
            loadCustomer();
        });

        initializeJasperReport();
    }

    private void initializeJasperReport() {
        try {
            JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream("/reports/customer-report.jrxml"));
            jasperReport = JasperCompileManager.compileReport(jasperDesign);


        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }


    private void loadCustomer(){

        Connection connection = DBConnection.getInstance().getConnection();
        ObservableList<Customer> customerList = tblCustomer.getItems();
        customerList.clear();

        try {
            PreparedStatement pStm = connection.prepareStatement("select * from Customer where id LIKE ? or name LIKE ? or address LIKE ?");
            String query = "%" + txtSearch.getText() + "%";
            pStm.setString(1,query);
            pStm.setString(2,query);
            pStm.setString(3,query);

            ResultSet resultSet = pStm.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");

                customerList.add(new Customer(id,name,address));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnBeanArrayDataSourceOnAction(ActionEvent event) throws JRException {
        ObservableList<Customer> customerList = tblCustomer.getItems();
        Customer[] customers = customerList.toArray(new Customer[0]);

        HashMap<String, Object> reportParam = new HashMap<>();
        JRBeanArrayDataSource dataSource = new JRBeanArrayDataSource(customers);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, reportParam, dataSource);
        JasperViewer.viewReport(jasperPrint,false);


    }

    @FXML
    void btnBeanCollectionDataSourceOnAction(ActionEvent event) throws JRException {
        ObservableList<Customer> customerList = tblCustomer.getItems();

        HashMap<String, Object> reportParam = new HashMap<>();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(customerList);    // Here we can use instances that have followed Java Bean Spec

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, reportParam, dataSource);
        JasperViewer.viewReport(jasperPrint,false);


    }

}
