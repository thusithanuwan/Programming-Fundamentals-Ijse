package lk.ijse.dep10.app.controller;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import lk.ijse.dep10.app.db.DBConnection;
import lk.ijse.dep10.app.model.Item;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.sql.*;

public class ItemViewController {

    @FXML
    private Button btnBrowse;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnNew;

    @FXML
    private Button btnSave;

    @FXML
    private ImageView imgPreview;

    @FXML
    private TableView<Item> tblItems;

    @FXML
    private TextField txtBuyingPrice;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtSellingPrice;

    @FXML
    private TextField txtStock;

    public void initialize(){
        tblItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        tblItems.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("buyingPrice"));
        tblItems.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("stock"));
        tblItems.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("profit"));

        loadItems();

        Platform.runLater(btnNew::fire);

        tblItems.getSelectionModel().selectedItemProperty().addListener((value,previous,current) -> {
            btnDelete.setDisable(current == null);
            if(current == null) return;

            txtCode.setText(current.getCode() + "");
            txtDescription.setText(current.getDescription());
            txtBuyingPrice.setText(current.getBuyingPrice() + "");
            txtSellingPrice.setText(current.getSellingPrice() + "");
            txtStock.setText(current.getStock() + "");
            if (current.getPicture() != null){
                try {
                    // Blob -> javaFx image
                    Image image = new Image(current.getPicture().getBinaryStream());
                    imgPreview.setImage(image);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } btnClear.fire();

        });


    }
    private void loadItems(){
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            PreparedStatement prepItemPic = connection.prepareStatement("select * from Item_Preview where item_code=?");
            ResultSet resultSet = statement.executeQuery("select * from Items");

            while (resultSet.next()){
                int code = resultSet.getInt("code");
                String description = resultSet.getString("description");
                BigDecimal sellingPrice = resultSet.getBigDecimal("selling_price");
                BigDecimal buyingPrice = resultSet.getBigDecimal("buying_price");
                int stock = resultSet.getInt("stock");
                Blob picture = null;


                prepItemPic.setInt(1,code);
                ResultSet rstItemPic = prepItemPic.executeQuery();
                if (rstItemPic.next()){
                    picture = rstItemPic.getBlob("picture");
                }

                Item item = new Item(code, description, sellingPrice, buyingPrice, stock, picture);
                tblItems.getItems().add(item);
                tblItems.refresh();

            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnBrowseOnAction(ActionEvent event) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg","*.png","*.jpeg","*.jif","*.bmp"));  // Set constrain to fileChooser
        fileChooser.setTitle("Select the Preview Picture");
        File file = fileChooser.showOpenDialog(btnBrowse.getScene().getWindow());
        if (file != null) {
            Image image = new Image(file.toURI().toURL().toString());
            imgPreview.setImage(image);
            btnClear.setDisable(false);
        }


    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        Image image = new Image("/img/No_Image_Available.jpg");
        imgPreview.setImage(image);
        btnClear.setDisable(true);

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        Item selectedItem = tblItems.getSelectionModel().getSelectedItem();
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);
            PreparedStatement stmPicture = connection.prepareStatement("delete from Item_Preview where item_code=?");
            PreparedStatement stmItem = connection.prepareStatement("delete from Items where code =?");

            stmPicture.setInt(1,selectedItem.getCode());
            stmItem.setInt(1,selectedItem.getCode());

            stmPicture.executeUpdate();
            stmItem.executeUpdate();

            tblItems.getItems().remove(selectedItem);




        } catch (Throwable e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Failed to delete the Item!").show();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @FXML
    void btnNewOnAction(ActionEvent event) {
        txtCode.setText("Generated Code");
        txtDescription.clear();
        txtBuyingPrice.clear();
        txtSellingPrice.clear();
        txtStock.clear();
        tblItems.getSelectionModel().clearSelection();
        txtDescription.requestFocus();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);
            PreparedStatement prepItems = connection.prepareStatement("insert into Items(description,selling_price, buying_price,stock) values(?,?,?,?) ",
                    Statement.RETURN_GENERATED_KEYS);
            prepItems.setString(1,txtDescription.getText());
            prepItems.setString(2,txtSellingPrice.getText());
            prepItems.setString(3,txtBuyingPrice.getText());
            prepItems.setString(4,txtStock.getText());

            prepItems.executeUpdate();

            ResultSet generatedKeys = prepItems.getGeneratedKeys();
            generatedKeys.next();

            int code = generatedKeys.getInt(1);

            Item item = new Item(code, txtDescription.getText(), new BigDecimal(txtBuyingPrice.getText()),
                    new BigDecimal(txtSellingPrice.getText()), Integer.parseInt(txtStock.getText()), null);

            if (!btnClear.isDisable()){
                PreparedStatement preparedPic = connection.prepareStatement("insert into Item_Preview (Item_code, picture) values (?,?)");
                preparedPic.setInt(1, code);
                Image image = imgPreview.getImage(); // java-fx.image / can only use in java-fx applications
                // java-fx.image -> Blob <-> byte []
                BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null); // Come from Swing dependency/Buffered image can use for any java application
                ByteArrayOutputStream baos = new ByteArrayOutputStream(); // This is inside our app (Inside the heap), not connected to the network or any socket
                //
                ImageIO.write(bufferedImage,"png",baos);
                byte[] bytes = baos.toByteArray();
                Blob picture = new SerialBlob(bytes);
                preparedPic.setBlob(2,picture);
                preparedPic.executeUpdate();
                item.setPicture(picture); // If there is a picture, it has set in this line.

                tblItems.getItems().add(item);
                tblItems.refresh();
                btnNew.fire();

            }
            connection.commit();


        } catch (Throwable e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Failed to Save the Item!").show();
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @FXML
    void tblItemsOnKeyReleased(KeyEvent event) {

    }

}
