package lk.ijse.dep10.serialization.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import lk.ijse.dep10.serialization.model.Customer;
import lk.ijse.dep10.serialization.model.Teacher;

import java.io.*;
import java.util.concurrent.ExecutionException;

public class SerialVersionUIDViewController {

    @FXML
    private Button btnDeSerialize;

    @FXML
    private Button btnSerialize;

//    private File file = new File("customer.dep10");
    private File file = new File("Teacher.dep10");

    @FXML
    void btnDeSerializeOnAction(ActionEvent event) {
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Teacher c001 = (Teacher) ois.readObject();
            new Alert(Alert.AlertType.INFORMATION, "Deserialization is done!").show();
            System.out.println(c001);

            ois.close();

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to fetch the customer.").show();
        }
    }

    @FXML
    void btnSerializeOnAcrion(ActionEvent event) {
//        Customer c001 = new Customer("S001", "Kasun", "Mawathagama");
        Teacher c001 = new Teacher(6, "Kasun");
        try {
            long serialVersionUID = ObjectStreamClass.lookup(c001.getClass()).getSerialVersionUID();
            System.out.println(serialVersionUID);


            FileOutputStream fis = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fis);
            oos.writeObject(c001);
            new Alert(Alert.AlertType.INFORMATION, "Serialization is done!").show();
//            oos.flush();  Don't need to add -> reason  when we close() the stream it automatically flush.
            oos.close();

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to write the customer.").show();

        }


    }

}
