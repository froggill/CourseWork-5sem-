package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.DatabaseHandler;
import sample.Order;

public class OrderAcceptController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Order> table;

    @FXML
    private TableColumn<Order, String> id;

    @FXML
    private TableColumn<Order, String> author;

    @FXML
    private TableColumn<Order, String> title;

    @FXML
    private TableColumn<Order, String> year;

    @FXML
    private TableColumn<Order, String> pages;

    @FXML
    private TableColumn<Order, String> format;

    @FXML
    private TableColumn<Order, String> material;

    @FXML
    private TableColumn<Order, String> coefficient;

    @FXML
    private TableColumn<Order, String> status;

    @FXML
    private TableColumn<Order, String> number;

    @FXML
    private Button buttonbck;

    private ObservableList<Order> orderData = FXCollections.observableArrayList();

    public ObservableList<Order> getOrderData() {
        return orderData;
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'OrderAccept.fxml'.";
        assert id != null : "fx:id=\"id\" was not injected: check your FXML file 'OrderAccept.fxml'.";
        assert author != null : "fx:id=\"author\" was not injected: check your FXML file 'OrderAccept.fxml'.";
        assert title != null : "fx:id=\"title\" was not injected: check your FXML file 'OrderAccept.fxml'.";
        assert year != null : "fx:id=\"year\" was not injected: check your FXML file 'OrderAccept.fxml'.";
        assert pages != null : "fx:id=\"pages\" was not injected: check your FXML file 'OrderAccept.fxml'.";
        assert format != null : "fx:id=\"format\" was not injected: check your FXML file 'OrderAccept.fxml'.";
        assert material != null : "fx:id=\"material\" was not injected: check your FXML file 'OrderAccept.fxml'.";
        assert coefficient != null : "fx:id=\"coefficient\" was not injected: check your FXML file 'OrderAccept.fxml'.";
        assert status != null : "fx:id=\"status\" was not injected: check your FXML file 'OrderAccept.fxml'.";
        assert buttonbck != null : "fx:id=\"buttonbck\" was not injected: check your FXML file 'OrderAccept.fxml'.";

        buttonbck.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/OrderAdminMenu.fxml");
        });
        DatabaseHandler db = new DatabaseHandler ();
        String query = "SELECT * FROM orders";
        PreparedStatement statement = db.getDbConnection ().prepareStatement (query);
        ResultSet resultSet = statement.executeQuery ();

        while (resultSet.next ()){
            Order order = new Order ();

            order.setIDorder (resultSet.getInt (1));
            order.setAuthor (resultSet.getString (2));
            order.setName (resultSet.getString (3));
            order.setYear (resultSet.getInt (4));
            order.setPages (resultSet.getInt (5));
            order.setFormat (resultSet.getString (7));
            order.setMaterial (resultSet.getString (6));
            order.setPrice (resultSet.getFloat (8));
            order.setadmin (resultSet.getInt (9));
            order.setDate (resultSet.getDate(10));
            order.setStatus (resultSet.getString (11));
            order.setNumber (resultSet.getInt (12));
            orderData.add (order);
        }
        id.setCellValueFactory(new PropertyValueFactory<Order,String> ("IDorder"));
        author.setCellValueFactory(new PropertyValueFactory<Order, String> ("author"));
        title.setCellValueFactory(new PropertyValueFactory<Order, String> ("name"));
        year.setCellValueFactory(new PropertyValueFactory<Order, String> ("year"));
        pages.setCellValueFactory(new PropertyValueFactory<Order, String> ("pages"));
        format.setCellValueFactory(new PropertyValueFactory<Order, String> ("format"));
        material.setCellValueFactory(new PropertyValueFactory<Order, String> ("material"));
        coefficient.setCellValueFactory(new PropertyValueFactory<Order, String> ("price"));
        status.setCellValueFactory(new PropertyValueFactory<Order, String> ("status"));
        number.setCellValueFactory (new PropertyValueFactory<Order,String> ("number"));


        table.setItems(orderData);
    }

    public void openNewWindow(String window) {
        buttonbck.getScene ().getWindow ().hide ();
        FXMLLoader loader = new FXMLLoader ();
        loader.setLocation (getClass ().getResource (window));

        try {
            loader.load ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
        Parent root = loader.getRoot ();
        Stage stage = new Stage ();
        stage.setScene (new Scene (root));
        stage.show ();
    }
}
