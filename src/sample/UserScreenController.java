package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javax.xml.soap.Text;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // DatabaseC.getInstance().getPersonalInformation();
    }

    @FXML private void update() throws IOException{



    }
}
