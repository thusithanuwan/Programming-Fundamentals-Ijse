package lk.ijse.dep10.app.controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.*;

public class FileManagerSceneController {

    public TextField txtSelectedDestination;
    public Label lblProgress;
    @FXML
    private Button btnCopy;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSelectDestination;

    @FXML
    private Button btnSelectFile;

    @FXML
    private ProgressBar pgbProgress;

    @FXML
    private TextField txtSelectedFile;

//    SimpleDoubleProperty progress = new SimpleDoubleProperty();
    File selectedFile;
    File destination;

    public void initialize(){
        btnReset.setDisable(true);
//        pgbProgress.progressProperty().bind(progress);
    }

    @FXML
    void btnSelectFileOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(btnSelectFile.getScene().getWindow());

        if (selectedFile == null) {
            btnSelectFile.requestFocus();
            return;
        }
        btnSelectDestination.setDisable(false);
        btnReset.setDisable(false);
        txtSelectedFile.setText(selectedFile.getPath());
    }

    @FXML
    void btnSelectDestinationOnAction(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        destination = directoryChooser.showDialog(btnSelectDestination.getScene().getWindow());

        if (destination == null) {
            btnSelectDestination.requestFocus();
            return;
        }
        if (selectedFile.getPath().equals((destination.getPath() + File.separator + selectedFile.getName()))) {
            Alert warning = new Alert(Alert.AlertType.WARNING, "Select different Destination");
            warning.showAndWait();
            btnSelectDestination.requestFocus();
            return;
        }
        btnCopy.setDisable(false);
        txtSelectedDestination.setText(destination.getPath());
    }

    @FXML
    void btnCopyOnAction(ActionEvent event) throws IOException {
        File destinationFile = new File(destination.getPath() + File.separator + selectedFile.getName());
        System.out.println(destinationFile.getName());

        double selectedFileLength = selectedFile.length();

        FileInputStream fileInputStream = new FileInputStream(selectedFile);
        FileOutputStream fileOutputStream = new FileOutputStream(destinationFile);


       Task<Void> task = new Task<Void>() {
           @Override
           protected Void call() throws Exception {
               while(true){
                   byte[] buffer = new byte[1024];
                   int read = fileInputStream.read(buffer);
                   if(read == -1){
                       break;
                   }
                   fileOutputStream.write(buffer,0,read);
//                   System.out.println((destinationFile.length()/selectedFileLength * 100));
                   updateMessage(String.format("%.2f",(destinationFile.length()/selectedFileLength * 100)) + " % Completed");
                   updateProgress(destinationFile.length(),selectedFileLength);
               }
               return null;
           }
       };

       new Thread(task).start();
       pgbProgress.progressProperty().bind(task.progressProperty());
       lblProgress.textProperty().bind(task.messageProperty());



//        while(true){
//            byte[] buffer = new byte[1024];
//            int read = fileInputStream.read(buffer);
//            if(read == -1){
//                break;
//            }
//            fileOutputStream.write(buffer,0,read);
//            progress = new SimpleDoubleProperty(destinationFile.length() / selectedFile.length());
//
//        }






    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        txtSelectedFile.clear();
        txtSelectedDestination.clear();
        btnSelectDestination.setDisable(true);
        btnCopy.setDisable(true);
        pgbProgress.progressProperty().unbind();
        pgbProgress.setProgress(0.0);
        lblProgress.textProperty().unbind();
        lblProgress.setText("0 %");
    }

}
