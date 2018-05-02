package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login implements Initializable {

    @FXML
    private TextField SsnText;
    @FXML
    private PasswordField PasswordText;

    @FXML
    private Label ssnw;



    //Scanner input = new Scanner(System.in);

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    @FXML
    public void loginButton(ActionEvent event) throws IOException {
        /*String test = "111111-2222";
        String[] tokens = test.split("-");

       //private static final String USERNAME_REGEX = "^[0-9-]{11}$";
        String Ssn = SsnText.getText();
        String Password = PasswordText.getText();*/

        if (SsnText.getLength() != 11) {
            ssnw.setText("Write in this format YYMMDD-XXXX");

        }else if (SsnText.getText().isEmpty() || PasswordText.getText().isEmpty()) {
            ssnw.setText("Enter details in empty fields!");
        }else {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader FxmlLoader = new FXMLLoader(getClass().getResource("UserScreen.fxml"));
            Parent root = FxmlLoader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        }
    }

    public void forgotButton(ActionEvent event) throws IOException {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("forgotPass.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);

    }    @FXML
    private void exitButton(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Do you want to exit ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(1);
        }
    }

}