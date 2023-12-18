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

public class BooksAdminMenuController {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button clckcatalog;
    @FXML
    private Button clckpoisk;
    @FXML
    private Button clckcontrolbooks;
    @FXML
    private Button clckgetset;
    @FXML
    private Button clckback;
   @FXML
    private Button chart;
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

    @FXML
    void initialize() {
        clckback.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/MenuAdmin.fxml");
        });

        clckcontrolbooks.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/BooksControl.fxml");
        });

        clckcatalog.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/BooksView.fxml");
        });

        clckpoisk.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/BookFind.fxml");
        });

        clckgetset.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/BooksGetSet.fxml");
        });

        chart.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/ChartBooks.fxml");
        });
    }
}
