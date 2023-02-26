package lk.ijse.dep10.editor;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep10.editor.controller.EditorSceneController;

import java.awt.*;
import java.io.IOException;
import java.util.Optional;

public class AppInitializer extends Application {

    public Label filename;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/EditorScene.fxml"));
        AnchorPane root = fxmlLoader.load();

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Untitled");
        primaryStage.show();
        primaryStage.centerOnScreen();

        EditorSceneController controller = fxmlLoader.getController();
        controller.closeWindowEvent(primaryStage);






    }
}
