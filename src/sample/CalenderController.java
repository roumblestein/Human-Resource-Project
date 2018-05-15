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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
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
    private Text day1,day2,day3,day4,day5,day6,day7,day8,day9,day10,day11,day12,day13,day14,day15,day16,day17,day18,day19,day20,day21,day22,day23,day24,day25,day26,day27,day28,day29,day30,day31,day32,day33,day34,day35;

    @FXML
    private Text text1,text2,text3,text4,text5,text6,text7,text8,text9,text10,text11,text12,text13,text14,text15,text16,text17,text18,text19,text20,text21,text22,text23,text24,text25,text26,text27,text28,text29,text30,text31,text32,text33,text34,text35;

    @FXML
    private Rectangle box1,box2,box3,box4,box5,box6,box7,box8,box9,box10,box11,box12,box13,box14,box15,box16,box17,box18,box19,box20,box21,box22,box23,box24,box25,box26,box27,box28,box29,box30,box31,box32,box33,box34,box35;

    @FXML
    private Label currentMonth;

    @FXML
    private Button previousMonth;

    @FXML
    private Button nextMonth;

    private ArrayList<Rectangle> boxArray = new ArrayList<>();
    private ArrayList<Text> textArray = new ArrayList<>();
    private ArrayList<Text> workTimes = new ArrayList<>();
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
        try {
            changeMonth();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void signOutButton(ActionEvent event) throws IOException {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserScreen.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }public void decreaseMonth(ActionEvent event) throws IOException, SQLException {
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
    }public void increaseMonth(ActionEvent event) throws IOException, SQLException {
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
        boxArray.add(box1);
        boxArray.add(box2);
        boxArray.add(box3);
        boxArray.add(box4);
        boxArray.add(box5);
        boxArray.add(box6);
        boxArray.add(box7);
        boxArray.add(box8);
        boxArray.add(box9);
        boxArray.add(box10);
        boxArray.add(box11);
        boxArray.add(box12);
        boxArray.add(box13);
        boxArray.add(box14);
        boxArray.add(box15);
        boxArray.add(box16);
        boxArray.add(box17);
        boxArray.add(box18);
        boxArray.add(box19);
        boxArray.add(box20);
        boxArray.add(box21);
        boxArray.add(box22);
        boxArray.add(box23);
        boxArray.add(box24);
        boxArray.add(box25);
        boxArray.add(box26);
        boxArray.add(box27);
        boxArray.add(box28);
        boxArray.add(box29);
        boxArray.add(box30);
        boxArray.add(box31);
        boxArray.add(box32);
        boxArray.add(box33);
        boxArray.add(box34);
        boxArray.add(box35);
        workTimes.add(text1);
        workTimes.add(text2);
        workTimes.add(text3);
        workTimes.add(text4);
        workTimes.add(text5);
        workTimes.add(text6);
        workTimes.add(text7);
        workTimes.add(text8);
        workTimes.add(text9);
        workTimes.add(text10);
        workTimes.add(text11);
        workTimes.add(text12);
        workTimes.add(text13);
        workTimes.add(text14);
        workTimes.add(text15);
        workTimes.add(text16);
        workTimes.add(text17);
        workTimes.add(text18);
        workTimes.add(text19);
        workTimes.add(text20);
        workTimes.add(text21);
        workTimes.add(text22);
        workTimes.add(text23);
        workTimes.add(text24);
        workTimes.add(text25);
        workTimes.add(text26);
        workTimes.add(text27);
        workTimes.add(text28);
        workTimes.add(text29);
        workTimes.add(text30);
        workTimes.add(text31);
        workTimes.add(text32);
        workTimes.add(text33);
        workTimes.add(text34);
        workTimes.add(text35);
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

    }public void changeMonth () throws SQLException {

        daysInMonth();
        day = 1;
        dayNextMonth = 1;
        previousDaycountdown = whichDay;
        String thisMonth = String.valueOf(month);
        if (month<10){
            thisMonth = "0"+(month+1);
        }
        ArrayList<CalenderBoxes> calenderBoxes = DatabaseC.getInstance().workingDay(thisMonth);
        int startIndex = 0;
        for (int i = 0 ; i < textArray.size(); i++){
            if (i < whichDay){
                workTimes.get(i).setText("");
                int pre = dayLastMonth-previousDaycountdown+1;
                textArray.get(i).setText(""+pre);
                boxArray.get(i).setFill(Paint.valueOf("#d0d0d0"));
                previousDaycountdown--;
            }else if (day>lastDay){
                textArray.get(i).setText(""+dayNextMonth);
                boxArray.get(i).setFill(Paint.valueOf("#d0d0d0"));
                workTimes.get(i).setText("");

                dayNextMonth++;
            }else{
                textArray.get(i).setText(""+(day));
                boxArray.get(i).setFill(Paint.valueOf("#ffffff"));
                workTimes.get(i).setText("");
                try {
                    if (calenderBoxes.get(startIndex).getDay().equals(String.valueOf(day))) {
                        boxArray.get(i).setFill(Paint.valueOf("#00ff19"));
                        workTimes.get(i).setText(calenderBoxes.get(startIndex).getStartTime()+"\n\n"+calenderBoxes.get(startIndex).getStoptime());

                        startIndex++;
                    }
                }catch (IndexOutOfBoundsException e){

                }

                day++;
            }
        }
    }



}





