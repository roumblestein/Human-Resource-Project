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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;

public class CalenderController implements Initializable {

    @FXML
    private Text day1, day2, day3, day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15, day16, day17, day18, day19, day20, day21, day22, day23, day24, day25, day26, day27, day28, day29, day30, day31, day32, day33, day34, day35;

    @FXML
    private Label currentMonth;

    @FXML
    private Button previousMonth;

    @FXML
    private Button nextMonth;

    private ArrayList<Text> textArray = new ArrayList<>();
    private int month;
    private int whichDay;
    private int lastDay;
    private int day = 1;
    private int dayNextMonth = 1;
    private int dayLastMonth;
    private int previousDaycountdown;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        getArray();

        Calendar cal = Calendar.getInstance();
        currentMonth.setText(cal.getDisplayName(Calendar.MONTH,Calendar.LONG, Locale.ENGLISH));
        month = cal.get(Calendar.MONTH);
        lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        dayLastMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        daysInMonth();
        previousDaycountdown = whichDay;
        System.out.println(dayLastMonth + " "+ whichDay);
        for (int i = 0 ; i < textArray.size(); i++){
            System.out.println(dayNextMonth);
            if (i < whichDay){
                int pre = dayLastMonth-previousDaycountdown+1;
                System.out.println(pre +" " + dayLastMonth +" "+ previousDaycountdown);
                textArray.get(i).setText(""+pre);
                previousDaycountdown--;
            }else if (day>lastDay){
                textArray.get(i).setText(""+dayNextMonth);
                dayNextMonth++;
                System.out.println(dayNextMonth);
            }else{
                textArray.get(i).setText(""+(day));
                day++;
            }
        }

    }

    public void signOutButton(ActionEvent event) throws IOException {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserScreen.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }public void decreaseMonth(ActionEvent event) throws IOException {
        nextMonth.setDisable(false);
        month--;
        if (month == 0){
            currentMonth.setText("January");
            previousMonth.setDisable(true);
        }else if (month == 1){
            currentMonth.setText("February");
        }else if (month == 2){
            currentMonth.setText("March");
        }else if (month == 3){
            currentMonth.setText("April");
        }else if (month == 4){
            currentMonth.setText("May");
        }else if (month == 5){
            currentMonth.setText("June");
        }else if (month == 6){
            currentMonth.setText("July");
        }else if (month == 7){
            currentMonth.setText("Augusti");
        }else if (month == 8){
            currentMonth.setText("September");
        }else if (month == 9){
            currentMonth.setText("October");
        }else if (month == 10){
            currentMonth.setText("November");
        }else if (month == 11){
            currentMonth.setText("December");
        }changeMonth();
    }public void increaseMonth(ActionEvent event) throws IOException {
        previousMonth.setDisable(false);
        month++;
        if (month == 0){
            currentMonth.setText("January");
        }else if (month == 1){
            currentMonth.setText("February");
        }else if (month == 2){
            currentMonth.setText("March");
        }else if (month == 3){
            currentMonth.setText("April");
        }else if (month == 4){
            currentMonth.setText("May");
        }else if (month == 5){
            currentMonth.setText("June");
        }else if (month == 6){
            currentMonth.setText("July");
        }else if (month == 7){
            currentMonth.setText("Augusti");
        }else if (month == 8){
            currentMonth.setText("September");
        }else if (month == 9){
            currentMonth.setText("October");
        }else if (month == 10){
            currentMonth.setText("November");
        }else if (month == 11){
            currentMonth.setText("December");
            nextMonth.setDisable(true);
        }
        changeMonth();


    }public void getArray (){
        textArray.add(day1);
        textArray.add(day2);
        textArray.add(day3);
        textArray.add(day4);
        textArray.add(day5);
        textArray.add(day6);
        textArray.add(day7);
        textArray.add(day8);
        textArray.add(day9);
        textArray.add(day10);
        textArray.add(day11);
        textArray.add(day12);
        textArray.add(day13);
        textArray.add(day14);
        textArray.add(day15);
        textArray.add(day16);
        textArray.add(day17);
        textArray.add(day18);
        textArray.add(day19);
        textArray.add(day20);
        textArray.add(day21);
        textArray.add(day22);
        textArray.add(day23);
        textArray.add(day24);
        textArray.add(day25);
        textArray.add(day26);
        textArray.add(day27);
        textArray.add(day28);
        textArray.add(day29);
        textArray.add(day30);
        textArray.add(day31);
        textArray.add(day32);
        textArray.add(day33);
        textArray.add(day34);
        textArray.add(day35);
    }
    public void daysInMonth (){
        if (month == 0){
            dayLastMonth = 31;
            lastDay = 31;
            whichDay = 1;
        } if (month == 1){
            dayLastMonth = 31;
            lastDay = 28;
            whichDay = 4;
        } if (month == 2){
            dayLastMonth = 28;
            lastDay = 31;
            whichDay = 4;
        } if (month == 3){
            dayLastMonth = 31;
            lastDay = 30;
            whichDay = 0;
        } if (month == 4){
            dayLastMonth = 30;
            lastDay = 31;
            whichDay = 2;
        } if (month == 5){
            dayLastMonth = 31;
            lastDay = 30;
            whichDay = 5;
        } if (month == 6){
            dayLastMonth = 30;
            lastDay = 31;
            whichDay = 0;
        } if (month == 7){
            dayLastMonth = 31;
            lastDay = 31;
            whichDay = 3;
        } if (month == 8){
            dayLastMonth = 31;
            lastDay = 30;
            whichDay = 6;
        } if (month == 9){
            dayLastMonth = 30;
            lastDay = 31;
            whichDay = 1;
        } if (month == 10){
            dayLastMonth = 31;
            lastDay = 30;
            whichDay = 4;
        } if (month == 11){
            dayLastMonth = 30;
            lastDay = 31;
            whichDay = 6;
        }

    }public void changeMonth (){

        daysInMonth();
        day = 1;
        dayNextMonth = 1;
        previousDaycountdown = whichDay;
        for (int i = 0 ; i < textArray.size(); i++){
            if (i < whichDay){
                int pre = dayLastMonth-previousDaycountdown+1;
                textArray.get(i).setText(""+pre);
                previousDaycountdown--;
            }else if (day>lastDay){
                textArray.get(i).setText(""+dayNextMonth);
                dayNextMonth++;
            }else{
                textArray.get(i).setText(""+(day));
                day++;
            }
        }
    }



}





