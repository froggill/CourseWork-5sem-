package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.DatabaseHandler;
import sample.Main;
import sample.User;

public class SignUpAdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField signUpLastname;

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField login_field;

    @FXML
    private TextField signUpName;

    @FXML
    private TextField signUpSurname;

    @FXML
    private TextField signUpCountry;

    @FXML
    private TextField signUpCity;

    @FXML
    private TextField signUpStreet;

    @FXML
    private Button signUpRegistrationButton;

    @FXML
    private RadioButton radiomale;

    @FXML
    private RadioButton radiofemale;

    @FXML
    private Button clckback;

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

    @FXML
    void initialize() {
        signUpRegistrationButton.setOnAction (actionEvent -> {
            signUpNewUser ();
        });
        clckback.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/UsersAdminMenu.fxml", clckback);
        });
    }

    private void signUpNewUser() {
        if (isInputValid ()) {
            String lastname = signUpLastname.getText ();
            String name = signUpName.getText ();
            String surname = signUpSurname.getText ();
            String password = password_field.getText ();
            String login = login_field.getText ();
            String country = signUpCountry.getText ();
            String city = signUpCity.getText ();
            String street = signUpStreet.getText ();
            String gender = "";
            String status = "admin";
            if (radiomale.isSelected ())
                gender = "Мужской";
            else gender = "Женский";
            User user = new User (lastname, name, surname, password, login, country, city, street, gender, status);
            try {
                Main.client.oos.writeInt (1);
                Main.client.oos.writeObject (user);
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (signUpLastname.getText () == null || signUpLastname.getText ().length () == 0) {
            errorMessage += "Не введена фамилия!\n";
        }
        if (signUpName.getText () == null || signUpName.getText ().length () == 0) {
            errorMessage += "Не введено имя!\n";
        }
        if (signUpSurname.getText () == null || signUpSurname.getText ().length () == 0) {
            errorMessage += "Не введено отчество!\n";
        }

        if (password_field.getText () == null || password_field.getText ().length () == 0) {
            errorMessage += "Не введен пароль!\n";
        }

        if (login_field.getText () == null || login_field.getText ().length () == 0) {
            errorMessage += "Не введен логин!\n";
        }

        if (signUpCountry.getText () == null || signUpCountry.getText ().length () == 0) {
            errorMessage += "Не введена страна!\n";
        }

        if (signUpCity.getText () == null || signUpCity.getText ().length () == 0) {
            errorMessage += "Не введен город!\n";
        }

        if (radiofemale.getText () == null || radiomale.getText ().length () == 0) {
            errorMessage += "Не введен пол!\n";
        }

        if (signUpStreet.getText () == null || signUpStreet.getText ().length () == 0) {
            errorMessage += "Не введена улица!\n";
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

