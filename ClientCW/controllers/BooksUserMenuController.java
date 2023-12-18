package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BooksUserMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button clckcatalog;

    @FXML
    private Button clckpoisk;

    @FXML
    private Button clckgetset;

    @FXML
    private Button clckback;

    @FXML
    private Button chart;

    @FXML
    void initialize() {
        assert clckcatalog != null : "fx:id=\"clckcatalog\" was not injected: check your FXML file 'BooksUserMenu.fxml'.";
        assert clckpoisk != null : "fx:id=\"clckpoisk\" was not injected: check your FXML file 'BooksUserMenu.fxml'.";
        assert clckgetset != null : "fx:id=\"clckgetset\" was not injected: check your FXML file 'BooksUserMenu.fxml'.";
        assert clckback != null : "fx:id=\"clckback\" was not injected: check your FXML file 'BooksUserMenu.fxml'.";
        assert chart != null : "fx:id=\"chart\" was not injected: check your FXML file 'BooksUserMenu.fxml'.";

        clckback.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/UserMenu.fxml", clckback);
        });
        chart.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/ChartBooksUser.fxml", chart);
        });

        clckcatalog.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/BooksViewUser.fxml",clckcatalog);
        });

        clckpoisk.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/BookFindUser.fxml",clckpoisk);
        });

        clckgetset.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/BooksGetSetUser.fxml",clckgetset);
        });
    }

    public void openNewWindow(String window, Button button) {
        button.getScene ().getWindow ().hide ();

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
