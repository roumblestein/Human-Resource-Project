package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {

    @FXML
    private TextField SsnText;
    @FXML
    private TextField PasswordText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void loginButton(ActionEvent event) throws IOException {
            int x;
            try{
                x = Integer.parseInt(SsnText.getText());
            }catch(NumberFormatException a){
                Alert dialog1 = new Alert(Alert.AlertType.INFORMATION);
                dialog1.setTitle("Information");
                dialog1.setHeaderText("Information");
                dialog1.setContentText("not numbers");
                dialog1.show();
            }
            if (SsnText.getLength() != 10) {

                    Alert dialog1 = new Alert(Alert.AlertType.INFORMATION);
                    dialog1.setTitle("Information");
                    dialog1.setHeaderText("Information");
                    dialog1.setContentText("Enter your 10 digit SSN");
                    dialog1.show();



            } else if (SsnText.getText().isEmpty() || PasswordText.getText().isEmpty()) {

                Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                dialog.setTitle("Information");
                dialog.setHeaderText("Information");
                dialog.setContentText("Enter details in empty fields");
                dialog.show();

            }else {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserScreen.fxml"));
                Parent root = fxmlLoader.load();

                Scene scene = new Scene(root);
                stage.setScene(scene);
            }
    }

    public void forgotButton (ActionEvent event) throws IOException {

        Node node = (Node)event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("forgotPass.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }









}
