package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import sample.*;

public class GetBookController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonadd;

    @FXML
    private Button clckback;

    @FXML
    private DatePicker datefield;

    @FXML
    private ComboBox<String> idbook;

    @FXML
    private ComboBox<String> iduser;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        assert buttonadd != null : "fx:id=\"buttonadd\" was not injected: check your FXML file 'GetBook.fxml'.";
        assert clckback != null : "fx:id=\"clckback\" was not injected: check your FXML file 'GetBook.fxml'.";
        assert datefield != null : "fx:id=\"datefield\" was not injected: check your FXML file 'GetBook.fxml'.";
        assert idbook != null : "fx:id=\"idbook\" was not injected: check your FXML file 'GetBook.fxml'.";
        assert iduser != null : "fx:id=\"iduser\" was not injected: check your FXML file 'GetBook.fxml'.";


        clckback.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/BooksGetSet.fxml");
        });
        buttonadd.setOnAction (actionEvent -> {
            try {
                delLoan ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
        });
        DatabaseHandler databaseHandler = new DatabaseHandler ();
        String select = "SELECT idbook FROM loans ";
        PreparedStatement statemen = databaseHandler.getDbConnection ().prepareStatement (select);
        ResultSet resultSet = statemen.executeQuery ();
        ObservableList<String> dbTypeList = FXCollections.observableArrayList ();
        List<Book> books = new ArrayList<> ();
        while (resultSet.next ()) {
            Book book = new Book ();
            book.setID (resultSet.getInt (1));
            dbTypeList.add (String.valueOf (book.getID ()));
            books.add (book);
        }
        idbook.setItems (dbTypeList);
        DatabaseHandler db = new DatabaseHandler ();
        String q = "SELECT iduser FROM loans ";
        PreparedStatement statemen1 = db.getDbConnection ().prepareStatement (q);
        ResultSet resultSet1 = statemen1.executeQuery ();
        ObservableList<String> dbTypeList1 = FXCollections.observableArrayList ();
        List<User> users = new ArrayList<> ();
        while (resultSet1.next ()) {
            User user = new User ();
            user.setID (resultSet1.getInt (1));
            dbTypeList1.add (String.valueOf (user.getID ()));
            System.out.println (user.getID ());
            users.add (user);
        }
        iduser.setItems (dbTypeList1);
    }

    private void delLoan() throws IOException {
        int idbooks = Integer.parseInt (idbook.getValue ());
        int idusers = Integer.parseInt (iduser.getValue ());
        System.out.println (idbooks);
        System.out.println (idusers);
        Main.client.oos.writeInt (16);
        Main.client.oos.writeInt (idbooks);
        Main.client.oos.writeInt (idusers);
    }

    public void openNewWindow(String window) {
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
}
