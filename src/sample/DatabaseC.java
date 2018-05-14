package sample;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseC {
    private static DatabaseC instance = null;

    protected DatabaseC() throws SQLException {
    }

    // ------------------CALENDER----------------------
    ArrayList<CalenderBoxes> calenderBox;
    // ------------------Password FXML----------------------
    private String SSN;
    private String Password;

    //--------------------USER-----------------------------

    //-------------------Login-----------------------------


    public static DatabaseC getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseC();
        }
        return instance;
    }


    Connection c = DriverManager.getConnection("jdbc:mysql://den1.mysql2.gear.host/hrproject?user=hrproject&password=Vb61C16K46_~");

    Statement st = c.createStatement();

    public void input() throws SQLException {

        boolean rs = st.execute("INSERT INTO skills (skills.Skill, skills.Level, skills.Skillcategory) VALUES ('Programming', 'Newbie', 'development')");
    }


    //--------------------------LOGIN METHODS----------------------------


    //----------------------------USER METHODS-----------------------------

    String ssn, firstName, lastName, email, adress, phone1;


    public User getPersonalInformation(String userlogin) throws SQLException{
        PreparedStatement statement = c.prepareStatement("Select * FROM userlogin, `personal phone` where SSN = '"+userlogin+"' AND SSN = userlogin_SSN");
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
            ssn = rs.getString("SSN");
            firstName = rs.getString("Name");
            lastName = rs.getString("Lastname");
            email = rs.getString("Email");
            adress = rs.getString("Adress");
            phone1 = rs.getString("PhoneNr");

        }
        return new User(ssn, firstName, lastName, email, adress, phone1);
    }

    public void updateInformation(String ssn, String firstName, String lastName, String email, String adress, String phone1) throws SQLException{
        PreparedStatement a = c.prepareStatement("UPDATE userlogin, `personal phone` SET Name = ?, " +
                "Lastname = ?, Email = ?, PhoneNr = ?, Adress = ? WHERE SSN = '"+ssn+"' AND SSN = userlogin_SSN");
        a.setString(1, firstName);
        a.setString(2, lastName);
        a.setString(3, email);
        a.setString(4, phone1);
        a.setString(5, adress);

        a.executeUpdate();
        a.close();
    }


    //-------------------------PASSWORD METHODS-----------------------------


    public boolean CheckUsername(String user) throws SQLException {

        PreparedStatement statement = c.prepareStatement("SELECT SSN from userlogin where SSN = '" + user + "'");
        String username = "";
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            username = rs.getString(1);
        }
        if (username.equals(user)) {
            SSN = user;
            return true;
        } else {
            return false;
        }

    }

    public void newPassword(String pass) throws SQLException {
        PreparedStatement ps = c.prepareStatement("UPDATE userlogin SET Password = ? WHERE SSN = ?");
        ps.setString(1, pass);
        ps.setString(2, SSN);
        ps.executeUpdate();
    }

    public String getEmail() throws SQLException {
        PreparedStatement statement = c.prepareStatement("SELECT email from userlogin where SSN = '" + SSN + "'");
        String username = "";
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            username = rs.getString(1);
        }

        return username;
    }

    public boolean CheckPassword(String user) throws SQLException {

        PreparedStatement statement = c.prepareStatement("SELECT Password from userlogin where Password = '" + user + "'");
        String password = "";
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            password = rs.getString(1);
        }
        if (password.equals(user)) {
            Password = user;
            return true;
        } else {
            return false;
        }


    }


    //--------------------------------TIMESTAMP--------------------------------


    public void workingExtra (String date) throws SQLException {
        PreparedStatement statement = c.prepareStatement("SELECT `Working day`  from timestamp where userlogin_SSN = '"+SSN+ "' AND `Working day` = '"+date+"'");
        ResultSet rs = statement.executeQuery();
        String dateCheck =  "";

        while (rs.next()){
            dateCheck = rs.getString(1);
        }
        if (dateCheck.isEmpty()){
            PreparedStatement st = c.prepareStatement("INSERT INTO timestamp (userlogin_SSN, Start, Stop, `Working day`, checkedIn, workDayOver) VALUES ('"+SSN+"',null,null,'"+date+"', 0,0)");
            st.execute();
        }


    }









    public TimeStamp getWorkTimes (String date) throws SQLException {

        PreparedStatement statement = c.prepareStatement("SELECT Start, stop, checkedIn  from timestamp where userlogin_SSN = '"+SSN+ "' AND `Working day` = '"+date+"'");
        ResultSet rs = statement.executeQuery();
        String start = "";
        String stop = "";
        int isChecked =  0;

        while (rs.next()) {
            start = rs.getString(1);
            stop = rs.getString(2);
            isChecked = rs.getInt(3);
        }
        TimeStamp timeStamp = new TimeStamp(start, stop, date, isChecked);

        return timeStamp;
    }
    public void checkIn (String start, String date) throws SQLException {
        PreparedStatement statement = c.prepareStatement("UPDATE timestamp SET start = ?, checkedIn = ? WHERE userlogin_SSN ='"+SSN+"' AND `Working day` = '"+date+"'");

        statement.setString(1, start);
        statement.setInt(2, 1);
        statement.executeUpdate();



    }
    public void checkOut (String stop, String date) throws SQLException {
        PreparedStatement statement = c.prepareStatement("UPDATE timestamp SET stop = ?, workDayOver = ? WHERE userlogin_SSN ='"+SSN+"' AND `Working day` = '"+date+"'");

        statement.setString(1, stop);
        statement.setInt(2, 1);
        statement.executeUpdate();
    }
    public boolean checkedIn (String date) throws SQLException {
        PreparedStatement statement = c.prepareStatement("SELECT checkedIn from timestamp where userlogin_SSN = '"+SSN+ "' AND `Working day` = '"+date+"'");
        int b = 0;
        ResultSet rs = statement.executeQuery();
        while (rs.next()){
            b = rs.getInt(1);
        }
        if (b == 1){
            return true;
        }else
            return false;
    }public boolean workDayOver (String date) throws SQLException {
        PreparedStatement statement = c.prepareStatement("SELECT workDayOver from timestamp where userlogin_SSN = '"+SSN+ "' AND `Working day` = '"+date+"'");
        int b = 0;
        ResultSet rs = statement.executeQuery();
        while (rs.next()){
            b = rs.getInt(1);
        }
        if (b == 1){
            return true;
        }else
            return false;
    }



    //-----------------------------------------CALENDER-----------------------------------



        public ArrayList<CalenderBoxes> workingDay (String month) throws SQLException {
            PreparedStatement statement = c.prepareStatement("SELECT `Working day`, Stop, Start, workDayOver  from timestamp where userlogin_SSN = '"+SSN+ "' AND `Working day` LIKE '2018-"+month+"-%' order by `Working day` +1");
            ResultSet rs = statement.executeQuery();
            calenderBox = new ArrayList<>();
            String workDay = "";
            boolean haveWorked = false;
            String startTime = "";
            String stopTime = "";
            String getDay = "";

            while (rs.next()) {
                workDay = rs.getString(1);
                stopTime = rs.getString(2);
                startTime = rs.getString(3);
                haveWorked = rs.getBoolean(4);


                getDay = workDay.substring(8,10);
                if (Integer.parseInt(getDay)<10){
                    getDay = ""+Integer.parseInt(getDay);
                }

                System.out.println(workDay + startTime + stopTime + haveWorked + getDay);

                CalenderBoxes calenderBoxes = new CalenderBoxes(haveWorked, getDay, month, startTime, stopTime);

                calenderBox.add(calenderBoxes);
            }
            return calenderBox;
        }








}
