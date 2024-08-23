package DSDB;
import java.util.*;

import DSDB.*;

import java.sql.*;
public class Tester {
    public static List1 tester_list = new List1();
    public static String  user;
    public static String  password;
    public static Scanner tester_scanner = new Scanner(System.in);
    public static PreparedStatement pst;
    public static String priority ;

// login Tester
    public static void login_tester() throws Exception{

        System.out.print("Enter ur name " +"\n" + Misc.BRIGHT_BACKGROUND_WHITE+Misc.ANSI_GRAY+"Name = "+ Misc.ANSI_RESET);
        user = tester_scanner.next();
        System.out.print("Enter ur password " +"\n" + Misc.BRIGHT_BACKGROUND_WHITE+Misc.ANSI_GRAY+"Password = "+ Misc.ANSI_RESET);
        password = tester_scanner.next();
        String checkSql = "select * from  tester where Tester_name ='"+user+"' and Tester_password = '"+password+"' ";
        pst = DataBase.con.prepareStatement(checkSql);
        ResultSet rs = pst.executeQuery(checkSql);
        int temp =0; 
           
            while (rs.next()) {
                temp = 1;
                System.out.println("Log in success");
                System.out.println("Welcome ");
                tester_Page();
        }
            if(temp!=1){
              System.out.println("user doesnot exist");
        }    
    }

// Tester Page
    public static void tester_Page() throws  Exception {
      while (true) {
        System.out.println("1.View Bugs");
        System.out.println("2.Add Bugs");
        System.out.println("3.Exit");
        System.out.print("\n"+Misc.ANSI_GRAY+" Enter Choice = " + Misc.ANSI_RESET);
        int choice = tester_scanner.nextInt();
        tester_scanner.nextLine();
        switch (choice) {
          case 1:
            displaytes_sorterd();
            break;
        
          case 2:
            addBug();
            break;
        
          case 3:
            System.out.println(Misc.BRIGHT_BACKGROUND_WHITE+ Misc.ANSI_GRAY+"Thank You For using it ");
            System.out.println(Misc.ANSI_RESET);
            System.exit(0);
            break;
        
            default:
            System.out.println(Misc.BRIGHT_BACKGROUND_RED+Misc.ANSI_WHITE+"Please select valid values" + Misc.ANSI_RESET);
            break;
        }
    }
}
//dipslay
    public static void displaytes_sorterd() throws Exception{

        String sql = "SELECT Task_Detail,Task_Priority FROM task ORDER BY FIELD(Task_Priority, 'High', 'Medium', 'Low');";
        pst = DataBase.con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery(sql);
          while (rs.next()) {
            String desc =rs.getString(1);
            String pr = rs.getString(2);
            tester_list.add(desc, pr);
            }
            tester_list.display();
            tester_list.head=null;
    }
// addding bug

    public static void addBug() throws  Exception {

        System.out.println("Enter Bug Details \n");
        String details = tester_scanner.nextLine();
        System.out.println("select priority \n 1.High \n 2.Medium \n 3.Low ");
        System.out.print("\n"+Misc.ANSI_GRAY+" Enter Priority = " + Misc.ANSI_RESET);
        int choice = tester_scanner.nextInt();

        switch (choice) {
          case 1:
          priority = "High";
          break;
          case 2:
          priority = "Medium";
          break;
          case 3:
          priority = "Low";
          break;
          default:
          System.out.println(Misc.BRIGHT_BACKGROUND_RED+Misc.ANSI_WHITE+"Please select valid values" + Misc.ANSI_RESET);
          addBug();
          Misc.cls();
          break;
        }
        long millis=System.currentTimeMillis();  
        java.sql.Date date = new java.sql.Date(millis);  
        String sql = "call insertintotask(?,?,?) ";
        CallableStatement cst = DataBase.con.prepareCall(sql);
        cst.setString(1, details);
        cst.setString(2, priority);
        cst.setDate(3,date);
        int rs = cst.executeUpdate();
        if(rs>0){
          System.out.println("insert success");
          tester_list.add(details, priority);
        }
        else{
          System.out.println("insert failed");
        }
        tester_scanner.nextLine();
        Misc.cls();
    }
}

