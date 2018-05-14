package sample;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.xml.transform.Result;

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

    String ssn, firstName, lastName, email, adress, phone1, idDepartment, password, access;

    public User getPersonalInformation(String userlogin) throws SQLException{
        PreparedStatement personalInformation = c.prepareStatement("Select * FROM userlogin, `personal phone` where SSN = '"+userlogin+"' AND SSN = userlogin_SSN");
        ResultSet rs = personalInformation.executeQuery();
        while(rs.next()){
            ssn = rs.getString("SSN");
            firstName = rs.getString("Name");
            lastName = rs.getString("Lastname");
            email = rs.getString("Email");
            adress = rs.getString("Adress");
            phone1 = rs.getString("PhoneNr");
            idDepartment = rs.getString("idDepartment");
            password = rs.getString("Password");
            access = rs.getString("Access");
        }
        return new User(ssn, firstName, lastName, email, adress, phone1, idDepartment, password,access);
    }

    String salary, employment, status, employmentDate, lastEmploymentDate;
    public Employment getEmploymentInformation(String userlogin) throws SQLException{
        PreparedStatement employmentInformation = c.prepareStatement("Select * from employment where employment.userlogin_SSN = '"+userlogin+"'");
        ResultSet bs = employmentInformation.executeQuery();
        while(bs.next()){
            salary = bs.getString("Salary");
            employment = bs.getString("Employment");
            status = bs.getString("Status");
            employmentDate = bs.getString("EmploymentDate");
            lastEmploymentDate = bs.getString("LastEmploymentDate");
        }
        return new Employment(salary, employment, status, employmentDate, lastEmploymentDate);
    }

    String skillCategory, skill, level, experience, performance;
    public Skills getSkills(String userlogin) throws SQLException{

        PreparedStatement employmentInformation = c.prepareStatement("Select Skillcategory, Skill, Level, Experience, Performance from skills, userlogin_has_skills where userlogin_has_skills.userlogin_SSN = '"+userlogin+"'" +
                "and Skills.idSkill = userlogin_has_skills.idSkill");
        ResultSet bs = employmentInformation.executeQuery();
        while(bs.next()){
            skillCategory = bs.getString("Skillcategory");
            skill = bs.getString("Skill");
            level = bs.getString("Level");
            experience = bs.getString("Experience");
            performance = bs.getString("Performance");
        }
        return new Skills(skillCategory,skill,level,experience,performance);
    }

    public ArrayList<Contacts> getContacts() throws SQLException{
        ArrayList<Contacts> contacts = new ArrayList<>();
        PreparedStatement users = c.prepareStatement("Select Name, Lastname, Email, PhoneNr, SSN from userlogin, `personal phone` where userlogin_SSN = SSN");
        ResultSet rs = users.executeQuery();
        while(rs.next()){
            contacts.add(new Contacts(rs.getString("Name"), rs.getString("Lastname"), rs.getString("Email"), rs.getString("PhoneNr"), rs.getString("SSN")));
        }
        return contacts;
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

    public int totalHours (String month) throws SQLException {
        PreparedStatement statement = c.prepareStatement("SELECT  Start, Stop from timestamp where userlogin_SSN = '"+SSN+ "' AND `Working day` LIKE '2018-"+month+"-%' order by `Working day` +1");
        ResultSet rs = statement.executeQuery();

        String startTime = "";
        String stopTime = "";
        int hours = 0;
        int totalHour = 0;
        while (rs.next()) {
            startTime = rs.getString(1);
            stopTime = rs.getString(2);

            hours = Integer.parseInt(stopTime.substring(0,2)) - Integer.parseInt(startTime.substring(0,2));
            totalHour += hours;
        }

        return totalHour;
    }




    //--------------------------ADMIN METHODS-------------------------------

    public void addEmployee(User user, Employment employment, Skills skills) throws SQLException{

        PreparedStatement addPersonalInformation = c.prepareStatement("Insert into userlogin" +
                "(SSN, idDepartment, Password, Access, Name, Lastname, Email, Adress) values" +
                "(?,?,?,?,?,?,?,?)");
        addPersonalInformation.setString(1,user.getSsn());
        addPersonalInformation.setString(2,user.getIdDepartment());
        addPersonalInformation.setString(3,user.getPassword());
        addPersonalInformation.setString(4,user.getAccess());
        addPersonalInformation.setString(5,user.getName());
        addPersonalInformation.setString(6,user.getLastName());
        addPersonalInformation.setString(7,user.getEmail());
        addPersonalInformation.setString(8,user.getAddress());

        addPersonalInformation.executeUpdate();
        addPersonalInformation.close();

        PreparedStatement addEmploymentInformation = c.prepareStatement("Insert into employment" +
                "(userlogin_SSN, Salary, Employment, Status, EmploymentDate, LastEmploymentDate) values" +
                "(?,?,?,?,?,?)");
        addEmploymentInformation.setString(1, user.getSsn());
        addEmploymentInformation.setString(2, employment.getSalary());
        addEmploymentInformation.setString(3, employment.getEmployment());
        addEmploymentInformation.setString(4, employment.getStatus());
        addEmploymentInformation.setString(5, employment.getEmploymentDate());
        addEmploymentInformation.setString(6, employment.getLastEmploymentDate());

        addEmploymentInformation.executeUpdate();
        addEmploymentInformation.close();

        PreparedStatement addSkillsInformation = c.prepareStatement("Insert into userlogin_has_skills" +
                "(userlogin_SSN, idSkill, Experience, Performance)values" +
                "(?,?,?,?)");
        addSkillsInformation.setString(1, user.getSsn());
        addSkillsInformation.setString(2, skills.getSkillCategory());
        addSkillsInformation.setString(3, skills.getLevel());
        addSkillsInformation.setString(4, skills.getPerformance());

        addSkillsInformation.executeUpdate();
        addSkillsInformation.close();


        PreparedStatement addPhoneInformation = c.prepareStatement("Insert into `personal phone`" +
                "(userlogin_SSN, PhoneNr) values (?,?)");

        addPhoneInformation.setString(1, user.getSsn());
        addPhoneInformation.setString(2, user.getPhone1());

        addPhoneInformation.executeUpdate();
        addPersonalInformation.close();
    }

    public void removeEmployee(String user) throws SQLException{
        PreparedStatement a = c.prepareStatement("Delete from userlogin_has_skills where userlogin_SSN ='"+user+"'");
        PreparedStatement a1 = c.prepareStatement("Delete from `personal phone` where userlogin_SSN = '"+user+"'");
        PreparedStatement b = c.prepareStatement("Delete from userlogin where SSN = '"+user+"'");
        a.executeUpdate();
        a.close();
        a1.executeUpdate();
        a1.close();
        b.executeUpdate();
        b.close();
    }

    public void editEmployeeInformation(User user, Employment emp) throws SQLException{
        PreparedStatement update = c.prepareStatement("Update userlogin, `personal phone`, employment Set Name = ?, Lastname = ?, Email = ?, " +
                "PhoneNr = ?, Adress = ?, Salary = ?, EmploymentDate = ?, LastEmploymentDate = ?, Employment = ?, Status = ?, Password = ?, " +
                "Access = ? where userlogin.SSN = '"+user.getSsn()+"' and userlogin.SSN = employment.userlogin_SSN " +
                "and userlogin.SSN = `personal phone`.userlogin_SSN and userlogin.SSN = employment.userlogin_SSN");

        update.setString(1, user.getName());
        update.setString(2, user.getLastName());
        update.setString(3, user.getEmail());
        update.setString(4, user.getPhone1());
        update.setString(5, user.getAddress());

        update.setString(6, emp.getSalary());
        update.setString(7, emp.getEmployment());
        update.setString(8, emp.getLastEmploymentDate());
        update.setString(9, emp.getEmployment());
        update.setString(10, emp.getStatus());

        update.setString(11, user.getPassword());
        update.setString(12, user.getAccess());

        update.executeUpdate();
        update.close();
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

    public boolean checkAccess(String user) throws SQLException{
        PreparedStatement statement = c.prepareStatement("Select SSN, Access from userlogin where SSN = '"+user+"'");
        ResultSet rs = statement.executeQuery();
        String access = "";
        while(rs.next()){
            access = rs.getString("Access");
        }
        if(access.equals("Admin")){
            return true;
        }else{
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

                CalenderBoxes calenderBoxes = new CalenderBoxes(haveWorked, getDay, month, startTime, stopTime);

                calenderBox.add(calenderBoxes);
            }
            return calenderBox;
        }








}
