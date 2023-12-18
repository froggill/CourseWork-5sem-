package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Book;
import sample.DatabaseHandler;

public class BookFindUserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonbck;

    @FXML
    private ComboBox<String> combopoisk;

    @FXML
    private TableView<Book> table;

    @FXML
    private TableColumn<Book, String> idd;

    @FXML
    private TableColumn<Book, String> name;

    @FXML
    private TableColumn<Book, String> ttll;

    @FXML
    private Button buttonfind;

    @FXML
    private TextField filtrfirld;

    private ObservableList<Book> booksData = FXCollections.observableArrayList ();

    public ObservableList<Book> getBooksData() {
        return booksData;
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        assert buttonbck != null : "fx:id=\"buttonbck\" was not injected: check your FXML file 'BookFind.fxml'.";
        assert combopoisk != null : "fx:id=\"combopoisk\" was not injected: check your FXML file 'BookFind.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'BookFind.fxml'.";
        assert idd != null : "fx:id=\"idd\" was not injected: check your FXML file 'BookFind.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'BookFind.fxml'.";
        assert ttll != null : "fx:id=\"ttll\" was not injected: check your FXML file 'BookFind.fxml'.";
        assert buttonfind != null : "fx:id=\"buttonfind\" was not injected: check your FXML file 'BookFind.fxml'.";
        assert filtrfirld != null : "fx:id=\"filtrfirld\" was not injected: check your FXML file 'BookFind.fxml'.";
        ObservableList<String> langs = FXCollections.observableArrayList ("ID", "Автор", "Название", "Год", "Наличие");
        buttonbck.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/BooksUserMenu.fxml");
        });

        DatabaseHandler db = new DatabaseHandler ();
        String query = "SELECT * FROM books";
        PreparedStatement statement = db.getDbConnection ().prepareStatement (query);
        ResultSet resultSet = statement.executeQuery ();

        while (resultSet.next ()) {
            Book book = new Book ();

            book.setID (resultSet.getInt (1));
            book.setAuthor (resultSet.getString (2));
            book.setNameb (resultSet.getString (3));
            book.setYear (resultSet.getInt (4));
            book.setPages (resultSet.getInt (5));
            book.setFormat (resultSet.getString (6));
            book.setMaterial (resultSet.getString (7));
            book.setCoefficient (resultSet.getFloat (8));
            book.setStatus (resultSet.getString (9));
            book.setNumber (resultSet.getInt (10));
            booksData.add (book);
        }
        idd.setCellValueFactory (new PropertyValueFactory<Book, String> ("ID"));
        name.setCellValueFactory (new PropertyValueFactory<Book, String> ("author"));
        ttll.setCellValueFactory (new PropertyValueFactory<Book, String> ("nameb"));

        table.setItems (booksData);
        combopoisk.setItems (langs);

    }

    @FXML
    void search(ActionEvent event) {
        List<Book> books = new ArrayList<> ();
        DatabaseHandler db = new DatabaseHandler ();
        ResultSet Set = null;

        String select = "SELECT * FROM books" ;

        PreparedStatement prt = null;
        try {
            prt = db.getDbConnection ().prepareStatement (select);
            Book book = new Book ();
            Set = prt.executeQuery ();
            while (true) {
                if (!Set.next ()) break;
                book = new Book ();
                book.setID (Set.getInt (1));
                book.setAuthor (Set.getString (2));
                book.setNameb (Set.getString (3));
                book.setYear (Set.getInt (4));
                book.setPages (Set.getInt (5));
                book.setFormat (Set.getString (6));
                book.setMaterial (Set.getString (7));
                book.setCoefficient (Set.getFloat (8));
                book.setStatus (Set.getString (9));
                book.setNumber (Set.getInt (10));
                booksData.add (book);
                books.add (book);
            }} catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace ();
        }
        String searchParameter = filtrfirld.getText ().toLowerCase ();
        String filter = combopoisk.getValue ();

        switch (filter) {
            case "ID":
                books = books.stream ()
                        .filter (book1 -> ((Integer)(book1.getID ())).toString ().toLowerCase ().contains (searchParameter))
                        .collect(Collectors.toList());
                break;
            case "Название":
                books = books.stream ()
                        .filter (book1 -> ((book1.getNameb ())).toLowerCase ().contains (searchParameter))
                        .collect(Collectors.toList());
                break;
            case "Автор":
                books = books.stream ()
                        .filter (book1 -> ((book1.getAuthor ())).toLowerCase ().contains (searchParameter))
                        .collect(Collectors.toList());
                break;
            case "Год":
                books = books.stream ()
                        .filter (book1 -> ((Integer)(book1.getYear ())).toString ().toLowerCase ().contains (searchParameter))
                        .collect(Collectors.toList());
                break;
            case "Наличие":
                books = books.stream ()
                        .filter (book1 -> ((book1.getStatus ())).toLowerCase ().contains (searchParameter))
                        .collect(Collectors.toList());
                break;
        }
        table.getItems().clear ();
        for (Book book2:books) {
            idd.setCellValueFactory (new PropertyValueFactory<Book, String> ("ID"));
            name.setCellValueFactory (new PropertyValueFactory<Book, String> ("author"));
            ttll.setCellValueFactory (new PropertyValueFactory<Book, String> ("nameb"));
            table.getItems ().add (book2);
        }


    }

    public void openNewWindow(String window) {
        buttonbck.getScene ().getWindow ().hide ();

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
}
