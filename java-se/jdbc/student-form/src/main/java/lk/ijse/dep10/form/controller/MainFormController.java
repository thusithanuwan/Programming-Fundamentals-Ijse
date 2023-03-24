package lk.ijse.dep10.form.controller;

import com.google.protobuf.Struct;
import com.google.protobuf.Value;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dep10.form.util.Gender;
import lk.ijse.dep10.form.util.Student;

import java.time.LocalDate;


import java.sql.*;

public class MainFormController {

    @FXML
    private ToggleGroup Gender;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnNew;

    @FXML
    private RadioButton rdoFemale;

    @FXML
    private RadioButton rdoMale;


    @FXML
    private DatePicker dtpDob;

    @FXML
    private TableView<Student> tblStudents;

    @FXML
    private TextField txtFirst_Name;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtLast_Name;

    @FXML
    private TextField txt_Address;

    private ResultSet resultSet;
    public static Connection connection;

    public void initialize() {


        tblStudents.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblStudents.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("firstname"));
        tblStudents.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("lastname"));
        tblStudents.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudents.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("gender"));
        tblStudents.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("date"));


        connect();

        // Real Time Validation

        txtFirst_Name.textProperty().addListener((value, previous, current) -> {
            txtFirst_Name.getStyleClass().remove("invalid");

            if (!txtFirst_Name.getText().matches("[A-za-z]{5,}")) {
                txtFirst_Name.getStyleClass().add("invalid");
            }

        });
        txtLast_Name.textProperty().addListener((value, previous, current) -> {
            txtLast_Name.getStyleClass().remove("invalid");

            if (!txtLast_Name.getText().matches("[A-Za-z]{5,}")) {
                txtLast_Name.getStyleClass().add("invalid");
            }

        });
        txt_Address.textProperty().addListener((value, previous, current) -> {
            txt_Address.getStyleClass().remove("invalid");

            if (!txt_Address.getText().matches("\\w.{3,}")) {
                txt_Address.getStyleClass().add("invalid");
            }
        });
        dtpDob.valueProperty().addListener((value, previous, current) -> {
            dtpDob.getStyleClass().remove("invalid");

            if (dtpDob.getValue() == null) {
                return;
            }

            int year = dtpDob.getValue().getYear();
            if (!String.valueOf(year).matches("198[0-9]|199[0-9]|200[0-9]|2010")) {
                dtpDob.getStyleClass().add("invalid");
            }
        });

        tblStudents.getSelectionModel().selectedItemProperty().addListener((value, previous, current) -> {
            btnRemove.setDisable(current == null);
            if (current == null) {
                return;
            }
            txtID.setText(current.getId() + "");
            txtFirst_Name.setText(current.getFirstname());
            txtLast_Name.setText(current.getLastname());
            txt_Address.setText(current.getAddress());
            rdoMale.setSelected(current.getGender() == lk.ijse.dep10.form.util.Gender.MALE);
            rdoFemale.setSelected(current.getGender() == lk.ijse.dep10.form.util.Gender.FEMALE);
            dtpDob.setValue(Date.valueOf(current.getDate()).toLocalDate());

        });


    }

    private void connect() {

        ObservableList<Student> studentList = tblStudents.getItems();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://dep10.lk/dep10", "root", "mysql");

            String sql = "SELECT * FROM Student";
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);


            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String address = resultSet.getString("address");
                String gender = resultSet.getString("gender");
                Date dob = resultSet.getDate("dob");


                Student student = new Student(id, firstName, lastName, address,
                        gender.equals("MALE") ? lk.ijse.dep10.form.util.Gender.MALE : lk.ijse.dep10.form.util.Gender.FEMALE,
                        dob.toString());

                studentList.add(student);

            }


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to connect to the Server!").showAndWait();
            e.printStackTrace();
            return;
        }
    }

    @FXML
    void btnNewOnAction(ActionEvent event) {
        txtID.setText(idGenerator() + "");

        TextField[] textFields = new TextField[]{txtFirst_Name, txtLast_Name, txt_Address};
        for (TextField textField : textFields) {
            textField.clear();
            textField.getStyleClass().remove("invalid");
        }

        rdoMale.getToggleGroup().selectToggle(null);
        tblStudents.getSelectionModel().clearSelection();

        txtFirst_Name.requestFocus();


    }

    private int idGenerator() {
        int id = 0;
        ObservableList<Student> studentList = tblStudents.getItems();
        for (Student student : studentList) {
            if (student.getId() > id) {
                id = student.getId();
            }
        }
        return id + 1;

    }


    @FXML
    void btnRemoveOnAction(ActionEvent event) {
        Student selectedStudent = tblStudents.getSelectionModel().getSelectedItem();

        int id = selectedStudent.getId();

        String deleteSQL = "DELETE FROM Student WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Oops, Failed to Delete!").show();
            e.printStackTrace();
            return;
        }

        tblStudents.getItems().remove(selectedStudent);
        new Alert(Alert.AlertType.INFORMATION, "Deleted!").show();
        tblStudents.refresh();

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {


        boolean isValid = true;


        if (dtpDob.getValue() == null || dtpDob.getStyleClass().contains("invalid")) {
            isValid = false;
            dtpDob.requestFocus();
        }
        if (rdoMale.getToggleGroup().getSelectedToggle() == null || dtpDob.getStyleClass().contains("invalid")) {
            isValid = false;
            rdoMale.requestFocus();
        }

        TextField[] textFields = new TextField[]{txtFirst_Name, txtLast_Name, txt_Address};
        for (TextField textField : textFields) {
            if (textField.getText().isBlank() || dtpDob.getStyleClass().contains("invalid")) {
                isValid = false;
                textField.requestFocus();
            }
        }

        if (!isValid) return;

        Student selectedStudent = tblStudents.getSelectionModel().getSelectedItem();


        int id = Integer.parseInt(txtID.getText());
        String firstName = txtFirst_Name.getText();
        String lastName = txtLast_Name.getText();
        String address = txt_Address.getText();
        Gender gender = (rdoMale.isSelected() ? lk.ijse.dep10.form.util.Gender.MALE : lk.ijse.dep10.form.util.Gender.FEMALE);
        Date dob = Date.valueOf(dtpDob.getValue()); // Local Data -> SQL Data


        if (selectedStudent == null) {  // Add


            String insertSQL = "INSERT INTO Student(id, first_name, last_name, address, gender, dob) "
                    + "VALUES (?,?,?,?,?,?)";

            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(insertSQL);
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, firstName);
                preparedStatement.setString(3, lastName);
                preparedStatement.setString(4, address);
                preparedStatement.setString(5, gender.toString());    // Should pass String to the Enum
                preparedStatement.setDate(6, dob);

                preparedStatement.execute();

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Oops, Cannot Save!").show();
                e.printStackTrace();
                return;
            }

            //   Add to the table
            Student student = new Student(id, firstName, lastName, address, gender, dob.toString());
            tblStudents.getItems().add(student);

            new Alert(Alert.AlertType.INFORMATION, "Add New Student!").show();
            clear();
            return;

        }

        // Update


        String updateSQL = "UPDATE Student SET first_name = ?,last_name = ?,address = ?,gender =?,dob = ? WHERE id =?";


        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, gender.toString());    // Should pass String to the Enum
            preparedStatement.setDate(5, dob);
            preparedStatement.setInt(6, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Oops, Can Not Update!").show();
            e.printStackTrace();
            return;
        }

        selectedStudent.setFirstname(firstName);
        selectedStudent.setLastname(lastName);
        selectedStudent.setAddress(address);
        selectedStudent.setGender(gender);
        selectedStudent.setDate(dtpDob.getValue().toString()); // Local Date -> String

        new Alert(Alert.AlertType.ERROR, "Updated!").show();
        tblStudents.refresh();
        clear();
    }

    private void clear() {
        TextField[] textFields = new TextField[]{txtID, txtFirst_Name, txtLast_Name, txt_Address};
        for (TextField textField : textFields) {
            textField.clear();
            textField.getStyleClass().remove("invalid");
        }

        rdoMale.getToggleGroup().selectToggle(null);

        dtpDob.setValue(null);

        txtFirst_Name.requestFocus();

    }

}
