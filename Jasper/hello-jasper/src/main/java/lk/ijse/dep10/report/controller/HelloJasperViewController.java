package lk.ijse.dep10.report.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.print.Printer;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.util.HashMap;

public class HelloJasperViewController {

    public Button btnPrint;
    public Button btnExport;
    @FXML
    private Button btnShowReport;
    private JasperPrint jasperPrint;


    public void initialize() {
        JasperDesign jasperDesign = null;
        try {
            jasperDesign = JRXmlLoader.load(this.getClass().getResourceAsStream("/report/hello-jasper.jrxml"));

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            HashMap<String, Object> reportParams = new HashMap<>();
            JREmptyDataSource dataSource = new JREmptyDataSource(5);

            jasperPrint = JasperFillManager.fillReport(jasperReport, reportParams, dataSource);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void btnShowReportOnAction(ActionEvent event) {

        JasperViewer.viewReport(jasperPrint, false); // default JVM shutdown when jasper report close, So we have say dont do that using "false"

    }

    public void btnPrintOnAction(ActionEvent actionEvent) throws JRException {
        //First and any printer in settings, if is not add new printer

        Printer defaultPrinter = Printer.getDefaultPrinter();   // Can check is there any default printer
//        System.out.println(defaultPrinter);
        if (defaultPrinter == null) {
                new Alert(Alert.AlertType.ERROR,"No default printer has been configured").showAndWait();
        }
        JasperPrintManager.printReport(jasperPrint, true);  // default print dialog box does not come so we have to call print dialog box by using "true"
    }

    public void btnExportOnAction(ActionEvent actionEvent) throws JRException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Report");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML File (*.xml)","*.xml"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("HTML File (*.html)","*.html"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF File (*.pdf)","*.pdf"));

        File file = fileChooser.showSaveDialog(btnExport.getScene().getWindow());
        if (file == null) return;
        String selectedExtension = fileChooser.getSelectedExtensionFilter().getExtensions().get(0);
        System.out.println(selectedExtension);

        switch (selectedExtension){
            case "*.xml":
                JasperExportManager.exportReportToXmlFile(jasperPrint, file.getAbsolutePath() + ".xml", true);
                break;
            case "*.html":
                JasperExportManager.exportReportToHtmlFile(jasperPrint,file.getAbsolutePath() + ".html");
                break;
            case "*.pdf":
                JasperExportManager.exportReportToPdfFile(jasperPrint, file.getAbsolutePath() + ".pdf");
        }

    }
}
