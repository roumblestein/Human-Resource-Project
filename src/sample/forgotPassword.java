package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class forgotPassword implements Initializable {


    @FXML
    private Button emailButton;

    @FXML
    private TextField emailCode;

    @FXML
    private TextField randomNR;

    @FXML
    private TextField SsnText;

    @FXML
    private TextField newPass;

    @FXML
    private TextField verifyPass;


    int randomNumber;
    String SSN;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void signOutButton (ActionEvent event) throws IOException {

        Node node = (Node)event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginSample.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void emailButton (ActionEvent event) throws IOException, SQLException {

        boolean reset = DatabaseC.getInstance().CheckUsername(SsnText.getText());

        if (reset){
            SsnText.setEditable(false);
            emailButton.setVisible(false);
            SecureRandom rand = new SecureRandom();
            randomNumber = rand.nextInt(9000)+1000;
            randomNR.setText(""+randomNumber);
            emailCode.setDisable(false);
            emailCode.setEditable(true);

        }else {
            //// error handling
        }

    }

    public void newPassButton (ActionEvent event) throws IOException {

        if (Integer.parseInt(emailCode.getText()) == randomNumber){

            Node node = (Node)event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewPass.fxml"));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        }

    }
    public void createNewPass (ActionEvent event) throws SQLException, IOException {

        if (newPass.getText().equals(verifyPass.getText())){

            DatabaseC.getInstance().newPassword(newPass.getText() );

            Node node = (Node)event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginSample.fxml"));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        }





    }






}
