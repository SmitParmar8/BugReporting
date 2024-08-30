package BugReporting;
import java.util.*;
import java.sql.*;

public class DataBase {

    String dburl = "jdbc:mysql://localhost:3306/project";
    String dbuser = "root";
    String dbpass = "";
    public static Connection con;
    public DataBase() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found Excepiton ");
        }
        try {
            con = DriverManager.getConnection(dburl, dbuser, dbpass);
        } catch (SQLException e) {
           System.out.println("SQL Exception");
        }

    }
}