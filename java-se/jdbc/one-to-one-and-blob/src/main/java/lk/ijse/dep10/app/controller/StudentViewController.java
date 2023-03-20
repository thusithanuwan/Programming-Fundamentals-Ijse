package lk.ijse.dep10.app.controller;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import lk.ijse.dep10.app.db.DBConnection;
import lk.ijse.dep10.app.model.Student;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.MalformedURLException;
import java.sql.*;

public class StudentViewController {

    @FXML
    private Button btnBrowse;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnNew;

    @FXML
    private Button btnSave;

    @FXML
    private ImageView imgProfile;

    @FXML
    private TableView<Student> tblStudents;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    public void initialize(){

        tblStudents.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblStudents.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudents.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));

        Platform.runLater(btnNew::fire);

        tblStudents.getSelectionModel().selectedItemProperty().addListener((value,previous,current) -> {
            btnDelete.setDisable(current == null);
            if (current == null) return;

            txtId.setText(current.getId() + "");
            txtName.setText(current.getName() + "");
            txtAddress.setText(current.getAddress() + "");
            if (current.getPicture() != null) {
                try {
                    Image image = new Image(current.getPicture().getBinaryStream());
                    imgProfile.setImage(image);
                    btnClear.setDisable(false);


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                btnClear.fire();
            }
        });

        loadStudent();

    }
    private void loadStudent(){
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from Profile_Picture where student_id =?");
            ResultSet resultSet = statement.executeQuery("select * from Student");

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                Blob picture = null;

                preparedStatement.setInt(1,id);
                ResultSet rstPicture = preparedStatement.executeQuery();
                if (rstPicture.next()){
                    picture = rstPicture.getBlob("picture");
                }

                Student student = new Student(id, name, address, picture);
                tblStudents.getItems().add(student);


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnBrowseOnAction(ActionEvent event) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg","*.png","*.jpeg","*.jif","*.bmp"));  // Set constrain to fileChooser
        fileChooser.setTitle("Select the Student Picture");
        File file = fileChooser.showOpenDialog(btnBrowse.getScene().getWindow());
        if (file != null) {
            Image image = new Image(file.toURI().toURL().toString());
            imgProfile.setImage(image);
            btnClear.setDisable(false);
        }

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        Image image = new Image("/img/No_Image_Available.jpg");
        imgProfile.setImage(image);
        btnClear.setDisable(true);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        Student selectedStudent = tblStudents.getSelectionModel().getSelectedItem();
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);
            PreparedStatement stmPicture = connection.prepareStatement("delete from Profile_Picture where student_id =?");
            PreparedStatement stmStudent = connection.prepareStatement("delete from Student where id =?");

            stmPicture.setInt(1,selectedStudent.getId());
            stmStudent.setInt(1,selectedStudent.getId());

            stmPicture.executeUpdate();
            stmStudent.executeUpdate();

            tblStudents.getItems().remove(selectedStudent);




        } catch (Throwable e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Failed to delete the customer!").show();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }

    @FXML
    void btnNewOnAction(ActionEvent event) {
        txtId.setText("Generated ID");
        txtName.clear();
        txtAddress.clear();
        btnClear.fire();
        tblStudents.getSelectionModel().clearSelection();
        txtName.requestFocus();

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (!isDataValid()) return;

        Connection connection = DBConnection.getInstance().getConnection();



        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("insert into Student (name, address) values (?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,txtName.getText());
            preparedStatement.setString(2,txtAddress.getText());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys(); // Should pass Statement.RETURN_GENERATED_KEYS
            generatedKeys.next();
            int Id = generatedKeys.getInt(1);
            Student student = new Student(Id, txtName.getText(), txtAddress.getText(), null);


            if (!btnClear.isDisable()){ // if btnCleat not disabled there is a image
                PreparedStatement preparedPic = connection.prepareStatement("insert into Profile_Picture (student_id, picture) values (?,?)");
                preparedPic.setInt(1, Id);
                Image image = imgProfile.getImage(); // java-fx.image / can only use in java-fx applications
                // java-fx.image -> Blob <-> byte []
                BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null); // Come from Swing dependency/Buffered image can use for any java application
                ByteArrayOutputStream baos = new ByteArrayOutputStream(); // This is inside our app (Inside the heap), not connected to the network or any socket
                //
                ImageIO.write(bufferedImage,"png",baos);
                byte[] bytes = baos.toByteArray();
                Blob picture = new SerialBlob(bytes);
                preparedPic.setBlob(2,picture);
                preparedPic.executeUpdate();
                student.setPicture(picture); // If there is a picture, it has set in this line.

            }
            connection.commit();

            tblStudents.getItems().add(student);
            tblStudents.refresh();
            btnNew.fire();





        } catch (Throwable e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Failed to save Student!");
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private boolean isDataValid() {
        boolean isValid = true;

        String name = txtName.getText().strip();
        String address = txtAddress.getText().strip();


        if (address.length() <=3){
            txtAddress.requestFocus();
            txtAddress.selectAll();
            isValid = false;
        }

        if (!name.matches("[A-Za-z ]+")){
            txtName.requestFocus();
            txtName.selectAll();
            isValid = false;
        }

        return isValid;
    }

    @FXML
    void tblOnKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE) btnDelete.fire();

    }

}
