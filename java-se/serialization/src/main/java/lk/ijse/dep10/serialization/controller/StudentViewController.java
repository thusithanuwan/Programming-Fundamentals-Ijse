package lk.ijse.dep10.serialization.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dep10.serialization.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class StudentViewController {

    public Label lblId;
    public Label lblName;
    public Label lblAddress;
    public Button btnNewStudent;
    public Button btnRemove;
    public Button btnSave;
    public TableView<Student> tblStudents;
    public TextField txtAddress;
    public TextField txtId;
    public TextField txtName;

    private ArrayList<Student> studentList = new ArrayList<>();
    private File dbFile = new File("student-database.dep10");

    public void initialize(){
        lblId.setLabelFor(txtId);
        lblName.setLabelFor(txtName);
        lblAddress.setLabelFor(txtAddress);
        btnRemove.setDisable(true);

        tblStudents.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblStudents.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudents.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));

//        ObservableList<Student> students = FXCollections.observableList(studentList);
//        tblStudents.setItems(students);

        loadAllStudents();

        tblStudents.getSelectionModel().selectedItemProperty().addListener((ov, old, current) -> {
            btnRemove.setDisable(current == null);
        });
    }

    private void loadAllStudents() {
        try {
            FileInputStream fis = new FileInputStream(dbFile);
            ObjectInputStream ois = new ObjectInputStream(fis);

//            System.out.println(this.studentList);
//            ArrayList<Student> studentList = (ArrayList<Student>) ois.readObject();
//            tblStudents.getItems().addAll(studentList);
//            System.out.println(this.studentList);

            studentList = (ArrayList<Student>) ois.readObject();
            tblStudents.getItems().addAll(studentList);

            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void btnNewStudentOnAction(ActionEvent event) {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();

        tblStudents.getSelectionModel().clearSelection();

        txtId.requestFocus();
    }

    
    public void btnRemoveOnAction(ActionEvent event) {
        Student selectedStudent = tblStudents.getSelectionModel().getSelectedItem();
        studentList.remove(selectedStudent);

        try {
            FileOutputStream fos = new FileOutputStream(dbFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(studentList);

            oos.close();
            tblStudents.getItems().remove(selectedStudent);
            btnNewStudent.fire();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to delete student").show();
            studentList.add(selectedStudent);
        }
    }

    
    public void btnSaveOnAction(ActionEvent event) {
        /* Data Validation */
        for (TextField txt : new TextField[]{txtId, txtName, txtAddress}) {
            if (txt.getText().isBlank()){
                txt.selectAll();
                txt.requestFocus();
                return;
            }
        }

        Student student = new Student(txtId.getText(),
                txtName.getText(),
                txtAddress.getText());
        studentList.add(student);

        try {
            FileOutputStream fos = new FileOutputStream(dbFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(studentList);
            oos.close();

            tblStudents.getItems().add(student);
            btnNewStudent.fire();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to save the student").show();
        }

    }

}
