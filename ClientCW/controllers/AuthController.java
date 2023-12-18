//package sample.controllers;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//import sample.User;
//
//public class AuthController {
//
//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;
//
//    @FXML
//    private TableView<User> table;
//
//    @FXML
//    private TableColumn<User, String> lstn;
//
//    @FXML
//    private TableColumn<User, String> name;
//
//    @FXML
//    private TableColumn<User, String> surname;
//
//    private ObservableList<User> usersData = FXCollections.observableArrayList();
//
//    @FXML
//    void initialize() {
//        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'AutorizationController.fxml'.";
//        assert lstn != null : "fx:id=\"lstn\" was not injected: check your FXML file 'AutorizationController.fxml'.";
//        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'AutorizationController.fxml'.";
//        assert surname != null : "fx:id=\"surname\" was not injected: check your FXML file 'AutorizationController.fxml'.";
//
//        initData();
//
//        // устанавливаем тип и значение которое должно хранится в колонке
//        lstn.setCellValueFactory(new PropertyValueFactory<User, String> ("lastname"));
//        name.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
//        surname.setCellValueFactory(new PropertyValueFactory<User, String> ("surname"));
//        table.setItems(usersData);
//    }
//
//    private void initData() {
//        usersData.add(new User ("Потапенко", "Игорь", "Михайлович", "1234", "а", "Михайлович", "Михайлович",  "Михайлович", "Михайлович", "Михайлович"));
//        usersData.add(new User("Атрощенко", "Натэлла", "Александровна", "1234", "а", "Михайлович", "Михайлович",  "Михайлович", "Михайлович", "Михайлович"));
//        usersData.add(new User("Корякина", "Анжела", "Петровна", "1234", "а", "Михайлович", "Михайлович",  "Михайлович", "Михайлович", "Михайлович"));
//        usersData.add(new User("Ступчик", "Валентин", "Игоревич", "1234", "а", "Михайлович", "Михайлович",  "Михайлович", "Михайлович", "Михайлович"));
//        usersData.add(new User("Троциков", "Антон", "Лохович", "1234", "а", "Михайлович", "Михайлович",  "Михайлович", "Михайлович", "Михайлович"));
//    }
//
//    private void showPersonDetails(User user) {
//        if (user != null) {
//            // Заполняем метки информацией из объекта person.
//            l.setText(user.getFirstName());
//            lastNameLabel.setText(user.getLastName());
//            streetLabel.setText(user.getStreet());
//            postalCodeLabel.setText(Integer.toString(user.getPostalCode()));
//            cityLabel.setText(user.getCity());
//
//            // TODO: Нам нужен способ для перевода дня рождения в тип String!
//            // birthdayLabel.setText(...);
//        } else {
//            // Если Person = null, то убираем весь текст.
//            firstNameLabel.setText("");
//            lastNameLabel.setText("");
//            streetLabel.setText("");
//            postalCodeLabel.setText("");
//            cityLabel.setText("");
//            birthdayLabel.setText("");
//        }
//    }
//}
package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
import sample.*;

