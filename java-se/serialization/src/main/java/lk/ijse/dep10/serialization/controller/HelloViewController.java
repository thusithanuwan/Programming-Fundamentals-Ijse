package lk.ijse.dep10.serialization.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lk.ijse.dep10.serialization.model.Student;

import java.io.*;

public class HelloViewController {

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    private File homeDir = new File(System.getProperty("user.home"));
    private File desktopDir = new File(homeDir, "Desktop");
    private File studentFile = new File(desktopDir, "student.dep");

    public void initialize(){
        if (!studentFile.exists()) return;

        try {
            FileInputStream fis = new FileInputStream(studentFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Student student = (Student) ois.readObject();
            txtId.setText(student.getId());
            txtName.setText(student.getName());
            txtAddress.setText(student.getAddress());
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        Student student = new Student(txtId.getText(),
                txtName.getText(),
                txtAddress.getText());

        try {
            FileOutputStream fos = new FileOutputStream(studentFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(student);
            oos.close();

            new Alert(Alert.AlertType.INFORMATION, "Saved!").show();
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to save the student, try again").show();
        }
    }

}
