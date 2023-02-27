package lk.ijse.dep10.concurrency.controller;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class progressBarSceneController {

    @FXML
    private Button btnRun;

    @FXML
    private Label lblProgress;

    @FXML
    private ProgressBar pgbProgressBar;

    @FXML
    void btnRunOnAction(ActionEvent event) {
        System.out.println(Thread.currentThread().getName());

       Task<Void> task = new Task<Void>() {
           @Override
           protected Void call() throws Exception {
               for (int i = 0; i <= 1000.0; i++) {
                   System.out.println(i);
                   updateMessage(String.format("%.2f",(i/1000.0 * 100) )+ " % Completed");
                   updateProgress(i,1000.0);
                                                    // UI can only work with Application Thread,so we need to say to the new thread to work with application thread and done the UI works that have done by new Thread
               }
               return null;
       }};

       new  Thread(task).start();
       pgbProgressBar.progressProperty().bind(task.progressProperty());
       lblProgress.textProperty().bind(task.messageProperty());








//
//            lblProgress.setText(progress +" % Complete");
//            pgbProgressBar.setProgress(progress);


        }

    }


