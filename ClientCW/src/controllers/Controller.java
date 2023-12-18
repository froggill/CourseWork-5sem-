package sample.controllers;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.User;
import sample.animations.Shake;

public class Controller implements Serializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button signInApp;

    @FXML
    private Button rgstButton;


    public Controller() {
    }

    @FXML
    void initialize() {
        signInApp.setOnAction (actionEvent -> {
            String login = login_field.getText ();
            String password = password_field.getText ();
            if (!login.equals ("") && !password.equals ("")) {
                try {
                    Main.client.oos.writeInt (2);
                    Main.client.oos.flush ();
                    loginUser (login, password);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace ();
                }
            } else
                System.out.println ("Login or password are empty");
        });


        rgstButton.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/sighUp.fxml");
        });
    }

    private void loginUser(String login, String password) throws IOException, ClassNotFoundException {
        User user = new User ();
        user.setLogin (login);
        user.setPassword (password);
        Main.client.oos.writeObject (user);
        User user2 = (User) Main.client.ois.readObject ();
        int counter = 0;
        if(user2 == null){}
        else counter=1;
        if (counter >= 1) {
            System.out.println (user2.getStatus ());
            if ((user2.getStatus ()).equals ("admin")){
            openNewWindow ("/sample/fxml/MenuAdmin.fxml");}
            else {openNewWindow ("/sample/fxml/UserMenu.fxml");}
            login_field.clear ();
            password_field.clear ();
        }
        else {
            Shake shake1 = new Shake (login_field);
            Shake shake2 = new Shake (password_field);
            shake1.playAnimation ();
            shake2.playAnimation ();
        }
    }
    public void openNewWindow(String window){
        rgstButton.getScene ().getWindow ().hide ();

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

