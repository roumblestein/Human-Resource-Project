package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class Login implements Initializable {
    @FXML
    private TextField SsnText;
    @FXML
    private PasswordField PasswordText;
    @FXML
    private Label ssnw;
    @FXML
    private CheckBox checkBox;
    private String rememberUser;


    public static String currentUserSsn = "";


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            checkRememberMe();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loginButton(ActionEvent event) throws IOException, SQLException {
        writeRememberMe();
        try {
            if (SsnText.getText().isEmpty() || PasswordText.getText().isEmpty()) {
                ssnw.setText("Enter details in empty fields!");
                Toolkit.getDefaultToolkit().beep();
            } else if (DatabaseC.getInstance().CheckUsername(SsnText.getText())) {

                if (DatabaseC.getInstance().CheckPassword(PasswordText.getText())) {

                    if(!DatabaseC.getInstance().checkAccess(SsnText.getText())){
                        currentUserSsn = SsnText.getText();
                        Node node = (Node) event.getSource();
                        Stage stage = (Stage) node.getScene().getWindow();

                        FXMLLoader FxmlLoader = new FXMLLoader(getClass().getResource("UserScreen.fxml"));
                        Parent root = FxmlLoader.load();

                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                        dialog.setTitle("INFORMATION");
                        dialog.setHeaderText("Don't forget the Time Stamp");
                        dialog.setContentText("Always use the Time Stamp when you start working !");
                        dialog.showAndWait();

                    }else if(DatabaseC.getInstance().checkAccess(SsnText.getText())){
                        currentUserSsn = SsnText.getText();
                        Node node = (Node) event.getSource();
                        Stage stage = (Stage) node.getScene().getWindow();

                        FXMLLoader FxmlLoader = new FXMLLoader(getClass().getResource("AdminScreen.fxml"));
                        Parent root = FxmlLoader.load();

                        Scene scene = new Scene(root);

                        stage.setScene(scene);
                    }

                } else if (!DatabaseC.getInstance().CheckPassword(PasswordText.getText())) {
                    ssnw.setText("Password is incorrect!");
                    Toolkit.getDefaultToolkit().beep();
                }
            } else if (!DatabaseC.getInstance().CheckUsername(SsnText.getText())) {
                ssnw.setText("Write in this format YYMMDD-XXXX");
                Toolkit.getDefaultToolkit().beep();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void forgotButton(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("forgotPass.fxml"));
        stage.setTitle("New password");
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    private void exitButton(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes!");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No!");
        alert.setTitle("Exit");
        alert.setHeaderText("Do you want to exit ?");
        alert.setContentText(" ");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(1);
        }
    }

    /*Checks if the RememberMe checkbox is ticked  */

    @FXML
    private void handleCheckBox(ActionEvent event) {
        if (event.getSource() instanceof CheckBox) {
            checkBox = (CheckBox) event.getSource();
            if (checkBox.isSelected()) {
                rememberUser = "true";
            } else {
                rememberUser = "false";
            }
        }
    }

    /*Saves Ssn in a file*/


    private void writeRememberMe() throws IOException {
        File file = new File("user.txt");


        Path path = Paths.get("user.txt");

        ArrayList rememberArray = new ArrayList();
        rememberArray.add(rememberUser);
        rememberArray.add(SsnText.getText());

        Files.write(path, rememberArray, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    /*Checks if file exists */

    private void checkRememberMe() throws IOException {
        File file = new File("user.txt");
        if (file.exists()) {

            BufferedReader bufferedReader = new BufferedReader(new FileReader("user.txt"));

            if (bufferedReader.readLine().equals("true")) {
                checkBox.fire();
                String username = new String(bufferedReader.readLine());
                SsnText.setText(username);

            }
            bufferedReader.close();
        }
    }

}