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

public class ChitAdminMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button clckVIEW;

    @FXML
    private Button clckbook;

    @FXML
    private Button clckbck;

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

    @FXML
    void initialize() {
        clckbck.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/MenuAdmin.fxml");
        });

        clckbook.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/ChitZalAdd.fxml");
        });
        clckVIEW.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/ChitZalCatalog.fxml");
        });
    }
}
