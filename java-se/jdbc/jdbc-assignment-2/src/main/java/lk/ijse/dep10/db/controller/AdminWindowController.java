package lk.ijse.dep10.db.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AdminWindowController {

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnManageCustomer;

    @FXML
    private Button btnManageItems;

    @FXML
    private Button btnMoveUsers;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblUserName;

    public void initialize(){
        Timeline clock = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e ->
                lblTime.setText("Date : Time :"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))),
                new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }



    public void initData(SimpleStringProperty input){
        lblUserName.setText("Logged User : " +  input.getValue());
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/LoginWindow.fxml"));
        AnchorPane root = fxmlLoader.load();
        Stage stage = (Stage) btnMoveUsers.getScene().getWindow();
        stage.setScene(new Scene(root));

        stage.show();
        stage.centerOnScreen();

    }

    @FXML
    void btnManageCustomerOnAction(ActionEvent event) {


    }

    @FXML
    void btnManageItemsOnActions(ActionEvent event) {

    }

    @FXML
    void btnMoveUsersOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/AddUserWindow.fxml"));
        AnchorPane root = fxmlLoader.load();
        Stage stage = (Stage) btnMoveUsers.getScene().getWindow();
        stage.setScene(new Scene(root));

        stage.show();
        stage.centerOnScreen();


    }

}
