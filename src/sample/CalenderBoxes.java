package sample;

public class CalenderBoxes {

    private boolean haveWorked ;
    private String day;
    private String month;
    private String startTime;
    private String Stoptime;

    public CalenderBoxes(boolean haveWorked, String day, String month, String startTime, String stoptime) {
        this.haveWorked = haveWorked;
        this.day = day;
        this.month = month;
        this.startTime = startTime;
        Stoptime = stoptime;
    }

    public boolean isHaveWorked() {
        return haveWorked;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getStoptime() {
        return Stoptime;
    }

    public void setHaveWorked(boolean haveWorked) {
        this.haveWorked = haveWorked;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setStoptime(String stoptime) {
        Stoptime = stoptime;
    }
}
