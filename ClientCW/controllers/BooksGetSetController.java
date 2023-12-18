package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BooksGetSetController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button clckback;

    @FXML
    private Button priem;

    @FXML
    private Button vidacha;

    @FXML
    void initialize() {
        assert clckback != null : "fx:id=\"clckback\" was not injected: check your FXML file 'BooksGetSet.fxml'.";
        assert priem != null : "fx:id=\"priem\" was not injected: check your FXML file 'BooksGetSet.fxml'.";
        assert vidacha != null : "fx:id=\"vidacha\" was not injected: check your FXML file 'BooksGetSet.fxml'.";

        clckback.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/BooksAdminMenu.fxml");
        });

        vidacha.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/AddNewLoan.fxml");
        });

        priem.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/GetBook.fxml");
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
        //stage.showAndWait ();
        stage.show ();
    }

}
