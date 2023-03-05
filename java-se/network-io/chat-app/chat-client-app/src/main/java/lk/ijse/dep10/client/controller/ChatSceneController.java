package lk.ijse.dep10.client.controller;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lk.ijse.dep10.shared.Dep10Headers;
import lk.ijse.dep10.shared.Dep10Message;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ChatSceneController {

    TextArea txtChatHistory;

    @FXML
    private ListView<String> lstMessage;

    @FXML
    private ListView<String> lstUsers;

    @FXML
    private TextField txtMessage;

    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;


    public void initialize() {
        connect();
        readResponses();

    }

    private void connect() {
        try {
            socket = new Socket("127.0.0.1", 5050);
            oos = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to connect the server!").showAndWait();
            Platform.exit();
        }
    }

    private void readResponses() {
        ois = new ObjectInputStream(socket.getInputStream());

        Dep10Message dep10Message = (Dep10Message) ois.readObject();
        if (dep10Message.getHeader()  == Dep10Headers.USERS) {
            ArrayList<String> ipAddressList = (ArrayList<String>) dep10Message.getBody();
            Platform.runLater( () ->{
                lstUsers.getItems().clear();
                lstUsers.getItems().addAll(ipAddressList);
            });
        } else if (dep10Message.getHeader() == Dep10Headers.MSG) {
            Platform.runLater(() -> {
                lstMessage.getItems().add(dep10Message.getBody().toString());
            });

        }

    }


}
