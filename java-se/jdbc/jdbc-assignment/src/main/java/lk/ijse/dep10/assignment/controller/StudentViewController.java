package lk.ijse.dep10.assignment.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class StudentViewController {

    public Button btnDeleteStudent;
    public Button btnNewStudent;
    public Button btnSaveStudent;
    public RadioButton rdFemale;
    public RadioButton rdMale;
    public TableView<?> tblStudents;
    public ToggleGroup tglGender;
    public TextField txtAddress;
    public DatePicker txtDOB;
    public TextField txtFirstName;
    public TextField txtId;
    public TextField txtLastName;
    public AnchorPane root;
    public Label lblId;
    public Label lblFirstName;
    public Label lblLastName;
    public Label lblAddress;
    public Label lblDOB;

    public void initialize() {
        lblId.setLabelFor(txtId);
        lblFirstName.setLabelFor(txtFirstName);
        lblLastName.setLabelFor(txtLastName);
        lblAddress.setLabelFor(txtAddress);
        lblDOB.setLabelFor(txtDOB);



    }

    public void btnDeleteStudentOnAction(ActionEvent event) {

    }


    public void btnNewStudentOnAction(ActionEvent event) {

    }


    public void btnSaveStudentOnAction(ActionEvent event) {

    }

    public void tblStudentsOnKeyReleased(KeyEvent keyEvent) {
    }
}
