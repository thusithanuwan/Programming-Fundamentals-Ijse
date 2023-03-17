package lk.ijse.dep10.form;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep10.form.controller.MainFormController;

import java.io.IOException;
import java.sql.SQLException;

public class AppInitializer extends Application {

    public static void main(String[] args) throws SQLException {
        launch(args);
        MainFormController.connection.close();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/MainForm.fxml"));
        AnchorPane root = fxmlLoader.load();

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setTitle("Student Form");
        primaryStage.centerOnScreen();

    }
}
