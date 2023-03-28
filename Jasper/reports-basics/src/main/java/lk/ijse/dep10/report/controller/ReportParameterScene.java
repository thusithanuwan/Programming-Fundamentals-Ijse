package lk.ijse.dep10.report.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

public class ReportParameterScene {

    @FXML
    private Button btnShowReport;

    @FXML
    private DatePicker dpkDate;

    @FXML
    private TextField txtTotal;

    @FXML
    private TextField txtUserName;

    private JasperReport jasperReport;


    public void initialize() throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream("/reports/reportwith-param.jrxml"));
        jasperReport = JasperCompileManager.compileReport(jasperDesign);
    }

    @FXML
    void btnShowReportOnAction(ActionEvent event) throws JRException {

        String userName = txtUserName.getText();
//        userName = userName.isBlank() ? "No username has been entered" : userName;  // No need this line because we have handled in Jasper Report


        LocalDate date = dpkDate.getValue();

        BigDecimal total = txtTotal.getText().isBlank() ? null:new BigDecimal(txtTotal.getText());

        HashMap<String, Object> reportParam = new HashMap<>();
        reportParam.put("usename",userName);
        reportParam.put("date",date);
        reportParam.put("total",total);

        JREmptyDataSource dataSource = new JREmptyDataSource(1);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, reportParam, dataSource);
        JasperViewer.viewReport(jasperPrint,false);


    }

}
