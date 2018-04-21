package sample;

import java.sql.*;

public class DatabaseC {
    private static DatabaseC instance = null;
    protected DatabaseC() throws SQLException {

    }
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
}
