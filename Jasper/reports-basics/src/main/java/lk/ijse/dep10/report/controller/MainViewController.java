package lk.ijse.dep10.report.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.dep10.report.db.DBConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

public class MainViewController {

    @FXML
    private Button btnBeanArrayvsBeanCollection;

    @FXML
    private Button btnDataConnectionDataSource;

    @FXML
    private Button btnFinalReport;

    @FXML
    private Button btnReportParam;

    @FXML
    void btnBeanArrayvsBeanCollectionOnAction(ActionEvent event) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/DataSourceScene1.fxml")));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Collection DS vs. Array DS");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(btnBeanArrayvsBeanCollection.getScene().getWindow());
        stage.show();
        stage.setResizable(false);
        stage.centerOnScreen();


    }

    @FXML
    void btnDataConnectionDataSourceOnAction(ActionEvent event) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream("/reports/customer-report-db.jrxml"));
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        HashMap<String, Object> reportParam = new HashMap<>();
        Connection connection = DBConnection.getInstance().getConnection();

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, reportParam, connection);
        JasperViewer.viewReport(jasperPrint,false);

    }

    @FXML
    void btnFinalReportOnAction(ActionEvent event) {

    }

    @FXML
    void btnReportParamOnAction(ActionEvent event) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/ReportParameterScene.fxml")));
        Stage stage = new Stage();
        stage.setScene(scene);
//        stage.setTitle("Collection DS vs. Array DS");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(btnBeanArrayvsBeanCollection.getScene().getWindow());
        stage.show();
        stage.setResizable(false);
        stage.centerOnScreen();



    }

}
