package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Book;
import sample.DatabaseHandler;

public class ChitZalCatalogUserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Book> table;

    @FXML
    private TableColumn<Book, String> id;

    @FXML
    private TableColumn<Book, String> author;

    @FXML
    private TableColumn<Book, String> title;

    @FXML
    private TableColumn<Book, String> year;

    @FXML
    private TableColumn<Book, String> pages;

    @FXML
    private TableColumn<Book, String> format;

    @FXML
    private TableColumn<Book, String> material;

    @FXML
    private TableColumn<Book, String> coefficient;

    @FXML
    private Button buttonbck;

    private ObservableList<Book> booksData = FXCollections.observableArrayList();

    public ObservableList<Book> getBooksData() {
        return booksData;
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'ChitZalCatalogUser.fxml'.";
        assert id != null : "fx:id=\"id\" was not injected: check your FXML file 'ChitZalCatalogUser.fxml'.";
        assert author != null : "fx:id=\"author\" was not injected: check your FXML file 'ChitZalCatalogUser.fxml'.";
        assert title != null : "fx:id=\"title\" was not injected: check your FXML file 'ChitZalCatalogUser.fxml'.";
        assert year != null : "fx:id=\"year\" was not injected: check your FXML file 'ChitZalCatalogUser.fxml'.";
        assert pages != null : "fx:id=\"pages\" was not injected: check your FXML file 'ChitZalCatalogUser.fxml'.";
        assert format != null : "fx:id=\"format\" was not injected: check your FXML file 'ChitZalCatalogUser.fxml'.";
        assert material != null : "fx:id=\"material\" was not injected: check your FXML file 'ChitZalCatalogUser.fxml'.";
        assert coefficient != null : "fx:id=\"coefficient\" was not injected: check your FXML file 'ChitZalCatalogUser.fxml'.";
        assert buttonbck != null : "fx:id=\"buttonbck\" was not injected: check your FXML file 'ChitZalCatalogUser.fxml'.";

        buttonbck.setOnAction (actionEvent -> {
            buttonbck.getScene ().getWindow ().hide ();

            FXMLLoader loader = new FXMLLoader ();
            loader.setLocation (getClass ().getResource ("/sample/fxml/UserMenu.fxml"));

            try {
                loader.load ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
            Parent root = loader.getRoot ();
            Stage stage = new Stage ();
            stage.setScene (new Scene (root));
            stage.show ();
        });
        DatabaseHandler db = new DatabaseHandler ();
        String query = "SELECT * FROM books WHERE status LIKE 'зал'";
        PreparedStatement statement = db.getDbConnection ().prepareStatement (query);
        ResultSet resultSet = statement.executeQuery ();

        while (resultSet.next ()){
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
            booksData.add (book);
        }
        id.setCellValueFactory(new PropertyValueFactory<Book,String> ("ID"));
        author.setCellValueFactory(new PropertyValueFactory<Book, String> ("author"));
        title.setCellValueFactory(new PropertyValueFactory<Book, String> ("nameb"));
        year.setCellValueFactory(new PropertyValueFactory<Book, String> ("year"));
        pages.setCellValueFactory(new PropertyValueFactory<Book, String> ("pages"));
        format.setCellValueFactory(new PropertyValueFactory<Book, String> ("format"));
        material.setCellValueFactory(new PropertyValueFactory<Book, String> ("material"));
        coefficient.setCellValueFactory(new PropertyValueFactory<Book, String> ("coefficient"));

        table.setItems(booksData);
    }
}
