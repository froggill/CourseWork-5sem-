package sample.controllers;

        import java.io.IOException;
        import java.net.URL;
        import java.util.ResourceBundle;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.image.ImageView;
        import javafx.stage.Stage;

public class MenuAdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView onClclorder;

    @FXML
    private ImageView onClckuser;

    @FXML
    private ImageView onClckarchive;

    @FXML
    private Button butClclBook;

    @FXML
    private Button butClclOrdr;

    @FXML
    private Button butClckUsr;

    @FXML
    private Button butClckArch;

    @FXML
    private Button onclckbck;

    @FXML
    private Button onclckcht;

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
    @FXML
    void initialize() {
        butClclBook.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/BooksAdminMenu.fxml",butClclBook);
        });

        butClckArch.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/ArchiveAdminMenu.fxml",butClclBook);
        });

        butClckUsr.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/UsersAdminMenu.fxml",butClclBook);
        });

        butClclOrdr.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/OrderAdminMenu.fxml",butClclBook);
        });

        onclckcht.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/ChitAdminMenu.fxml",butClclBook);
        });

        onclckbck.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/sample.fxml",butClclBook);
        });
    }
}
