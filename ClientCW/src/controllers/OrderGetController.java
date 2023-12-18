package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import sample.DatabaseHandler;
import sample.Main;
import sample.Order;

public class OrderGetController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add;

    @FXML
    private Button clckbck;

    @FXML
    private ComboBox<String> comboidbook;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        assert add != null : "fx:id=\"add\" was not injected: check your FXML file 'OrderGet.fxml'.";
        assert clckbck != null : "fx:id=\"clckbck\" was not injected: check your FXML file 'OrderGet.fxml'.";
        assert comboidbook != null : "fx:id=\"comboidbook\" was not injected: check your FXML file 'OrderGet.fxml'.";
        DatabaseHandler databaseHandler = new DatabaseHandler ();
        String select = "SELECT idorder FROM orders WHERE status='оформлен'";
        PreparedStatement statemen = databaseHandler.getDbConnection ().prepareStatement (select);
        ResultSet resultSet = statemen.executeQuery ();
        ObservableList<String> dbTypeList = FXCollections.observableArrayList ();
        List<Order> orders = new ArrayList<> ();
        while (resultSet.next ()) {
            Order order = new Order ();
            order.setIDorder (resultSet.getInt (1));
            dbTypeList.add (String.valueOf (order.getIDorder ()));
            orders.add (order);
        }
        comboidbook.setItems (dbTypeList);

        add.setOnAction (actionEvent -> {
            if (isInputValid ()){
            String id = comboidbook.getValue ();
            try {
                Main.client.oos.writeInt (21);
                Main.client.oos.writeObject (id);
            } catch (IOException e) {
                e.printStackTrace ();
            }}
        });

        clckbck.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/OrderAdminMenu.fxml");
        });
    }
    public void openNewWindow(String window){
        clckbck.getScene ().getWindow ().hide ();

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
    private boolean isInputValid() {
        String errorMessage = "";

        if (comboidbook.getValue () == null || comboidbook.getValue ().length () == 0) {
            errorMessage += "Не введен ID!\n";
        }
        if (errorMessage.length () == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle ("Ошибка");
            alert.setHeaderText ("Пожалуйста, введите данные корректно.");
            alert.setContentText (errorMessage);

            alert.show ();

            return false;
        }
    }
}
