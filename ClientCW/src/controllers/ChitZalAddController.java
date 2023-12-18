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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Book;
import sample.DatabaseHandler;
import sample.Main;
import sample.User;

public class ChitZalAddController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField addauthor;

    @FXML
    private TextField addcoeff;

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
    void initialize() {
        assert addauthor != null : "fx:id=\"addauthor\" was not injected: check your FXML file 'ChitZalAdd.fxml'.";
        assert addcoeff != null : "fx:id=\"addcoeff\" was not injected: check your FXML file 'ChitZalAdd.fxml'.";
        assert addname != null : "fx:id=\"addname\" was not injected: check your FXML file 'ChitZalAdd.fxml'.";
        assert addyear != null : "fx:id=\"addyear\" was not injected: check your FXML file 'ChitZalAdd.fxml'.";
        assert addpages != null : "fx:id=\"addpages\" was not injected: check your FXML file 'ChitZalAdd.fxml'.";
        assert addformat != null : "fx:id=\"addformat\" was not injected: check your FXML file 'ChitZalAdd.fxml'.";
        assert addmaterial != null : "fx:id=\"addmaterial\" was not injected: check your FXML file 'ChitZalAdd.fxml'.";
        assert buttonadd != null : "fx:id=\"buttonadd\" was not injected: check your FXML file 'ChitZalAdd.fxml'.";
        assert clckback != null : "fx:id=\"clckback\" was not injected: check your FXML file 'ChitZalAdd.fxml'.";
         buttonadd.setOnAction (actionEvent -> {
             try {
                 addNewBook ();
             } catch (IOException e) {
                 e.printStackTrace ();
             }
         });
        clckback.setOnAction (actionEvent -> {
        openNewWindow ("/sample/fxml/ChitAdminMenu.fxml");
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
    private void addNewBook() throws IOException {
        String author = addauthor.getText ();
        String name = addname.getText ();
        int year = Integer.parseInt (addyear.getText ());
        int pages = Integer.parseInt (addpages.getText ());
        String format = addformat.getText ();
        String material = addmaterial.getText ();
        float coeff = Float.parseFloat (addcoeff.getText ());
        String status = "зал";
        Book book = new Book (name,author,year,pages,format,material,coeff,status,1);
        Main.client.oos.writeInt (13);
        Main.client.oos.writeObject (book);
    }
}