public class AuthController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<User> table;

    @FXML
    private TableColumn<User, String> lstn;

    @FXML
    private TableColumn<User, String> name;

    @FXML
    private TableColumn<User, String> surname;

    @FXML
    private Label idlastname;

    @FXML
    private Label idname;

    @FXML
    private Label idsurname;

    @FXML
    private Label idcountry;

    @FXML
    private Label idcity;

    @FXML
    private Label idstreet;

    @FXML
    private Label idpassword;

    @FXML
    private Label idlogin;

    @FXML
    private Label idgender;

    @FXML
    private Button ideditbutton;

    @FXML
    private Button iddeletebutton;

    @FXML
    private Button idaddbutton;

    @FXML
    private Button idaddbutton1;

    @FXML
    private Button buttonbck;

    private Stage primaryStage;

    private ObservableList<User> usersData = FXCollections.observableArrayList();

    public ObservableList<User> getUsersData() {
        return usersData;
    }

    private Stage dialogStage;
    //private User user;
    private boolean okClicked = false;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException, IOException {
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'AutorizationController.fxml'.";
        assert lstn != null : "fx:id=\"lstn\" was not injected: check your FXML file 'AutorizationController.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'AutorizationController.fxml'.";
        assert surname != null : "fx:id=\"surname\" was not injected: check your FXML file 'AutorizationController.fxml'.";
        assert idlastname != null : "fx:id=\"idlastname\" was not injected: check your FXML file 'AutorizationController.fxml'.";
        assert idname != null : "fx:id=\"idname\" was not injected: check your FXML file 'AutorizationController.fxml'.";
        assert idsurname != null : "fx:id=\"idsurname\" was not injected: check your FXML file 'AutorizationController.fxml'.";
        assert idcountry != null : "fx:id=\"idcountry\" was not injected: check your FXML file 'AutorizationController.fxml'.";
        assert idcity != null : "fx:id=\"idcity\" was not injected: check your FXML file 'AutorizationController.fxml'.";
        assert idstreet != null : "fx:id=\"idstreet\" was not injected: check your FXML file 'AutorizationController.fxml'.";
        assert idpassword != null : "fx:id=\"idpassword\" was not injected: check your FXML file 'AutorizationController.fxml'.";
        assert idlogin != null : "fx:id=\"idlogin\" was not injected: check your FXML file 'AutorizationController.fxml'.";
        assert idgender != null : "fx:id=\"idgender\" was not injected: check your FXML file 'AutorizationController.fxml'.";
        assert ideditbutton != null : "fx:id=\"ideditbutton\" was not injected: check your FXML file 'AutorizationController.fxml'.";
        assert iddeletebutton != null : "fx:id=\"iddeletebutton\" was not injected: check your FXML file 'AutorizationController.fxml'.";
        assert idaddbutton != null : "fx:id=\"idaddbutton\" was not injected: check your FXML file 'AutorizationController.fxml'.";

        DatabaseHandler db = new DatabaseHandler ();
        String query = "SELECT * FROM users";
        PreparedStatement statement = db.getDbConnection ().prepareStatement (query);
        ResultSet resultSet = statement.executeQuery ();

        while (resultSet.next ()){
            User user = new User ();
            user.setID (resultSet.getInt (1));
            user.setLastname (resultSet.getString (2));
            user.setName (resultSet.getString (3));
            user.setSurname (resultSet.getString (4));
            user.setPassword (resultSet.getString (5));
            user.setLogin (resultSet.getString (6));
            user.setCountry (resultSet.getString (7));
            user.setCity (resultSet.getString (8));
            user.setStreet (resultSet.getString (9));
            user.setGender (resultSet.getString (10));
            user.setStatus (resultSet.getString (11));
            usersData.add (user);
            showUsersDetails (null);
            table.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> showUsersDetails (newValue));

        }
        lstn.setCellValueFactory(new PropertyValueFactory<User,String> ("lastname"));
        name.setCellValueFactory(new PropertyValueFactory<User, String> ("name"));
        surname.setCellValueFactory(new PropertyValueFactory<User, String> ("surname"));
        table.setItems(usersData);


        iddeletebutton.setOnAction (actionEvent -> {
            try {
                handleDeletePerson ();

            } catch (SQLException | ClassNotFoundException | IOException e) {
                e.printStackTrace ();
            }
        });
        ideditbutton.setOnAction (actionEvent -> {
            handleEditPerson ();
        });
        idaddbutton.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/AddUser.fxml",idaddbutton);
        });

        idaddbutton1.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/AutorizationController.fxml",idaddbutton1);
        });

        buttonbck.setOnAction (actionEvent -> {
            buttonbck.getScene ().getWindow ().hide ();

            FXMLLoader loader = new FXMLLoader ();
            loader.setLocation (getClass ().getResource ("/sample/fxml/UsersAdminMenu.fxml"));

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
    }

    private void showUsersDetails(User user) {
        if (user != null) {
            // Заполняем метки информацией из объекта person.
            idlastname.setText(user.getLastname ());
            idname.setText(user.getName ());
            idsurname.setText(user.getSurname ());
            idpassword.setText (user.getPassword ());
            idlogin.setText (user.getLogin ());
            idcountry.setText (user.getCountry ());
            idcity.setText (user.getCity ());
            idstreet.setText (user.getStreet ());
            idgender.setText (user.getGender ());
        } else {
            idgender.setText("");
            idstreet.setText("");
            idcity.setText("");
            idcountry.setText("");
            idlogin.setText("");
            idpassword.setText("");
            idsurname.setText("");
            idname.setText("");
            idlastname.setText("");
        }
    }

    private void handleDeletePerson() throws SQLException, ClassNotFoundException, IOException {
        int selectedIndex = table.getSelectionModel ().getSelectedIndex ();
        if (selectedIndex >= 0) {
            System.out.println (selectedIndex);
            String id = usersData.get (selectedIndex).getLogin ();
            Main.client.oos.writeInt (4);
            Main.client.oos.flush ();
            Main.client.oos.writeObject (id);
            table.getItems ().remove (selectedIndex);}
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Пользователь не выбран");
            alert.setContentText("Пожалуйста, выберите пользователя в таблице.");
            alert.showAndWait();
        }
    }

    public boolean showPersonEditDialog(User user) {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AuthController.class.getResource("/sample/fxml/EditUsersController.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            System.out.println (user.getID ());
            // Передаём адресата в контроллер.
            EditUsersController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(user);
            controller.IDred = user.getID ();

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait ();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void handleEditPerson() {
        User selectedPerson = table.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = showPersonEditDialog (selectedPerson);
            if (okClicked) {
                showUsersDetails (selectedPerson);
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
    public void openNewWindow(String window, Button button){
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
}
