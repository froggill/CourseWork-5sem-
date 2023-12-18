package sample.controllers;

import java.io.IOException;
import java.net.URL;
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
import sample.Client;
import sample.DatabaseHandler;
import sample.Main;
import sample.User;

public class EditUsersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField lastnameedit;

    @FXML
    private TextField nameedit;

    @FXML
    private TextField surnameedit;

    @FXML
    private TextField countryedit;

    @FXML
    private TextField ctyedit;

    @FXML
    private TextField strtedit;

    @FXML
    private TextField pssordedit;

    @FXML
    private TextField logedit;

    @FXML
    private TextField poledit;

    @FXML
    private Button okbutton;

    @FXML
    private Button buttonbck;

    private Stage dialogStage;
    private boolean okClicked = false;
    public int IDred;

    @FXML
    void initialize() throws IOException {
        assert lastnameedit != null : "fx:id=\"lastnameedit\" was not injected: check your FXML file 'Untitled'.";
        assert nameedit != null : "fx:id=\"nameedit\" was not injected: check your FXML file 'Untitled'.";
        assert surnameedit != null : "fx:id=\"surnameedit\" was not injected: check your FXML file 'Untitled'.";
        assert countryedit != null : "fx:id=\"countryedit\" was not injected: check your FXML file 'Untitled'.";
        assert ctyedit != null : "fx:id=\"ctyedit\" was not injected: check your FXML file 'Untitled'.";
        assert strtedit != null : "fx:id=\"strtedit\" was not injected: check your FXML file 'Untitled'.";
        assert pssordedit != null : "fx:id=\"pssordedit\" was not injected: check your FXML file 'Untitled'.";
        assert logedit != null : "fx:id=\"logedit\" was not injected: check your FXML file 'Untitled'.";
        assert poledit != null : "fx:id=\"poledit\" was not injected: check your FXML file 'Untitled'.";
        assert okbutton != null : "fx:id=\"okbutton\" was not injected: check your FXML file 'Untitled'.";
        assert buttonbck != null : "fx:id=\"buttonbck\" was not injected: check your FXML file 'Untitled'.";


        okbutton.setOnAction (actionEvent -> {
            try {
                handleOk ();
            } catch (IOException | SQLException | ClassNotFoundException e) {
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


    public void setPerson(User user) {
        lastnameedit.setText (user.getLastname ());
        nameedit.setText (user.getName ());
        surnameedit.setText (user.getSurname ());
        pssordedit.setText (user.getPassword ());
        logedit.setText (user.getLogin ());
        countryedit.setText (user.getCountry ());
        ctyedit.setText (user.getCity ());
        strtedit.setText (user.getStreet ());
        poledit.setText (user.getGender ());
        System.out.println (user.getID ());
    }

    public boolean isOkClicked() {
        return okClicked;
    }


    @FXML
    private void handleOk() throws IOException, SQLException, ClassNotFoundException {
        if (isInputValid ()) {
            User user = new User ();
            user.setLastname (lastnameedit.getText ());
            user.setName (nameedit.getText ());
            user.setStreet (surnameedit.getText ());
            user.setSurname (surnameedit.getText ());
            user.setPassword (pssordedit.getText ());
            user.setLogin (logedit.getText ());
            user.setCountry (countryedit.getText ());
            user.setCity (ctyedit.getText ());
            user.setStreet (strtedit.getText ());
            user.setGender (poledit.getText ());
            user.setStatus ("user");
            okClicked = true;
            setPerson (user);
            Main.client.oos.writeInt (5);
            user.setID (IDred);
            Main.client.oos.writeObject (user);
            dialogStage.close ();
        }
    }
    public void openNewWindow(String window){

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

        if (lastnameedit.getText () == null || lastnameedit.getText ().length () == 0) {
            errorMessage += "Не введена фамилия!\n";
        }
        if (nameedit.getText () == null || nameedit.getText ().length () == 0) {
            errorMessage += "Не введено имя!\n";
        }
        if (surnameedit.getText () == null || surnameedit.getText ().length () == 0) {
            errorMessage += "Не введено отчество!\n";
        }

        if (pssordedit.getText () == null || pssordedit.getText ().length () == 0) {
            errorMessage += "Не введен пароль!\n";
        }

        if (logedit.getText () == null || logedit.getText ().length () == 0) {
            errorMessage += "Не введен логин!\n";
        }

        if (countryedit.getText () == null || countryedit.getText ().length () == 0) {
            errorMessage += "Не введена страна!\n";
        }

        if (ctyedit.getText () == null || ctyedit.getText ().length () == 0) {
            errorMessage += "Не введен город!\n";
        }

        if (poledit.getText () == null || poledit.getText ().length () == 0) {
            errorMessage += "Не введен пол!\n";
        }

        if (strtedit.getText () == null || strtedit.getText ().length () == 0) {
            errorMessage += "Не введена улица!\n";
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
