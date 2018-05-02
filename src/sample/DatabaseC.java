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

    String ssn;
    String firstName;
    String lastName;
    String email;
    String adress;

    public User getPersonalInformation(String userlogin) throws SQLException {
        PreparedStatement statement = c.prepareStatement("Select * FROM userlogin where SSN = '" + userlogin + "'");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            ssn = rs.getString(1);
            firstName = rs.getString(5);
            lastName = rs.getString(6);
            email = rs.getString(7);
            adress = rs.getString(8);
        }
        return new User(ssn, firstName, lastName, email, adress);
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
