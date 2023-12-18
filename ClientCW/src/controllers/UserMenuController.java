package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane lichproperty;

    @FXML
    private Button butClclBook;

    @FXML
    private Button onclckbck;

    @FXML
    private Button onclckcht;

    @FXML
    void initialize() {
        assert lichproperty != null : "fx:id=\"lichproperty\" was not injected: check your FXML file 'UserMenu.fxml'.";
        assert butClclBook != null : "fx:id=\"butClclBook\" was not injected: check your FXML file 'UserMenu.fxml'.";
        assert onclckbck != null : "fx:id=\"onclckbck\" was not injected: check your FXML file 'UserMenu.fxml'.";
        assert onclckcht != null : "fx:id=\"onclckcht\" was not injected: check your FXML file 'UserMenu.fxml'.";


        onclckbck.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/sample.fxml",onclckbck);
        });

        onclckcht.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/ChitZalCatalogUser.fxml",onclckcht);
        });

        butClclBook.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/BooksUserMenu.fxml",butClclBook);
        });
    }
    public void openNewWindow(String window,Button button){
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
