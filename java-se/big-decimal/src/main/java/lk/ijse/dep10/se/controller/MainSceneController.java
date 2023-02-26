package lk.ijse.dep10.se.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dep10.se.BigDecimalDemo;
import lk.ijse.dep10.se.controller.util.Item;

import java.math.BigDecimal;

public class MainSceneController {

    public Label lblProfit;
    @FXML
    private Button btnNew;

    @FXML
    private Button btnSave;

    @FXML
    private TableView<Item> tblItems;

    @FXML
    private TextField txtBuyingPrice;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtSellingPrice;

    public void initialize(){
        //Mapping
        tblItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("buyingPrice"));
        tblItems.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        tblItems.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("profit"));
        tblItems.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tblItems.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("total"));
        tblItems.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("totalProfit"));

        btnNew.fire(); // so we can generate code when press new Button

        //Listener to Item Table
        tblItems.getSelectionModel().selectedItemProperty().addListener((value,previous,current) -> {

        });
    }
    //New Item Button
    @FXML
    void btnNewOnAction(ActionEvent event) {
        txtCode.setText(idGenerator());
        txtDescription.clear();         // we can do that using for loop
        txtBuyingPrice.clear();
        txtSellingPrice.clear();
        txtQuantity.clear();
        tblItems.getSelectionModel().clearSelection();
        txtDescription.requestFocus();


    }
    //Save Button
    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String code = txtCode.getText();
        String description = txtDescription.getText();
        String sellingPrice = txtSellingPrice.getText();
        String buyingPrice = txtBuyingPrice.getText();


        int quantity = Integer.parseInt(txtQuantity.getText());
        Item item = new Item(code, description, new BigDecimal(buyingPrice),new BigDecimal(sellingPrice), quantity);
        tblItems.getItems().add(item);

        totalProfit(item);

        btnNew.fire();


    }
    //  To show Total Profit
    private void totalProfit(Item item){
        BigDecimal x = BigDecimal.ZERO;
        ObservableList<Item> itemList = tblItems.getItems();

        for (Item it : itemList) {
            x = x.add(it.getTotal());
        }
        lblProfit.setText("EST.TOTAL.PROFIT: " + x);
    }





    //Id Generator
    private String idGenerator() {
        ObservableList<Item> itemList = tblItems.getItems();
        if (itemList.isEmpty()) return "I001";
        int latestId = 1;
        for (Item item : itemList) {
            int id = Integer.parseInt(item.getCode().substring(1));
            if (latestId <= id) {
                latestId = id + 1;
            }
        }
        return String.format("I%03d", latestId);

    }

}
