package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.*;

public class ArchiveAddController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> idbook;

    @FXML
    private TextField addname;

    @FXML
    private TextField idadmin;

    @FXML
    private TextField idauthor;

    @FXML
    private Button buttonadd;

    @FXML
    private Button clckback;

    @FXML
    private DatePicker datefield;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        assert idbook != null : "fx:id=\"idbook\" was not injected: check your FXML file 'ArchiveAdd.fxml'.";
        assert addname != null : "fx:id=\"addname\" was not injected: check your FXML file 'ArchiveAdd.fxml'.";
        assert idadmin != null : "fx:id=\"idadmin\" was not injected: check your FXML file 'ArchiveAdd.fxml'.";
        assert idauthor != null : "fx:id=\"idauthor\" was not injected: check your FXML file 'ArchiveAdd.fxml'.";
        assert buttonadd != null : "fx:id=\"buttonadd\" was not injected: check your FXML file 'ArchiveAdd.fxml'.";
        assert clckback != null : "fx:id=\"clckback\" was not injected: check your FXML file 'ArchiveAdd.fxml'.";
        assert datefield != null : "fx:id=\"datefield\" was not injected: check your FXML file 'ArchiveAdd.fxml'.";
        DatabaseHandler dbHandler = new DatabaseHandler ();
        buttonadd.setOnAction (actionEvent -> {
            addArchive ();
        });
        clckback.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/ArchiveAdminMenu.fxml", clckback);
        });
        String query = "SELECT idbooks FROM books ";
        PreparedStatement statement = dbHandler.getDbConnection ().prepareStatement (query);
        ResultSet resultSet = statement.executeQuery ();
        ObservableList<String> dbTypeList = FXCollections.observableArrayList();
        List<Book> books = new ArrayList<Book>();
        while (resultSet.next ()){
            Book book = new Book ();
            book.setID (resultSet.getInt (1));
            dbTypeList.add(String.valueOf (book.getID ()));
            books.add (book);
        }
        idbook.setItems (dbTypeList);
    }

    private void addArchive() {
        if (isInputValid ()){
        int idbooks = Integer.parseInt (idbook.getValue ());
        String date = String.valueOf (datefield.getValue ());
        int idadmins = Integer.parseInt (idadmin.getText ());
        String authors = idauthor.getText ();
        String titles = addname.getText ();
        Archive archive = new Archive (idbooks, date, authors, idadmins, titles);
        try {
            Main.client.oos.writeInt (11);
            Main.client.oos.writeObject (archive);
        } catch (IOException e) {
            e.printStackTrace ();
        }}
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
    private boolean isInputValid() {
        String errorMessage = "";

        if (idbook.getValue () == null || idbook.getValue ().length () == 0) {
            errorMessage += "Не введена фамилия!\n";
        }
        if (addname.getText () == null || addname.getText ().length () == 0) {
            errorMessage += "Не введено имя!\n";
        }
        if (datefield.getValue () == null ) {
            errorMessage += "Не введено отчество!\n";
        }

        if (idauthor.getText () == null || idauthor.getText ().length () == 0) {
            errorMessage += "Не введен пароль!\n";
        }

        if (idadmin.getText () == null || idadmin.getText ().length () == 0) {
            errorMessage += "Не введен логин!\n";
        }

        if (errorMessage.length () == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle ("Ошибка");
            alert.setHeaderText ("Пожалуйста, введите данные корректно.");
            alert.setContentText (errorMessage);

            alert.show ();

            return false;
        }
    }
}
