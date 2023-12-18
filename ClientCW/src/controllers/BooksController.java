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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Book;
import sample.DatabaseHandler;
import sample.Main;
import sample.User;

public class BooksController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Book> table;

    @FXML
    private TableColumn<Book, String> idd;

    @FXML
    private TableColumn<Book, String> name;

    @FXML
    private TableColumn<Book, String> ttll;

    @FXML
    private Label id;

    @FXML
    private Label idauthor;

    @FXML
    private Label idtitle;

    @FXML
    private Label idyear;

    @FXML
    private Label idpages;

    @FXML
    private Label idformat;

    @FXML
    private Label idmaterial;

    @FXML
    private Label idcoefficient;

    @FXML
    private Label idstatus;

    @FXML
    private Label idnumber;

    @FXML
    private Button ideditbutton;

    @FXML
    private Button iddeletebutton;

    @FXML
    private Button buttonbck;

    @FXML
    private Button idaddbutton;

    @FXML
    private Button idaddbutton1;

    private ObservableList<Book> booksData = FXCollections.observableArrayList ();

    public ObservableList<Book> getBooksData() {
        return booksData;
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'BooksControl.fxml'.";
        assert idd != null : "fx:id=\"idd\" was not injected: check your FXML file 'BooksControl.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'BooksControl.fxml'.";
        assert ttll != null : "fx:id=\"ttll\" was not injected: check your FXML file 'BooksControl.fxml'.";
        assert id != null : "fx:id=\"id\" was not injected: check your FXML file 'BooksControl.fxml'.";
        assert idauthor != null : "fx:id=\"idauthor\" was not injected: check your FXML file 'BooksControl.fxml'.";
        assert idtitle != null : "fx:id=\"idtitle\" was not injected: check your FXML file 'BooksControl.fxml'.";
        assert idyear != null : "fx:id=\"idyear\" was not injected: check your FXML file 'BooksControl.fxml'.";
        assert idpages != null : "fx:id=\"idpages\" was not injected: check your FXML file 'BooksControl.fxml'.";
        assert idformat != null : "fx:id=\"idformat\" was not injected: check your FXML file 'BooksControl.fxml'.";
        assert idmaterial != null : "fx:id=\"idmaterial\" was not injected: check your FXML file 'BooksControl.fxml'.";
        assert idcoefficient != null : "fx:id=\"idcoefficient\" was not injected: check your FXML file 'BooksControl.fxml'.";
        assert idstatus != null : "fx:id=\"idstatus\" was not injected: check your FXML file 'BooksControl.fxml'.";
        assert ideditbutton != null : "fx:id=\"ideditbutton\" was not injected: check your FXML file 'BooksControl.fxml'.";
        assert iddeletebutton != null : "fx:id=\"iddeletebutton\" was not injected: check your FXML file 'BooksControl.fxml'.";
        assert buttonbck != null : "fx:id=\"buttonbck\" was not injected: check your FXML file 'BooksControl.fxml'.";
        assert idaddbutton != null : "fx:id=\"idaddbutton\" was not injected: check your FXML file 'BooksControl.fxml'.";

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
            showBooksDetails (null);
            table.getSelectionModel ().selectedItemProperty ().addListener (
                    (observable, oldValue, newValue) -> showBooksDetails (newValue));

        }
        idd.setCellValueFactory (new PropertyValueFactory<Book, String> ("ID"));
        name.setCellValueFactory (new PropertyValueFactory<Book, String> ("author"));
        ttll.setCellValueFactory (new PropertyValueFactory<Book, String> ("nameb"));
        table.setItems (booksData);

        idaddbutton.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/BooksAdd.fxml");
        });

        iddeletebutton.setOnAction (actionEvent -> {
            try {
                handleDeleteBook ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
        });

        idaddbutton1.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/BooksControl.fxml");
        });

        ideditbutton.setOnAction (actionEvent -> {
            handleEditBook ();
        });

        buttonbck.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/BooksAdminMenu.fxml");
        });
    }

    private void handleDeleteBook() throws IOException {
        int selectedIndex = table.getSelectionModel ().getSelectedIndex ();
        if (selectedIndex >= 0) {
            System.out.println (selectedIndex);
            int id = booksData.get (selectedIndex).getID ();
            System.out.println (id);
            Main.client.oos.writeInt (14);
            Main.client.oos.writeInt (id);
            table.getItems ().remove (selectedIndex);
        } else {
            Alert alert = new Alert (Alert.AlertType.WARNING);
            alert.setTitle ("Ошибка");
            alert.setHeaderText ("Книга не выбрана");
            alert.setContentText ("Пожалуйста, выберите книгу в таблице.");
            alert.showAndWait ();
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
        stage.show ();
    }

    private void showBooksDetails(Book book) {
        if (book != null) {
            id.setText (String.valueOf ((book.getID ())));
            idauthor.setText (book.getAuthor ());
            idtitle.setText (book.getNameb ());
            idyear.setText (String.valueOf (book.getYear ()));
            idpages.setText (String.valueOf (book.getPages ()));
            idformat.setText (book.getFormat ());
            idmaterial.setText (book.getMaterial ());
            idcoefficient.setText (String.valueOf (book.getCoefficient ()));
            idstatus.setText (book.getStatus ());
            idnumber.setText (String.valueOf (book.getNumber ()));
        } else {
            id.setText ("");
            idauthor.setText ("");
            idtitle.setText ("");
            idyear.setText ("");
            idpages.setText ("");
            idformat.setText ("");
            idmaterial.setText ("");
            idcoefficient.setText ("");
            idstatus.setText ("");
            idnumber.setText ("");
        }
    }

    public boolean showBookEditDialog(Book book) {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AuthController.class.getResource("/sample/fxml/EditBooksController.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Book");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Передаём адресата в контроллер.
            EditBooksController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setBook(book);
            controller.IDred = book.getID ();

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait ();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    @FXML
    private void handleEditBook() {
        Book selectedBook = table.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            boolean okClicked = showBookEditDialog (selectedBook);
            if (okClicked) {
                showBooksDetails (selectedBook);
            }

        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Пользователь не выбран");
            alert.setContentText("Пожалуйста, выберите пользователя из таблицы.");

            alert.showAndWait();
        }
    }
}
