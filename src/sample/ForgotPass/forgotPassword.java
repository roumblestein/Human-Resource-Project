package sample.ForgotPass;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.DatabaseC;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.*;
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
    @FXML
    private Label warningForgetP;
    @FXML
    private Label warningText;

    private int randomNumber;
    private String password;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void signOutButton(ActionEvent event) throws IOException {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/Login/LoginSample.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.close();
    }

    public void emailButton(ActionEvent event) throws IOException, SQLException, MessagingException {
        if (SsnText.getText().isEmpty()) {
            warningForgetP.setText("Enter details in empty fields!");
            Toolkit.getDefaultToolkit().beep();
        }
        boolean reset = DatabaseC.getInstance().CheckUsername(SsnText.getText());
        if (reset) {
            SsnText.setEditable(false);
            emailButton.setVisible(false);
            SecureRandom rand = new SecureRandom();
            randomNumber = rand.nextInt(9000) + 1000;

            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setTitle("INFORMATION");
            dialog.setHeaderText("4 digit code ");
            dialog.setContentText("You have gotten a 4 digit code on your email." +
                    " " +
                    "Enter it below!");
            dialog.showAndWait();

            String to = DatabaseC.getInstance().getEmail();
            String from = "humanresourceprojecthkr@gmail.com";
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", 587);
            Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("humanresourceprojecthkr@gmail.com", "hrproject");

                }
            });

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Reset Password");
            message.setText("To Create a new password please input this code into the application!  " + randomNumber);
            Transport.send(message);

            emailCode.setDisable(false);
            emailCode.setEditable(true);
        } else {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("ERROR");
            dialog.setHeaderText("Wrong SSN ");
            dialog.setContentText("Please enter a valid SSN");
            dialog.show();
        }
    }

    public void newPassButton(ActionEvent event) throws IOException {

        if (Integer.parseInt(emailCode.getText()) == randomNumber) {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/ForgotPass/NewPass.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);


        }
    }

    public void createNewPass(ActionEvent event) throws SQLException, IOException {

        password = newPass.getText();


       if (!newPass.getText().isEmpty() || !verifyPass.getText().isEmpty()){

           if (newPass.getText().equals(verifyPass.getText())) {

               if (password.length() > 5) {

        if (checkPass()) {
                DatabaseC.getInstance().newPassword(newPass.getText());

                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/Login/LoginSample.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.close();
            }else if (!checkPass()){

            warningText.setText("Password must consist of:\n" +
                    "Digits\n" +
                    "Uppercase letter\n" +
                    "and lowercase letters");
        }
               }else {
                   warningText.setText("Password must consist of more than 5 characters!");
               }
           }else {
            warningText.setText("Passwords doesn't match!");
           }
       }else{
           warningText.setText("Fields are empty! please fill them out");
       }

    }

    public boolean checkPass() {
            boolean passwordSafety = false;
            password = newPass.getText();
                boolean hasNum = false;
                boolean hasCap = false;
                boolean hasLow = false;
                char c;
                for (int i = 0; i < password.length(); i++)

                {
                    c = password.charAt(i);
                    if (Character.isDigit(c)) {
                        hasNum = true;
                    } else if (Character.isUpperCase(c)) {
                        hasCap = true;
                    } else if (Character.isLowerCase(c)) {
                        hasLow = true;
                    }
                    if (hasNum && hasCap && hasLow) {
                        passwordSafety = true;
                    }
                }
              return passwordSafety;
            }
        }




