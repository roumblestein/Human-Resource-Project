package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.*;

public class DatabaseC {
    private static DatabaseC instance = null;
    protected DatabaseC() throws SQLException {}


    // ------------------Password FXML----------------------
    private String SSN;


    //--------------------USER-----------------------------

    //-------------------Login-----------------------------








    public static DatabaseC getInstance () throws SQLException {
        if (instance == null) {
            instance = new DatabaseC();
        }return instance;
    }


    Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hr-project?user=root&password=root");

    Statement st = c.createStatement();

    public void input () throws SQLException {

        boolean rs = st.execute("INSERT INTO skills (skills.Skill, skills.Level, skills.Skillcategory) VALUES ('Programming', 'Newbie', 'development')");
    }



    //--------------------------LOGIN METHODS----------------------------



    //----------------------------USER METHODS-----------------------------


    //-------------------------PASSWORD METHODS-----------------------------


    public boolean CheckUsername (String user) throws SQLException {

         PreparedStatement statement = c.prepareStatement("SELECT SSN from userlogin where SSN = '"+user+"'");
        int username = 0;
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                username = rs.getInt(1);
            }
            if (String.valueOf(username).equals(user) ){
                SSN = user;
                return true;
            }else {
                return false;
            }

    }

    public void newPassword (String pass) throws SQLException {
        PreparedStatement ps = c.prepareStatement("UPDATE userlogin SET Password = ? WHERE SSN = ?");
        ps.setString(1,pass);
        ps.setString(2, SSN);
        ps.executeUpdate();
    }
    public String getEmail () throws SQLException {
        PreparedStatement statement = c.prepareStatement("SELECT email from userlogin where SSN = '"+SSN+"'");
        String username = "";
        ResultSet rs = statement.executeQuery();
        while (rs.next()){
            username = rs.getString(1);
        }

        return username;
    }












}
