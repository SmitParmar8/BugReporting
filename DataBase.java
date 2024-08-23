package DSDB;
import java.util.*;
import java.sql.*;

public class DataBase {

    String dburl = "jdbc:mysql://localhost:3306/project";
    String dbuser = "root";
    String dbpass = "";
    public static Connection con;
    public DataBase() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(dburl, dbuser, dbpass);
        System.out.println("success");
        
    }

}
