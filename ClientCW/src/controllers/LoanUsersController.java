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
import sample.DatabaseHandler;
import sample.Loan;

public class LoanUsersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Loan> table;

    @FXML
    private TableColumn<Loan, String> id;

    @FXML
    private TableColumn<Loan, String> iduser;

    @FXML
    private TableColumn<Loan, String> idbook;

    @FXML
    private TableColumn<Loan, String> term;

    @FXML
    private TableColumn<Loan, String> date;

    @FXML
    private Button buttonbck;

    private ObservableList<Loan> loansData = FXCollections.observableArrayList ();

    public ObservableList<Loan> getBooksData() {
        return loansData;
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'LoanUsers.fxml'.";
        assert id != null : "fx:id=\"id\" was not injected: check your FXML file 'LoanUsers.fxml'.";
        assert iduser != null : "fx:id=\"iduser\" was not injected: check your FXML file 'LoanUsers.fxml'.";
        assert idbook != null : "fx:id=\"idbook\" was not injected: check your FXML file 'LoanUsers.fxml'.";
        assert term != null : "fx:id=\"term\" was not injected: check your FXML file 'LoanUsers.fxml'.";
        assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'LoanUsers.fxml'.";
        assert buttonbck != null : "fx:id=\"buttonbck\" was not injected: check your FXML file 'LoanUsers.fxml'.";

        buttonbck.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/UsersAdminMenu.fxml");
        });

        DatabaseHandler db = new DatabaseHandler ();
        String query = "SELECT * FROM loans";
        PreparedStatement statement = db.getDbConnection ().prepareStatement (query);
        ResultSet resultSet = statement.executeQuery ();

        while (resultSet.next ()) {
            Loan loan = new Loan ();

            loan.setIDloan (resultSet.getInt (1));
            loan.setIDuser (resultSet.getInt (2));
            loan.setIDbook (resultSet.getInt (3));
            loan.setTerm (resultSet.getInt (4));
            loan.setDate (resultSet.getDate (5));
            loansData.add (loan);
        }


        id.setCellValueFactory (new PropertyValueFactory<Loan, String> ("IDloan"));
        idbook.setCellValueFactory (new PropertyValueFactory<Loan, String> ("IDbook"));
        iduser.setCellValueFactory (new PropertyValueFactory<Loan, String> ("IDuser"));
        term.setCellValueFactory (new PropertyValueFactory<Loan, String> ("term"));
        date.setCellValueFactory (new PropertyValueFactory<Loan, String> ("date"));

        table.setItems (loansData);
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
}
