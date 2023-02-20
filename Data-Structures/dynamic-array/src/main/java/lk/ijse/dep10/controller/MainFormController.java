package lk.ijse.dep10.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import lk.ijse.dep10.util.DynamicArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class MainFormController {

    private DynamicArray numbers = new DynamicArray();

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnSize;

    @FXML
    private Button btnClearAll;

    @FXML
    private Button btnContains;

    @FXML
    private Button btnPrint;

    @FXML
    private Button btnPrintAll;

    @FXML
    private Button btnRemove;

    @FXML
    private TextField txtNumber;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String value =  txtNumber.getText();
        numbers.add(value);
        txtNumber.clear();
        txtNumber.requestFocus();

    }

    @FXML
    void btnClearAllOnAction(ActionEvent event) {
        numbers.clear();

    }



    @FXML
    void btnContainsOnAction(ActionEvent event) {
        TextInputDialog prompt = new TextInputDialog();
        prompt.setHeaderText("Enter Value");
        Optional<String> optInput = prompt.showAndWait();
        if(optInput.isEmpty() || optInput.get().isBlank()) return;
        int value = Integer.parseInt(optInput.get());
        if(numbers.contains(value)){
            System.out.println(true);
        } else{
            System.out.println(false);
        }

    }

    @FXML
    void btnPrintAllOnAction(ActionEvent event) {
//        System.out.println(numbers.toString());  // use Arrays.toString() method to print array
        System.out.println(Arrays.toString(numbers.getInputs()));


    }

    @FXML
    void btnPrintOnAction(ActionEvent event) {
        TextInputDialog prompt = new TextInputDialog();
        prompt.setHeaderText("Enter the Index");
        Optional<String> optInput = prompt.showAndWait();
        if(optInput.isEmpty() || optInput.get().isBlank()) return;
        int index = Integer.parseInt(optInput.get());
        if(index < 0 || index >= numbers.size()){
            System.out.println("Invalid Index");
            return;
        }
        System.out.println("Index " + index + " is : "+ numbers.get(index));

    }

    @FXML
    void btnRemoveOnAction(ActionEvent event) {
        TextInputDialog prompt = new TextInputDialog();
        prompt.setHeaderText("Enter the Index");
        Optional<String> optInput = prompt.showAndWait();
        if(optInput.isEmpty() || optInput.get().isBlank()) return;
        int index = Integer.parseInt(optInput.get());
        if(index < 0 || index >= numbers.size()){
            System.out.println("Invalid Index");
            return;
        }
        System.out.println(numbers.get(index) + " is removed");
        numbers.remove(index);


    }

    public void btnSizeOnAction(ActionEvent actionEvent) {
        System.out.println("Size :" + numbers.size());

    }
}
