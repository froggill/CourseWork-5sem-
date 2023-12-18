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

public class OrderAdminMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button clcknewbook;

    @FXML
    private Button clckgetzkz;

    @FXML
    private Button clckhistoryzkz;

    @FXML
    private Button clckback;

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

    @FXML
    void initialize() {
        clckback.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/MenuAdmin.fxml");
        });

        clcknewbook.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/OrderAdd.fxml");
        });

        clckhistoryzkz.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/OrderAccept.fxml");
        });

        clckgetzkz.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/OrderGet.fxml");
        });
    }
}
