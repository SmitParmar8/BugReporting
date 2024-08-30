package BugReporting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main2 {
    static  String dburl = "jdbc:mysql://localhost:3306/project";
    static String dbuser = "root";
    static String dbpass = "";
    public static Connection con;
    public static void main(String[] args)  {
        
        String [] opt = {"1.Log in" ,"2.Add Person" ,"3.EXIT----"};
        String [] opt2 = {"succ","inscuuc","extrreme"};
        String paddedstr = "";
        for (int i = 0; i < opt.length; i++) {
          
        }
        // System.out.println(paddedstr);
    }
}