package sample;

import com.sun.xml.internal.org.jvnet.mimepull.MIMEMessage;
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

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class forgotPassword implements Initializable {


    @FXML
    private Button emailButton;

    @FXML
    private TextField emailCode;

    @FXML
    private TextField SsnText;

    @FXML
    private TextField newPass;

    @FXML
    private TextField verifyPass;


    int randomNumber;

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

    public void emailButton (ActionEvent event) throws IOException, SQLException, MessagingException {
        boolean reset = DatabaseC.getInstance().CheckUsername(SsnText.getText());
        if (reset){
            SsnText.setEditable(false);
            emailButton.setVisible(false);
            SecureRandom rand = new SecureRandom();
            randomNumber = rand.nextInt(9000)+1000;

            String to = DatabaseC.getInstance().getEmail();
            String from = "humanresourceprojecthkr@gmail.com";
            String host = "localhost";
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", 587);
            Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator(){
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("humanresourceprojecthkr@gmail.com","hrproject");
                }
            });

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Reset Password");
            message.setText("To Create a new password please input this code into the application!  "+randomNumber);
            Transport.send(message);

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
