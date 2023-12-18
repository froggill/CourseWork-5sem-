package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import sample.Book;
import sample.DatabaseHandler;

public class ChartBooksUserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button clckback;

    @FXML
    private BarChart<String, Book> chart;

    @FXML
    private CategoryAxis bookid;

    @FXML
    private NumberAxis raitingid;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        assert clckback != null : "fx:id=\"clckback\" was not injected: check your FXML file 'ChartBooks.fxml'.";
        assert chart != null : "fx:id=\"chart\" was not injected: check your FXML file 'ChartBooks.fxml'.";
        assert bookid != null : "fx:id=\"bookid\" was not injected: check your FXML file 'ChartBooks.fxml'.";
        assert raitingid != null : "fx:id=\"raitingid\" was not injected: check your FXML file 'ChartBooks.fxml'.";
        DatabaseHandler db = new DatabaseHandler ();
        List<Book> books = db.ViewBooks ();
        XYChart.Series<String,Book> seria = new XYChart.Series<> ();
        bookid.setTickLabelRotation(0);

        for (Book book:books){
            seria.getData ().add (new XYChart.Data (book.getNameb (),book.getCoefficient ()));
        }
        chart.getData ().addAll (seria);

        clckback.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/BooksUserMenu.fxml");
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
}
