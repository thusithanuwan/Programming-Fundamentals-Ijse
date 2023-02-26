package lk.ijse.dep10.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dep10.application.controller.model.Student;

import java.io.*;
import java.util.ArrayList;

public class MainFormSceneController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnNewStudent;

    @FXML
    private Button btnSave;

    @FXML
    private TableView<Student> tblStudent;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;


    private String usrHomeDir = System.getProperty("user.home");
    private File savedLocation = new File(usrHomeDir, "/Desktop/Student/student.txt");

    private ArrayList<Student> studentArrayList = new ArrayList<>();


    public void initialize() {

        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        
        if (!savedLocation.exists()) {
            return;
        }
        try{
            FileInputStream fis = new FileInputStream(savedLocation);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Student> readObject = (ArrayList)ois.readObject();
            studentArrayList = readObject;
            tblStudent.getItems().addAll(studentArrayList);
            System.out.println(tblStudent.getItems());
            ois.close();


        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Something went wrong");
        }



    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnNewStudentOnAction(ActionEvent event) {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();

        btnSave.setDisable(false);
        btnDelete.setDisable(true);

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();

        Student student = new Student(id, name, address);
        tblStudent.getItems().add(student);
        studentArrayList.add(student);


        try {
            System.out.println(savedLocation);
            FileOutputStream fis = new FileOutputStream(savedLocation);
            ObjectOutputStream oos = new ObjectOutputStream(fis);
            System.out.println("Hello");
            oos.writeObject(studentArrayList);
            oos.close();

            new Alert(Alert.AlertType.INFORMATION, "Saved.").show();


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong, try again.");
        }

    }
}
