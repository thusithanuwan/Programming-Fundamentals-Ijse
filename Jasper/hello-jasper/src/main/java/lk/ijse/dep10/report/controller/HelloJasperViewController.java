package lk.ijse.dep10.report.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.util.HashMap;

public class HelloJasperViewController {

    public Button btnPrint;
    public Button btnExport;
    @FXML
    private Button btnShowReport;

    @FXML
    void btnShowReportOnAction(ActionEvent event) {
        try {
            JasperDesign jasperDesign =JRXmlLoader.load(this.getClass().getResourceAsStream("/report/hello-jasper.jrxml"));

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            HashMap<String, Object> reportParams = new HashMap<>();
            JREmptyDataSource dataSource = new JREmptyDataSource(5);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, reportParams, dataSource);

            JasperViewer.viewReport(jasperPrint,false); // By default JVM shutdown when jasper report close, So we have say dont do that using "false"



        } catch (JRException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnPrintOnAction(ActionEvent actionEvent) {
    }

    public void btnExportOnAction(ActionEvent actionEvent) {
    }
}
