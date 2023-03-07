package lk.ijse.dep10.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.time.Duration;
import java.time.LocalDate;

public class MainFormController {

    @FXML
    private ComboBox<String> cmbType;

    @FXML
    private DatePicker dtpIn;

    @FXML
    private DatePicker dtpOut;

    @FXML
    private Label lblHint;

    @FXML
    private Label lblRate;

    @FXML
    private Label lblTotal;

    public void initialize() {
        cmbType.getItems().addAll("Economy", "Premium Economy", "Business Executive", "First Class");
    }

    @FXML
    void cmbTypeOnAction(ActionEvent event) {
        String selectedItem = cmbType.getSelectionModel().getSelectedItem();
        switch (selectedItem) {
            case "Economy":
                lblRate.setText("Rate: $5");
                break;
            case "Premium Economy":
                lblRate.setText("Rate: $7");
                break;
            case "Business Executive":
                lblRate.setText("Rate: $10");
                break;
            case "First Class":
                lblRate.setText("Rate: $15");
                break;
            default:
                lblRate.setText("Select a type to display the rate.");

        }
        calculateTotal();

    }

    @FXML
    void dtpInOnAction(ActionEvent event) {
        calculateTotal();
    }

    @FXML
    void dtpOutOnAction(ActionEvent event) {
        calculateTotal();
    }

    private void calculateTotal() {
        int selectedIndex = cmbType.getSelectionModel().getSelectedIndex();
        boolean isValid = true;
        long days = 0;

        if (selectedIndex == -1) {
            isValid = false;
        }

        if (dtpIn.getValue() == null || dtpOut.getValue() == null) {
            lblHint.setText("Select In and Out dates");
            isValid = false;
        } else if (dtpIn.getValue().isAfter(dtpOut.getValue())) {
            lblHint.setText("In and Out dates are Invalid!");
            isValid = false;
        } else if (dtpIn.getValue().isEqual(dtpOut.getValue())) {
            lblHint.setText("Date can not be equal.");
            isValid = false;
        }

        if (!isValid) {
            return;
        }

        int rate = new int[]{5, 7, 10, 15}[selectedIndex];


        LocalDate intDate = dtpIn.getValue();
        LocalDate outDate = dtpOut.getValue();

        days = Duration.between(intDate.atStartOfDay(), outDate.atStartOfDay()).toDays(); // To get days -> if we get Period we get months and days...eg -: 2Months 2Days
        lblHint.setText("Number of days : " + days);

        long total = rate * days;
        lblTotal.setText(String.format("Total: %.2f", (double)total));


    }

}
