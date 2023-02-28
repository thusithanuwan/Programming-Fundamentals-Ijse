package lk.ijse.dep10.serialization.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import lk.ijse.dep10.serialization.model.inherit1.D;

import java.io.*;

public class InheritanceController {

    @FXML
    private Button btnDeSerialize;

    @FXML
    private Button btnSerialize;

    private File file1 = new File("/home/thusitha/Desktop/File1.dep");

    @FXML
    void btnDeSerializeOnAction(ActionEvent event) {


        try{
            FileInputStream fis = new FileInputStream(file1);
            ObjectInputStream ois = new ObjectInputStream(fis);

            D dInstance = (D) ois.readObject();

            ois.close();
            System.out.println(dInstance);
        } catch (Exception e){
            e.printStackTrace();
        }


    }

    @FXML
    void btnSerializeOnAction(ActionEvent event) {
        D dInstance = new D(50, 60, 70, 80);

        try{
            FileOutputStream fis = new FileOutputStream(file1);
            ObjectOutputStream oos = new ObjectOutputStream(fis);
            oos.writeObject(dInstance);

            oos.close();
            System.out.println("Serialization Done!");
        } catch (Exception e){
            e.printStackTrace();
        }



    }

}
