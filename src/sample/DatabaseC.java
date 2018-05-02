package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.*;

public class DatabaseC {
    private static DatabaseC instance = null;

    protected DatabaseC() throws SQLException {
    }


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
}
