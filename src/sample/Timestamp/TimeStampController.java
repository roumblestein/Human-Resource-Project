package sample.Timestamp;

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
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Database.DatabaseC;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;

public class TimeStampController implements Initializable {


    @FXML
    private Label time;

    @FXML
    private Label date;

    @FXML
    private TextField checkIn;

    @FXML
    private TextField checkOut;

    @FXML
    private Button checkInButton;

    @FXML
    private Button checkOutButton;

    private String workDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            Calendar cal = Calendar.getInstance();
            int second = cal.get(Calendar.SECOND);
            int minute = cal.get(Calendar.MINUTE);
            int hour = cal.get(Calendar.HOUR_OF_DAY);

            String sec = String.valueOf(second);
            String min = String.valueOf(minute);
            String h = String.valueOf(hour);
            if (second < 10) {
                sec = "0" + second;
            }
            if (minute < 10) {
                min = "0" + minute;
            }
            if (hour < 10) {
                h = "0" + hour;
            }
            time.setText(h + ":" + (min) + ":" + sec);

            String day = String.valueOf(cal.get(Calendar.DATE));
            String month = String.valueOf(cal.get(Calendar.MONTH)+1);
            int year = cal.get(Calendar.YEAR);
            if (cal.get(Calendar.DATE) < 10){
                day = "0"+day;
            }if (cal.get(Calendar.MONTH) < 10){
                month = "0"+month;
            }

            date.setText(day+ "-"+month+"-"+year);

        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        Calendar calendar = Calendar.getInstance();
        String day = String.valueOf(calendar.get(Calendar.DATE));
        String month = String.valueOf(calendar.get(Calendar.MONTH)+1);
        int year = calendar.get(Calendar.YEAR);
        if (calendar.get(Calendar.DATE) < 10){
            day = "0"+day;
        }if (calendar.get(Calendar.MONTH) < 10){
            month = "0"+month;
        }

        workDate = year+ "-"+month+"-"+day;

        try {
            TimeStamp newTimeStamp = DatabaseC.getInstance().getWorkTimes(workDate);
            if (DatabaseC.getInstance().checkedIn(workDate)){
                checkIn.setText(newTimeStamp.startTime);
            }else {
                checkIn.setPromptText(newTimeStamp.startTime);
            }if (DatabaseC.getInstance().workDayOver(workDate)){
                checkOut.setText(newTimeStamp.stopTime);
            }else {
                checkOut.setPromptText(newTimeStamp.stopTime);
            }
            if (DatabaseC.getInstance().checkedIn(workDate)){
                checkInButton.setDisable(true);
            }if (DatabaseC.getInstance().workDayOver(workDate)){
                checkOutButton.setDisable(true);
            }if (!DatabaseC.getInstance().checkedIn(workDate)){
                checkOutButton.setDisable(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void signOutButton(ActionEvent event) throws IOException {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/User/UserScreen.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }public void checkIn(ActionEvent event) throws IOException {


        try {
            DatabaseC.getInstance().workingExtra(workDate);
            DatabaseC.getInstance().checkIn(time.getText(), workDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }checkIn.setText(time.getText());
        checkInButton.setDisable(true);
        checkOutButton.setDisable(false);


    }
    public void checkout(ActionEvent event) throws IOException {
        checkOut.setText(time.getText());
        checkOutButton.setDisable(true);
        try {
            DatabaseC.getInstance().checkOut(time.getText(), workDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}





