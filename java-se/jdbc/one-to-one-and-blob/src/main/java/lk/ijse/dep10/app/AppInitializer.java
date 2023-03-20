package lk.ijse.dep10.app;

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/StudentView.fxml"));
        AnchorPane root = fxmlLoader.load();

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.centerOnScreen();

    }
}
