package lk.ijse.dep10.serialization.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewController {

    @FXML
    private Button btnManageStudent;

    @FXML
    private Button btnHello;

    private Stage stgStudent;
    private Stage stgHello;

    @FXML
    void btnManageStudentOnAction(ActionEvent event) throws IOException {
        if (stgStudent != null) {
            return;
        }
        stgStudent = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/StudentView.fxml"));
        AnchorPane root = fxmlLoader.load();
        stgStudent.setScene(new Scene(root));
        stgStudent.show();
        stgStudent.centerOnScreen();
        stgStudent.setOnCloseRequest(e -> stgStudent = null);

    }

    @FXML
    void btnHelloOnAction(ActionEvent event) throws IOException {
        if (stgHello != null) {
            return;
        }
        stgHello = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/HelloView.fxml"));
        AnchorPane root = fxmlLoader.load();
        stgHello.setScene(new Scene(root));
        stgHello.show();
        stgHello.centerOnScreen();
        stgHello.setOnCloseRequest(e -> stgHello = null);

    }

}
