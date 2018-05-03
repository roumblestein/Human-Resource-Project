package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Properties;
import java.util.ResourceBundle;

public class TimeStampController implements Initializable {


@FXML
private Text time;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            Calendar cal = Calendar.getInstance();
            int second = cal.get(Calendar.SECOND);
           int  minute = cal.get(Calendar.MINUTE);
            int hour = cal.get(Calendar.HOUR_OF_DAY);

            String sec = String.valueOf(second);
            String min = String.valueOf(minute);
            String h = String.valueOf(hour);
            if (second < 10){
                sec = "0"+second;
            }if (minute < 10){
                min = "0"+ minute;
            }if (hour < 10){
                h = "0"+hour;
            }
            time.setText(h + ":" + (min) + ":" + sec);
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

    }

    public void signOutButton (ActionEvent event) throws IOException {

        Node node = (Node)event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserScreen.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }





    public void createNewPass (ActionEvent event) throws SQLException, IOException {

        }
    }

