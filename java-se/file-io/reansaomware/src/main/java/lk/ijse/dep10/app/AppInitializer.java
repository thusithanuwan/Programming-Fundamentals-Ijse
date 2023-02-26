package lk.ijse.dep10.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.dep10.app.controller.SplashScreenController;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/SplashScreen.fxml"));
        AnchorPane root = fxmlLoader.load();

        SplashScreenController controller = fxmlLoader.getController();
        controller.initData(primaryStage);

        primaryStage.setScene(new Scene(root));

        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.toBack();
        primaryStage.show();
        primaryStage.centerOnScreen();

    }
}
