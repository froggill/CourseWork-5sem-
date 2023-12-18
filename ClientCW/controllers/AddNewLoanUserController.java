package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

public class AddNewLoanUserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> iduser;

    @FXML
    private Button buttonadd;

    @FXML
    private Button clckback;

    @FXML
    private DatePicker datefield;

    @FXML
    private ComboBox<String> idbook;

    @FXML
    private TextField termin;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException, IOException {
        assert iduser != null : "fx:id=\"iduser\" was not injected: check your FXML file 'AddNewLoan.fxml'.";
        assert buttonadd != null : "fx:id=\"buttonadd\" was not injected: check your FXML file 'AddNewLoan.fxml'.";
        assert clckback != null : "fx:id=\"clckback\" was not injected: check your FXML file 'AddNewLoan.fxml'.";
        assert datefield != null : "fx:id=\"datefield\" was not injected: check your FXML file 'AddNewLoan.fxml'.";
        assert idbook != null : "fx:id=\"idbook\" was not injected: check your FXML file 'AddNewLoan.fxml'.";
        assert termin != null : "fx:id=\"termin\" was not injected: check your FXML file 'AddNewLoan.fxml'.";

        clckback.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/BooksGetSetUser.fxml");
        });
        buttonadd.setOnAction (actionEvent -> {
            try {
                addNewLoan ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
        });
        DatabaseHandler databaseHandler = new DatabaseHandler ();
        String select = "SELECT booktitle FROM books WHERE status='доступна'";
        PreparedStatement statemen = databaseHandler.getDbConnection ().prepareStatement (select);
        ResultSet resultSet = statemen.executeQuery ();
        ObservableList<String> dbTypeList = FXCollections.observableArrayList();
        List<Book> books = new ArrayList<> ();
        while (resultSet.next ()){
            Book book = new Book ();
            book.setNameb (resultSet.getString (1));
            dbTypeList.add(book.getNameb ());
            books.add (book);
        }
        idbook.setItems (dbTypeList);
        DatabaseHandler db = new DatabaseHandler ();
        String q = "SELECT idusers FROM users ";
        PreparedStatement statemen1 = db.getDbConnection ().prepareStatement (q);
        ResultSet resultSet1 = statemen1.executeQuery ();
        ObservableList<String> dbTypeList1 = FXCollections.observableArrayList();
        List<User> users = new ArrayList<> ();
        while (resultSet1.next ()){
            User user = new User ();
            user.setID (resultSet1.getInt ("idusers"));
            dbTypeList1.add(String.valueOf (user.getID ()));
            users.add (user);
        }
        iduser.setItems (dbTypeList1);
    }
    private void addNewLoan() throws IOException {
        if (isInputValid ()) {
            String books = idbook.getValue ();
            Main.client.oos.writeInt (8);
            Main.client.oos.writeObject (books);
            int idbooks = Main.client.ois.readInt ();
            System.out.println (idbooks);
            int idusers = Integer.parseInt (iduser.getValue ());
            int time = Integer.parseInt (termin.getText ());
            Date date = Date.valueOf (datefield.getValue ());
            Loan loan = new Loan (idusers, idbooks, time, date);
            Main.client.oos.writeInt (9);
            Main.client.oos.writeObject (loan);
        }
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
    private boolean isInputValid() {
        String errorMessage = "";

        if (iduser.getValue () == null || iduser.getValue ().length () == 0) {
            errorMessage += "Не введен ИД пользователя!\n";
        }
        if (idbook.getValue () == null || idbook.getValue ().length () == 0) {
            errorMessage += "Не введен ИД книги!\n";
        }
        if (datefield.getValue () == null) {
            errorMessage += "Не введена дата!\n";
        }

        if (termin.getText () == null || termin.getText ().length () == 0) {
            errorMessage += "Не введен срок!\n";
        }

        if (errorMessage.length () == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert (Alert.AlertType.ERROR);
            //alert.initOwner (dialogStage);
            alert.setTitle ("Ошибка");
            alert.setHeaderText ("Пожалуйста, введите данные корректно.");
            alert.setContentText (errorMessage);

            alert.show ();

            return false;
        }
    }
}

