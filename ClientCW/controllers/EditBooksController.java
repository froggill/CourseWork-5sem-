package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.*;

public class EditBooksController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField author;

    @FXML
    private TextField title;

    @FXML
    private TextField year;

    @FXML
    private TextField pages;

    @FXML
    private TextField format;

    @FXML
    private TextField material;

    @FXML
    private TextField reiting;

    @FXML
    private TextField status;

    @FXML
    private TextField number;

    @FXML
    private Button okbutton;

    @FXML
    private Button buttonbck;

    private Stage dialogStage;
    private boolean okClicked = false;
    public int IDred;

    @FXML
    void initialize() {
        assert author != null : "fx:id=\"author\" was not injected: check your FXML file 'EditBooksController.fxml'.";
        assert title != null : "fx:id=\"title\" was not injected: check your FXML file 'EditBooksController.fxml'.";
        assert year != null : "fx:id=\"year\" was not injected: check your FXML file 'EditBooksController.fxml'.";
        assert pages != null : "fx:id=\"pages\" was not injected: check your FXML file 'EditBooksController.fxml'.";
        assert format != null : "fx:id=\"format\" was not injected: check your FXML file 'EditBooksController.fxml'.";
        assert material != null : "fx:id=\"material\" was not injected: check your FXML file 'EditBooksController.fxml'.";
        assert reiting != null : "fx:id=\"reiting\" was not injected: check your FXML file 'EditBooksController.fxml'.";
        assert status != null : "fx:id=\"status\" was not injected: check your FXML file 'EditBooksController.fxml'.";
        assert number != null : "fx:id=\"number\" was not injected: check your FXML file 'EditBooksController.fxml'.";
        assert okbutton != null : "fx:id=\"okbutton\" was not injected: check your FXML file 'EditBooksController.fxml'.";
        assert buttonbck != null : "fx:id=\"buttonbck\" was not injected: check your FXML file 'EditBooksController.fxml'.";


        okbutton.setOnAction (actionEvent -> {
            try {
                handleOk ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
        });

        buttonbck.setOnAction (actionEvent -> {
            handleCancel ();
        });


    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public void setBook(Book user) {
        author.setText (user.getAuthor ());
        title.setText (user.getNameb ());
        year.setText (String.valueOf (user.getYear ()));
        material.setText (user.getMaterial ());
        reiting.setText (String.valueOf (user.getCoefficient ()));
        status.setText (user.getStatus ());
        format.setText (user.getFormat ());
        pages.setText (String.valueOf (user.getPages ()));
        number.setText (String.valueOf (user.getNumber ()));
        System.out.println (user.getID ());
    }

    public boolean isOkClicked() {
        return okClicked;
    }


    @FXML
    private void handleOk() throws IOException {
        if (isInputValid ()) {
            Book user = new Book ();
            user.setAuthor (author.getText ());
            user.setNameb (title.getText ());
            user.setYear (Integer.parseInt (year.getText ()));
            user.setPages (Integer.parseInt (pages.getText ()));
            user.setFormat (format.getText ());
            user.setMaterial (material.getText ());
            user.setCoefficient (Float.parseFloat (reiting.getText ()));
            user.setStatus (status.getText ());
            user.setNumber (Integer.parseInt (number.getText ()));
            okClicked = true;
            setBook (user);
            Main.client.oos.writeInt (15);
            user.setID (IDred);
            Main.client.oos.writeObject (user);
            dialogStage.close ();
        }
    }

    public void openNewWindow(String window) {

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

    @FXML
    private void handleCancel() {
        dialogStage.close ();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (author.getText () == null || author.getText ().length () == 0) {
            errorMessage += "Не введен автор!\n";
        }
        if (title.getText () == null || title.getText ().length () == 0) {
            errorMessage += "Не введено название!\n";
        }
        if (number.getText () == null || number.getText ().length () == 0) {
            errorMessage += "Не введено количество!\n";
        }

        if (reiting.getText () == null || reiting.getText ().length () == 0) {
            errorMessage += "Не введен рейтинг!\n";
        }

        if (pages.getText () == null || pages.getText ().length () == 0) {
            errorMessage += "Не введено количество страниц!\n";
        }

        if (year.getText () == null || year.getText ().length () == 0) {
            errorMessage += "Не введен год пубоикации!\n";
        }

        if (format.getText () == null || format.getText ().length () == 0) {
            errorMessage += "Не введен формат!\n";
        }

        if (material.getText () == null || material.getText ().length () == 0) {
            errorMessage += "Не введен материал!\n";
        }

        if (status.getText () == null || status.getText ().length () == 0) {
            errorMessage += "Не введена статус!\n";
        }

        if (errorMessage.length () == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.initOwner (dialogStage);
            alert.setTitle ("Ошибка");
            alert.setHeaderText ("Пожалуйста, введите данные корректно.");
            alert.setContentText (errorMessage);

            alert.show ();

            return false;
        }
    }
}
