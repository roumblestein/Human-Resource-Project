package sample.Timestamp;

import java.util.Date;

public class TimeStamp {

    String startTime;
    String stopTime;
    String date;
    int checkedIn;

    public TimeStamp(String startTime, String stopTime, String date, int checkedIn) {
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.date = date;
        this.checkedIn = checkedIn;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public String getDate() {
        return date;
    }
}
