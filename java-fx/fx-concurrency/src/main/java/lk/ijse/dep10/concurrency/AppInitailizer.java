package lk.ijse.dep10.concurrency;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitailizer extends Application {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        launch(args);
        System.out.println(Thread.currentThread().getName());
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        System.out.println(Thread.currentThread().getName());


        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/ProgressBarScene.fxml"));
        AnchorPane root = fxmlLoader.load();


        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.centerOnScreen();

    }
}
