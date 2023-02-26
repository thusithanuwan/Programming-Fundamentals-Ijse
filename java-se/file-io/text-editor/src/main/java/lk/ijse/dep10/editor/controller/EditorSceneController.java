package lk.ijse.dep10.editor.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.clapper.util.html.HTMLUtil;
import org.jsoup.Jsoup;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.Optional;

public class EditorSceneController {

    public HTMLEditor txtEditor;
    public MenuItem mnSavaAs;
    @FXML
    private MenuItem mnAbout;

    @FXML
    private MenuItem mnClose;

    @FXML
    private MenuItem mnNew;

    @FXML
    private MenuItem mnOpen;

    @FXML
    private MenuItem mnPrint;

    @FXML
    private MenuItem mnSave;

    private boolean isSaved = false;
    private FileChooser fileChooser;
    private File file;
    String fileName;

    Stage sendStage;

    private boolean isChanged = false;

    String previous = "<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><br></p></body></html>";

//            "<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>"
    String current;
    String temporary;




    public void initialize() {
        fileName = "Untitled";

    }

    public void closeWindowEvent(Stage primaryStage) {
        sendStage = primaryStage;
        primaryStage.setOnCloseRequest(event -> {

            if (!isChanged) {
                primaryStage.close();
            }else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, String.format("Save changes to document %s before closing ?", fileName), ButtonType.OK, ButtonType.CANCEL, ButtonType.NO);
                Optional<ButtonType> button = alert.showAndWait();
                if (button.isPresent() && button.get() == ButtonType.YES) {
                    try {
                        save();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    primaryStage.close();
                } else if (button.isPresent() && button.get() == ButtonType.CANCEL) {
                    event.consume();
                } else{
                    primaryStage.close();
                }
                event.consume();
            }
        });
    }





    @FXML
    void mnAboutOnAction(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/AboutScene.fxml"));
        AnchorPane root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(sendStage.getScene().getWindow());
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.setTitle("About");
        stage.show();
        stage.centerOnScreen();


    }

    @FXML
    void mnCloseOnAction(ActionEvent event) {

    }

    @FXML
    void mnNewOnAction(ActionEvent event) {

        Stage stage = (Stage) txtEditor.getScene().getWindow();
        stage.setTitle("Untitled");

        if (isChanged && isSaved) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, String.format("Save changes to document %s before closing ?", fileName), ButtonType.OK, ButtonType.CANCEL, ButtonType.NO);
            Optional<ButtonType> button = alert.showAndWait();
            if (button.isPresent() && button.get() == ButtonType.YES) {
                try {
                    save();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                txtEditor.setHtmlText("");
                file = null;
                isSaved = false;

            } else if (button.isPresent() && button.get() == ButtonType.CANCEL) {
                event.consume();
            } else{
                txtEditor.setHtmlText("");  // Do not have clear methode, So we have to set blank text
                file = null;
                isSaved = false;
            }
            event.consume();
    }
        txtEditor.setHtmlText("");
        file = null;

    }

    public void txtEditorOnKeyReleased(KeyEvent keyEvent) throws IOException {

        current = txtEditor.getHtmlText();
        System.out.println(previous);
        System.out.println(current);

        Stage stage = (Stage) txtEditor.getScene().getWindow();

        if(!current.equals(previous) && (file == null)){
            stage.setTitle("*Untitled");
            isChanged = true;
        } else if (!current.equals(previous) && (file != null)) {
            stage.setTitle("*" + file.getName());
            isChanged = true;
        } else if (current.equals(previous) && (file == null)) {
            stage.setTitle("Untitled");
            isChanged = false;
        } else if (current.equals(previous) && (file != null)) {
            stage.setTitle(file.getName());
            isChanged = false;

        }


    }

    public void txtEditorOnMouseClicked(MouseEvent mouseEvent) {
//        current = txtEditor.getHtmlText();
//        System.out.println(current);
//        System.out.println(previous);
//        System.out.println(current.equals(previous));


//        if(!current.equals(previous) && stage.getTitle().equals("Untitled")){
//            stage.setTitle("*Untitled");
//            isChanged = true;
//        } else if (!current.equals(previous) && (!stage.getTitle().equals("*Untitled"))) {
//            stage.setTitle("*" + file.getName());
//            isChanged = true;
//        }
//        previous = current;

    }


    //Open a new File

    @FXML
    void mnOpenOnAction(ActionEvent event) throws IOException {

        fileChooser = new FileChooser();
//        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("txt"));         // To choose perticular file
        fileChooser.setTitle("Open a text file");
        file = fileChooser.showOpenDialog(txtEditor.getScene().getWindow());

        Stage stage = (Stage) txtEditor.getScene().getWindow();
        stage.setTitle(file.getName());

        if (file == null) return;
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = inputStream.readAllBytes();
        txtEditor.setHtmlText(new String(bytes));
        previous = txtEditor.getHtmlText();
        inputStream.close();

        isSaved = true;

    }

    @FXML
    void mnPrintOnAction(ActionEvent event) {

    }

    // Save method
    @FXML
    void mnSaveAction(ActionEvent event) throws IOException {
        save();
    }

    private void save() throws IOException {
        if (!isSaved) {
            fileChooser = new FileChooser();
            fileChooser.setTitle("Save a text file");
            file = fileChooser.showSaveDialog(txtEditor.getScene().getWindow());

            Stage stage = (Stage) txtEditor.getScene().getWindow();
            stage.setTitle(file.getName());

            if (file == null) return;
            isSaved = true;
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
//        String text = HTMLUtil.textFromHTML(txtEditor.getHtmlText());
        String text = txtEditor.getHtmlText();
        byte[] bytes = text.getBytes();
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
    // Save as method
    public void mnSavaAsOnAction(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save a text file");
        File file = fileChooser.showSaveDialog(txtEditor.getScene().getWindow());
        if (file == null) return;
        FileOutputStream fileOutputStream = new FileOutputStream(file, false); // By default this is false that means we can not append
//        String text = Jsoup.parse(txtEditor.getHtmlText()) ;
        String text = HTMLUtil.textFromHTML(txtEditor.getHtmlText());
        byte[] bytes = text.getBytes();
        fileOutputStream.write(bytes);

        this.file = file;  // Use for save
        isSaved = true;

        Stage stage = (Stage) txtEditor.getScene().getWindow();
        stage.setTitle(file.getName());

        fileOutputStream.close();
    }

    // Drag and Drop a file

    public void rootOnDragOver(DragEvent dragEvent) {
        dragEvent.acceptTransferModes(TransferMode.ANY);  // Accept external any drag

    }

    public void rootOnDragDrop(DragEvent dragEvent) throws IOException {
//        dragEvent.setDropCompleted(true);
        File dropFile = dragEvent.getDragboard().getFiles().get(0); // Making pointer

        System.out.println(dropFile.getName());

        Stage stage = (Stage) txtEditor.getScene().getWindow();
        stage.setTitle(dropFile.getName());

        FileInputStream fileInputStream = new FileInputStream(dropFile);
        byte[] bytes = fileInputStream.readAllBytes();
        txtEditor.setHtmlText(new String(bytes));
        fileInputStream.close();
    }
}
