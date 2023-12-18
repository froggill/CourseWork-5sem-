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

public class UsersAdminMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button clckcntrlusers;

    @FXML
    private Button clckaddadmin;

    @FXML
    private Button clcklistborrow;

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

        clckcntrlusers.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/AutorizationController.fxml");
        });

        clckaddadmin.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/SignUpAdmin.fxml");
        });

        clcklistborrow.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/LoanUsers.fxml");
        });
    }

}
