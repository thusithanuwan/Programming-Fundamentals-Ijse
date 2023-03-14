package lk.ijse.dep10.regexp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lk.ijse.dep10.regexp.util.SearchResult;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javafx.beans.binding.Bindings.select;

public class MainFormController {

    @FXML
    private Button btnDown;

    @FXML
    private Button btnReplace;

    @FXML
    private Button btnReplaceAll;

    @FXML
    private Button btnUp;

    @FXML
    private CheckBox chkMaxCase;

    @FXML
    private Label lblResult;

    @FXML
    private TextArea txtEditor;

    @FXML
    private TextField txtFind;

    @FXML
    private TextField txtReplace;

    ArrayList<SearchResult> searchResultsList = new ArrayList<>();
    int position = 0;


    public void initialize() {
        txtFind.textProperty().addListener((value, previous, current) -> {
            findResultCount();
        });
        txtEditor.textProperty().addListener((value, previous, current) -> {
            findResultCount();
        });


    }

    private void findResultCount() {


        txtEditor.deselect();
        position = 0;
        searchResultsList.clear();

        String query = txtFind.getText();


        if (query.isEmpty()) return;
//            lblResult.setText("0 Result");
//        }
//        String[] split = txtEditor.getText().concat("").split(query); // should concat "" -> reason -: "Som|et|hing Something|et| "
//        lblResult.setText(split.length - 1 + ""); // should add (-1) -> reason :- Always we got additional split item.


        Pattern pattern;
        try {
            pattern = Pattern.compile(query);
        } catch (RuntimeException e) {
            return;
        }
        Matcher matcher = pattern.matcher(txtEditor.getText());

        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            SearchResult searchResult = new SearchResult(start, end);
            searchResultsList.add(searchResult);
        }
        lblResult.setText(String.format("%d Result", searchResultsList.size()));

        select();


    }

    void select() {
        if (searchResultsList.isEmpty()) return;
        SearchResult searchResult = searchResultsList.get(position);
        txtEditor.selectRange(searchResult.getStart(), searchResult.getEnd());
        lblResult.setText(String.format("%d/%d Result", (position +1),searchResultsList.size()));
    }

    @FXML
    void btnDownOnAction(ActionEvent event) {
        position += 1;
        if (position == searchResultsList.size()) {
            position = -1;
            return;
        }
        select();
    }


    @FXML
    void btnReplaceAllOnAction(ActionEvent event) {


    }

    @FXML
    void btnReplaceOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpOnAction(ActionEvent event) {
        position =- 1;
        if (position < 0) {
            position = searchResultsList.size();
            return;
        }
        select();
    }

    @FXML
    void chkMatchCaseOnAction(ActionEvent event) {

    }

}
