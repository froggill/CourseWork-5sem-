package sample.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.DatabaseHandler;
import sample.Main;
import sample.Order;

public class OrderEndAddController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button clckbck;

    @FXML
    private Button dob;

    @FXML
    private Label lbl;

    @FXML
    private Label lbl1;

    @FXML
    private Button dob1;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        assert clckbck != null : "fx:id=\"clckbck\" was not injected: check your FXML file 'OrderEndAdd.fxml'.";
        assert dob != null : "fx:id=\"dob\" was not injected: check your FXML file 'OrderEndAdd.fxml'.";
        assert lbl != null : "fx:id=\"lbl\" was not injected: check your FXML file 'OrderEndAdd.fxml'.";
        assert lbl1 != null : "fx:id=\"lbl1\" was not injected: check your FXML file 'OrderEndAdd.fxml'.";

        float d = (float) (Math.random ()*30+20);
        d = new BigDecimal (d).setScale(2, RoundingMode.UP).floatValue ();
        lbl.setText (String.valueOf (d));
        DatabaseHandler db = new DatabaseHandler ();
        ResultSet order =  db.MaxOrder ();
        float sum = 0;
        order.last ();
        if (order.getInt (1)>=15){
            sum = (float) (order.getInt (1)*d*0.85);
        }
        else if (order.getInt (1)>=5){
        sum = (float) (order.getInt (1)*d*0.95);}
        else {sum =  order.getInt (1)*d;}
        System.out.println (sum);
        lbl1.setText (String.valueOf (sum));

        float finalD = d;
        dob.setOnAction (actionEvent -> {
            try {
                Main.client.oos.writeInt (19);
                Main.client.oos.writeFloat (finalD);
            } catch (IOException e) {
                e.printStackTrace ();
            }
        });

        clckbck.setOnAction (actionEvent -> {
            openNewWindow ("/sample/fxml/OrderAdd.fxml");
        });
        dob1.setOnAction (actionEvent -> {
            try {
                Main.client.oos.writeInt (20);
            } catch (IOException e) {
                e.printStackTrace ();
            }
        });
    }
    public void openNewWindow(String window){
        clckbck.getScene ().getWindow ().hide ();

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

