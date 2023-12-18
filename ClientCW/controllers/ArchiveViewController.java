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
import sample.Archive;
import sample.Book;
import sample.DatabaseHandler;

public class ArchiveViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Archive> table;

    @FXML
    private TableColumn<Archive, String> idbook;

    @FXML
    private TableColumn<Archive, String> idadmin;

    @FXML
    private TableColumn<Archive, String> title;

    @FXML
    private TableColumn<Archive, String> author;

    @FXML
    private TableColumn<Archive, String> date;

    @FXML
    private Button buttonbck;

    private ObservableList<Archive> ArchiveData = FXCollections.observableArrayList();

    public ObservableList<Archive> getArchiveData() {
        return ArchiveData;
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'ArchiveView.fxml'.";
        assert idbook != null : "fx:id=\"idbook\" was not injected: check your FXML file 'ArchiveView.fxml'.";
        assert idadmin != null : "fx:id=\"idadmin\" was not injected: check your FXML file 'ArchiveView.fxml'.";
        assert title != null : "fx:id=\"title\" was not injected: check your FXML file 'ArchiveView.fxml'.";
        assert author != null : "fx:id=\"author\" was not injected: check your FXML file 'ArchiveView.fxml'.";
        assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'ArchiveView.fxml'.";
        assert buttonbck != null : "fx:id=\"buttonbck\" was not injected: check your FXML file 'ArchiveView.fxml'.";
        buttonbck.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/ArchiveAdminMenu.fxml");
        });

        DatabaseHandler db = new DatabaseHandler ();
        String query = "SELECT * FROM archive";
        PreparedStatement statement = db.getDbConnection ().prepareStatement (query);
        ResultSet resultSet = statement.executeQuery ();

        while (resultSet.next ()){
            Archive archive = new Archive ();

            archive.setID (resultSet.getInt (1));
            archive.setIDbook (resultSet.getInt (2));
            archive.setDate (resultSet.getString (3));
            archive.setIDadmin (resultSet.getInt (4));
            archive.setAuthor (resultSet.getString (5));
            archive.setTitle (resultSet.getString (6));
            ArchiveData.add (archive);
        }
        idbook.setCellValueFactory(new PropertyValueFactory<Archive,String> ("IDbook"));
        author.setCellValueFactory(new PropertyValueFactory<Archive, String> ("author"));
        title.setCellValueFactory(new PropertyValueFactory<Archive, String> ("title"));
        idadmin.setCellValueFactory(new PropertyValueFactory<Archive, String> ("IDadmin"));
        date.setCellValueFactory(new PropertyValueFactory<Archive, String> ("date"));

        table.setItems(ArchiveData);
    }
    public void openNewWindow(String window){
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
