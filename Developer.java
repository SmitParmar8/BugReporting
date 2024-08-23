package DSDB;
import java.sql.*;
import java.util.*;

import DSDB.*;

public class Developer {


    public static Scanner developer_scanner = new Scanner(System.in);
    public static String  user;
    public static int   id;
    public static String  password;
    public static PreparedStatement pst;
    static List1 developerlist = new List1();
    
//Login Developer
    public static  void login_Developer() throws Exception{

        System.out.print("Enter ur name " +"\n" + Misc.BRIGHT_BACKGROUND_WHITE+Misc.ANSI_GRAY+"Name = "+ Misc.ANSI_RESET);
        user = developer_scanner.next();
        System.out.print("Enter ur password " +"\n" + Misc.BRIGHT_BACKGROUND_WHITE+Misc.ANSI_GRAY+"Password = "+ Misc.ANSI_RESET);
        password = developer_scanner.next();
        String checkSql = "select * from  developer where Developer_name ='"+user+"' and Developer_password = '"+password+"' ";
        pst = DataBase.con.prepareStatement(checkSql);
        ResultSet rs = pst.executeQuery(checkSql);
        int temp =0; 
        while (rs.next()) {
            temp = 1;
            id = rs.getInt(1);
            System.out.println("Log in success");
            developer_page();
        }     
        if(temp!=1){
            System.out.println("user doesnot exist");
        }    
    }

//developer page
    public static void developer_page() throws Exception{
       while (true) {
            System.out.println("1.View Assigned Tasks for me \n2.View Bugs \n3.Exit \n4.Home Page");
            System.out.print("\n"+Misc.ANSI_GRAY+" Enter Choice = " + Misc.ANSI_RESET);
            int choice = developer_scanner.nextInt();
            switch (choice) {
                case 1:

                    display_assignedtask();                
                    break;
            
                case 2:
                    displayall();                
                    break;
            
                case 4:
                    Page.firstPage();                
                    break;
            
                case 3:
                    System.out.println(Misc.BRIGHT_BACKGROUND_WHITE+ Misc.ANSI_GRAY+"Thank You For using it ");
                    System.out.println(Misc.ANSI_RESET);
                    Misc.cls();
                    System.exit(0);                
                    break;
            
                default:
                    System.out.println(Misc.BRIGHT_BACKGROUND_RED+Misc.ANSI_WHITE+"Please select valid values" + Misc.ANSI_RESET);
                    break;
            }
        }
    }

// display assigned task
    public static void display_assignedtask() throws Exception{
       String sql = "Select * from task_assigned where Developer_id = ?";
       pst = DataBase.con.prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            System.out.println("Task_assigned_id    Task_id    Task_detail ");
            
            System.out.println(rs.getInt(1) + "                   " + rs.getInt(3) + "      " +rs.getString(4));
            
        }
        else{
            System.out.println("no task available");
        }
        

    }

//display all method
    public static void displayall() throws Exception{
        String sql = "SELECT Task_Detail,Task_Priority FROM task ORDER BY FIELD(Task_Priority, 'High', 'Medium', 'Low');";
        pst = DataBase.con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery(sql);
          while (rs.next()) {
            String desc =rs.getString(1);
            String pr = rs.getString(2);
            developerlist.add(desc, pr);
            }
            developerlist.display();
            developerlist.head=null;
            
        }
    
    //
}

