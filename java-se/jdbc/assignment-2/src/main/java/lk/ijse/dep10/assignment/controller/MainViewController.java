package lk.ijse.dep10.assignment.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.dep10.assignment.model.User;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainViewController {

    public Button btnManageCustomers;
    public Button btnManageItems;
    public Button btnMangeUsers;
    public Label lblDateTime;
    public Label lblUser;

    public void initialize(){
        User principal = (User)System.getProperties().get("principal");
        lblUser.setText(String.format("%s : %s",principal.getRole().name(), principal.getFullName()));
        btnMangeUsers.setVisible(principal.getRole() == User.Role.ADMIN);

        KeyFrame key =new KeyFrame(Duration.seconds(1) , event -> {
           lblDateTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:dd")));
        });
        Timeline timeline = new Timeline(key);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();

        Platform.runLater(() -> {
            var accelerators = btnMangeUsers.getScene().getAccelerators();
            accelerators.put(new KeyCodeCombination(KeyCode.F1),btnManageCustomers::fire);
            accelerators.put(new KeyCodeCombination(KeyCode.F2),btnManageItems::fire);
            accelerators.put(new KeyCodeCombination(KeyCode.F3),btnMangeUsers::fire);
        });
    }

    public void btnManageCustomersOnAction(ActionEvent event) throws IOException {
        URL loginView = getClass().getResource("/view/CustomerView.fxml");
        var loginScene = new Scene(FXMLLoader.load(loginView));
        Stage stage = (Stage) btnManageCustomers.getScene().getWindow();
        stage.setScene(loginScene);
        stage.show();
        stage.centerOnScreen();


    }

    
    public void btnManageItemsOnAction(ActionEvent event) {

    }

    
    public void btnMangeUsersOnAction(ActionEvent event) {

    }

}
