package lk.ijse.dep10.serialization.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import lk.ijse.dep10.serialization.model.Employee;
import lk.ijse.dep10.serialization.model.PersonInfo;
import lk.ijse.dep10.serialization.model.Status;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class TransientViewController {

    public VBox vboxSpouse;
    public ToggleGroup tglStatus;
    @FXML
    private Button btnAddEmployeeContact;

    @FXML
    private Button btnAddSpouseContact;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnNewEmployee;

    @FXML
    private Button btnRemoveEmployeeContact;

    @FXML
    private Button btnRemoveSpouseContact;

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtEmployeeContact;

    @FXML
    private TextField txtEmployeeID;

    @FXML
    private TextField txtEmployeeName;

    @FXML
    private TextField txtSpouseContact;

    @FXML
    private TextField txtSpouseName;

    @FXML
    private Label lblStatus;

    @FXML
    private ListView<String> lstEmployeeContact;

    @FXML
    private ListView<String> lstSpouseContact;

    @FXML
    private RadioButton rdoSingle;

    @FXML
    private RadioButton rdoMarried;

    @FXML
    private TableView<Employee> tblEmployeeDetail;

    private File file = new File("/home/thusitha/Desktop/employee.txt");
    private ArrayList<Employee> employeesList = new ArrayList<>();


    public void initialize() {
        tblEmployeeDetail.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblEmployeeDetail.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        tblEmployeeDetail.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("spouseName"));
        tblEmployeeDetail.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("btnRemove"));

        lstEmployeeContact.getSelectionModel().selectedItemProperty().addListener((value, previous, current) -> {
            btnRemoveEmployeeContact.setDisable(current == null);
        });
        lstSpouseContact.getSelectionModel().selectedItemProperty().addListener((value, previous, current) -> {
            btnRemoveSpouseContact.setDisable(current == null);
        });
        tblEmployeeDetail.getSelectionModel().selectedItemProperty().addListener((value, previous, current) -> {
            btnDelete.setDisable(current == null);
        });
        tglStatus.selectedToggleProperty().addListener((value, previous, current) -> {
            vboxSpouse.setDisable(current != rdoMarried);
        });


        btnRemoveEmployeeContact.setDisable(true);
        btnRemoveSpouseContact.setDisable(true);
        btnDelete.setDisable(true);
        vboxSpouse.setDisable(true);

    }

    @FXML
    void btnAddEmployeeContactOnAction(ActionEvent event) {
        String contact = txtEmployeeContact.getText();
        if (contact.isEmpty() || lstEmployeeContact.getItems().contains(contact)) {
            txtEmployeeContact.requestFocus();
            return;
        }
        lstEmployeeContact.getItems().add(txtEmployeeContact.getText());
        txtEmployeeContact.clear();
        txtEmployeeContact.requestFocus();

    }

    @FXML
    void btnAddSpouseContactOnAction(ActionEvent event) {
        String contact = txtSpouseContact.getText();
        if (contact.isEmpty() || lstSpouseContact.getItems().contains(contact)) {
            txtSpouseContact.requestFocus();
            return;
        }
        lstSpouseContact.getItems().add(txtSpouseContact.getText());
        txtSpouseContact.clear();
        txtSpouseContact.requestFocus();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnNewEmployeeOnAction(ActionEvent event) {
        for (TextField textField : new TextField[]{txtEmployeeID, txtEmployeeName, txtEmployeeContact, txtSpouseName, txtSpouseContact}) {
            textField.clear();
        }

        tglStatus.selectToggle(null);

        lstEmployeeContact.getItems().clear();
        lstSpouseContact.getItems().clear();

        tblEmployeeDetail.getSelectionModel().clearSelection();


        txtEmployeeID.requestFocus();
    }

    @FXML
    void btnRemoveEmployeeContactOnAction(ActionEvent event) {
        lstEmployeeContact.getItems().remove(lstEmployeeContact.getSelectionModel().getSelectedItem());
        if (lstEmployeeContact.getItems().isEmpty()) {
            txtEmployeeContact.requestFocus();
        }
    }

    @FXML
    void btnRemoveSpouseContactOnAction(ActionEvent event) {
        lstSpouseContact.getItems().remove(lstSpouseContact.getSelectionModel().getSelectedItem());
        if (lstSpouseContact.getItems().isEmpty()) {
            txtSpouseContact.requestFocus();
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

        if (!validationData()){        // AOP paradigm
            return;
        }

        String employeeID = txtEmployeeID.getText();
        String employeeName = txtEmployeeName.getText();
        ArrayList<String> employeeContact = new ArrayList<>(lstEmployeeContact.getItems());
        PersonInfo employeeInfo = new PersonInfo(employeeName, employeeContact);
        Status status = tglStatus.getSelectedToggle() == rdoMarried ? Status.MARRIED : Status.UNMARRIED;
        PersonInfo spouseInfo = null;
        if (status == Status.MARRIED) {
            String spouseName = txtSpouseName.getText();
            ArrayList<String> spouseContact = new ArrayList<>(lstSpouseContact.getItems());
            spouseInfo = new PersonInfo(spouseName, spouseContact);
        }

        Button btnRemove = new Button("DELETE");


        Employee employee = new Employee(employeeID, employeeInfo, status, spouseInfo, btnRemove);

        employeesList.add(employee);

        btnRemove.setOnAction(actionEvent -> tblEmployeeDetail.getItems().remove(employee));


        try {
            FileOutputStream fis = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fis);
            oos.writeObject(employeesList);
            tblEmployeeDetail.getItems().add(employee);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.INFORMATION, "Not Saved!").show();
            employeesList.remove(employee);
        }


    }

    public boolean validationData() {
        boolean isValid = true;

        if (lstEmployeeContact.getItems().isEmpty()) {
            isValid = false;
            txtEmployeeContact.requestFocus();
        }
        if (tglStatus.getSelectedToggle() == null) {
            isValid = false;
            rdoSingle.requestFocus();
        }
        if (txtEmployeeName.getText().isBlank()) {
            isValid = false;
            txtEmployeeName.requestFocus();
        }
        if (txtEmployeeID.getText().isBlank()) {
            isValid = false;
            txtEmployeeID.requestFocus();
        }
        if (rdoMarried.isSelected()) {
            if (lstSpouseContact.getItems().isEmpty()) {
                isValid = false;
                txtSpouseContact.requestFocus();
            }
            if (txtSpouseName.getText().isBlank()) {
                isValid = false;
                txtSpouseName.requestFocus();
            }
        }

        return isValid;

    }

    @FXML
    void lstEmployeeOnKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE) {
            btnRemoveEmployeeContact.fire();
        }
    }

    @FXML
    void lstSpouseOnKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE) {
            btnRemoveSpouseContact.fire();
        }

    }

    @FXML
    void tblEmployeeDetailOnkeyReleased(KeyEvent event) {

    }

}
