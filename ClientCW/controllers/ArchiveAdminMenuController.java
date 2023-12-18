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

public class ArchiveAdminMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button clckctlg;

    @FXML
    private Button clckdltbook;

    @FXML
    private Button clcknst;

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

        clckctlg.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/ArchiveView.fxml");
        });

        clckdltbook.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/OrderDel.fxml");
        });

        clcknst.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/ArchiveAdd.fxml");
        });
    }
}
