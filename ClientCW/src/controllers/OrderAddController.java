package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DatabaseHandler;
import sample.Main;
import sample.Order;


public class OrderAddController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField addauthor;

    @FXML
    private TextField number;

    @FXML
    private TextField addname;

    @FXML
    private TextField addyear;

    @FXML
    private TextField addpages;

    @FXML
    private TextField addformat;

    @FXML
    private TextField addmaterial;

    @FXML
    private Button buttonadd;

    @FXML
    private Button clckback;

    @FXML
    private TextField addidadmin;

    @FXML
    private DatePicker date;

    @FXML
    void initialize() {
        assert addauthor != null : "fx:id=\"addauthor\" was not injected: check your FXML file 'OrderAdd.fxml'.";
        assert addname != null : "fx:id=\"addname\" was not injected: check your FXML file 'OrderAdd.fxml'.";
        assert addyear != null : "fx:id=\"addyear\" was not injected: check your FXML file 'OrderAdd.fxml'.";
        assert addpages != null : "fx:id=\"addpages\" was not injected: check your FXML file 'OrderAdd.fxml'.";
        assert addformat != null : "fx:id=\"addformat\" was not injected: check your FXML file 'OrderAdd.fxml'.";
        assert addmaterial != null : "fx:id=\"addmaterial\" was not injected: check your FXML file 'OrderAdd.fxml'.";
        assert buttonadd != null : "fx:id=\"buttonadd\" was not injected: check your FXML file 'OrderAdd.fxml'.";
        assert clckback != null : "fx:id=\"clckback\" was not injected: check your FXML file 'OrderAdd.fxml'.";
        assert addidadmin != null : "fx:id=\"addidadmin\" was not injected: check your FXML file 'OrderAdd.fxml'.";
        buttonadd.setOnAction (actionEvent -> {
            try {
                addNewOrder ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
            openNewWindow ("/sample/fxml/OrderEndAdd.fxml");
        });
        clckback.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/OrderAdminMenu.fxml");
        });
    }
    public void openNewWindow(String window){
        clckback.getScene ().getWindow ().hide ();

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
    private void addNewOrder() throws IOException {
        if (isInputValid ()) {
            String author = addauthor.getText ();
            String name = addname.getText ();
            int year = Integer.parseInt (addyear.getText ());
            int pages = Integer.parseInt (addpages.getText ());
            String format = addformat.getText ();
            String material = addmaterial.getText ();
            float price = (float) (Math.random () * 20 + 30);
            int numb = Integer.parseInt (number.getText ());
            int idadmin = Integer.parseInt (addidadmin.getText ());
            Date datee = Date.valueOf (date.getValue ());
            //Date dat = String.valueOf (datee);
            Order order = new Order (author, name, year, pages, material, format, price, idadmin, datee, numb);
            Main.client.oos.writeInt (17);
            Main.client.oos.writeObject (order);
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (addauthor.getText () == null || addauthor.getText ().length () == 0) {
            errorMessage += "Не введена автор!\n";
        }
        if (addformat.getText () == null || addformat.getText ().length () == 0) {
            errorMessage += "Не введен формат!\n";
        }
        if (addidadmin.getText () == null || addidadmin.getText ().length () == 0) {
            errorMessage += "Не введено админ!\n";
        }

        if (addname.getText () == null || addname.getText ().length () == 0) {
            errorMessage += "Не введено название!\n";
        }

        if (addmaterial.getText () == null || addmaterial.getText ().length () == 0) {
            errorMessage += "Не введен материал!\n";
        }

        if (addpages.getText () == null || addpages.getText ().length () == 0) {
            errorMessage += "Не введены страницы!\n";
        }

        if (addyear.getText () == null || addyear.getText ().length () == 0) {
            errorMessage += "Не введены год!\n";
        }

        if (date.getValue () == null ) {
            errorMessage += "Не введена дата!\n";
        }

        if (number.getText () == null || number.getText ().length () == 0) {
            errorMessage += "Не введено количество!\n";
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
