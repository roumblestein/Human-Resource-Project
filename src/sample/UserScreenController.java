package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import javax.xml.soap.Text;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by Shpat on 2018-04-25.
 */
public class UserScreenController implements Initializable {

    @FXML private TextField employeeCodeTextField;
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField ssnTextField;
    @FXML private TextField emailTextFiled;
    @FXML private TextField phoneOneTextField;
    @FXML private TextField phoneTwoTextField;
    @FXML private TextField addressTextField;
    @FXML private Button updateButton;

    User currentUser;
    String currentSSN = "121212-3333";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       try{
           currentUser = DatabaseC.getInstance().getPersonalInformation(currentSSN);
           ssnTextField.setText(currentUser.getSsn());
           firstNameTextField.setText(currentUser.getName());
           lastNameTextField.setText(currentUser.getLastName());
           emailTextFiled.setText(currentUser.getEmail());
           phoneOneTextField.setText(currentUser.getPhone1());
           addressTextField.setText(currentUser.getAddress());
       }catch (SQLException s){
           System.out.println("Could not connect to database");
       }catch (NullPointerException a){
           System.out.println("No info accessible");
       }
    }

    @FXML private void update() throws SQLException{
        DatabaseC.getInstance().updateInformation(currentSSN, firstNameTextField.getText(),
                lastNameTextField.getText(), emailTextFiled.getText(),addressTextField.getText(), phoneOneTextField.getText());
        System.out.println(emailTextFiled.getText() + " " + phoneOneTextField.getText());
    }
}
