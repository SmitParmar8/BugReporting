package BugReporting;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import BugReporting.*;


public class Senior {
      
    public static List1 senior_list = new List1();
    public static String  user;
    public static String  password;
    public static Scanner senior_scanner = new Scanner(System.in);
    public static PreparedStatement pst;
    
//Login senior
    public static boolean seniorLogin() throws Exception{

        System.out.println("Enter the name of the user ");
        user = senior_scanner.next();
        System.out.println("Enter the password ");
        password = senior_scanner.next();
        String checkSql = "select * from Senior where s_name ='"+user+"' and s_pass = '"+password+"' ";
        PreparedStatement pst = DataBase.con.prepareStatement(checkSql);
        ResultSet rs = pst.executeQuery(checkSql);
        int temp =0; 
        while (rs.next()) {
                temp = 1;
                System.out.println("Log in success");
                System.out.println("Welcome ");
                
            }      
        if(temp!=1){
              System.out.println("user doesnot exist");
              Misc.cls();
              return false;
            }
        else{
              return true;
            }    
            
        }
//Addperson page
    public static void addPerson() throws Exception{
    
        Thread t = Thread.currentThread();
        System.out.println("Please enter your senior id");
        if(seniorLogin()){
            String addperson = "Select the person u want to add \n 1.Developer \n 2.Tester \n 3.Home Page\n 4 Senior Main page\n";
            while (true) {
            for(int i =0;i<addperson.length();i++)
              {
                System.out.print(addperson.charAt(i));
                t.sleep(20);
              }
            System.out.print("enter choice = ");

                int choice = senior_scanner.nextInt();
                senior_scanner.nextLine();
            switch (choice) {
              case 1:
                addDeveloper();
              break;
              case 2:
                 addTester();
              break;
              case 3:
                 new Page().firstPage();;
              break;
              case 4:
                 seniorPage();
              break;
              default:
              // System.out.println(Misc.BRIGHT_BACKGROUND_RED+Misc.ANSI_WHITE+"Please select valid values" + Misc.ANSI_RESET);
              Misc.cls();
              break;
            }  
          }
        }
      }

// add developer    
    public static void addDeveloper() throws Exception{

        System.out.println("enter the name of developer");
        String devName =senior_scanner.nextLine();

        System.out.println("enter the password");
        String devpass =senior_scanner.nextLine();
        String sql = "insert into developer(Developer_name,Developer_password) values('"+devName+"','"+devpass+"') ";
        pst =DataBase.con.prepareStatement(sql);
        int rs = pst.executeUpdate(sql);
            if(rs>0){
               System.out.println(devName+ "added successfully ");
            }
            else{
               System.out.println(devName+"is not added ");
            }
            Misc.cls();
    }

//Addtester
    public static void addTester() throws Exception{

       try {
            System.out.println("Enter the name of Tester:");
            String testerName = senior_scanner.nextLine();
            
            System.out.println("Enter the password:");
            String password = senior_scanner.nextLine();

            String sql = "INSERT INTO tester (Tester_name, tester_password) VALUES (?, ?)";
            PreparedStatement pst = DataBase.con.prepareStatement(sql);
            pst.setString(1, testerName);
            pst.setString(2, password);

            int rs = pst.executeUpdate();
            
            if (rs > 0) {
                System.out.println(testerName + " added successfully.");
            } else {
                System.out.println(testerName + " is not added.");
            }

            // Close the PreparedStatement to avoid resource leaks
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while adding the tester.");
        } finally {
          System.out.println("done");
          Misc.cls();
            // senior_scanner.close(); // Close the scanner to avoid resource leaks
        }
    }

//aad assignbug
    public static void assignBug() throws Exception{

      System.out.println("------------------------Developers who are not Assigned any Bugs------------------------");
      String sql = "SELECT developer.developer_id,developer.Developer_name\r\n" + //
            "FROM developer\r\n" + //
            "LEFT JOIN task_assigned ON developer.developer_id = task_assigned.developer_id\r\n" + //
            "WHERE task_assigned.developer_id IS NULL;";
        pst = DataBase.con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        System.out.println(" Dev_id || Dev_name ");
        while (rs.next()) {
          System.out.println("    "+rs.getInt(1)+"      "+rs.getString(2));
          }
        //   
          display();
          System.out.println("enter the Develoer Id to whombug should be assigned");
          System.out.println("Developer id");
          int developer_id = senior_scanner.nextInt();
          System.out.println("enter task id");
          int task_id = senior_scanner.nextInt();
          String assign ="insert into task_assigned (Developer_id,task_id) values('"+developer_id+"','"+task_id+"')";
          pst = DataBase.con.prepareStatement(assign);
          int update = pst.executeUpdate();
          if(update>0){
              System.out.println("insert succes");
            }else{
                System.out.println("update failed");
            }
            Misc.cls();
        }
        //display assigned bugs
        public static void display_assigned_bugs() throws Exception {
            String sql = "select * from task_assigned ";
            pst = DataBase.con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
              System.out.print("task_assigned id = " +rs.getInt(1));
              System.out.print(" task id = "+rs.getInt(2));
              System.out.print(" developer id ="+rs.getInt(3));
              System.out.print(" task detail = "+rs.getString(4));
              System.out.println();

            }
            
        }
        //deisplaycompleted bugs
        public static void display_completed_bugs() throws Exception {
            String sql = "select * from completed_bugs ";
            pst = DataBase.con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
              System.out.print(" task id = "+rs.getInt(2));
              System.out.print(" task detail = "+rs.getString(3));
              System.out.println();

            }
            
        }
        //deleted task display
        public static void display_deleted_task() throws Exception {
            String sql = "select * from deleted_task ";
            pst = DataBase.con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
              System.out.print(" task id = "+rs.getInt(2));
              System.out.print(" task detail ="+rs.getString(3));
              System.out.print(" task priority = "+rs.getString(4));
              System.out.println();

            }
            
        }
// Senior page 
    public static void seniorPage() throws Exception{
          
          while (true) {
            System.out.println("1.Home Page");
            System.out.println("2.add person");
            System.out.println("3.add Bugs");
            System.out.println("4.View Bugs");
            System.out.println("5.assign Bugs");
            System.out.println("6.complete bugs");
            System.out.println("7.Display assigned bugs");
            System.out.println("8.delete task");
            System.out.println("9.view deleted task");
            System.out.println("10.Exit");
            int choice = senior_scanner.nextInt();
            switch (choice) {
              case 1:
              new Page().firstPage();
              break;
              case 2:
              addPerson();
              break;
              
              case 3:
              addBug();
              break;
              case 4:
              display();
              break;
              case 5:
              assignBug();
              break;
              case 6:
              completingBug();
              break;
              
              case 7:
              display_assigned_bugs();
                break;
            
              case 8:
                delete_Bug();
                break;
              case 9:
                System.exit(0);
                break;
              case 10:
                System.exit(0);
                break;
            
              default:
                System.out.println(Misc.BRIGHT_BACKGROUND_RED+Misc.ANSI_WHITE+"Please select valid values" + Misc.ANSI_RESET);
                break;
            }
        }
        
    }
    //completeing bug
    public static void completingBug() throws Exception{

      display_assigned_bugs();
      System.out.println(" enter the task_assigned_id to marks as complete ");
      int choice = senior_scanner.nextInt();
      String sql ="delete from task_assigned where task_assign_id = '"+choice+"'";
      pst = DataBase.con.prepareStatement(sql);
      int dele = pst.executeUpdate();
      if(dele>0){
        System.out.println("delete success");
      }
      }
// dleting bug
public static void delete_Bug() throws Exception{

  display();
  System.out.println(" enter the task_id to marks as complete ");
  int choice = senior_scanner.nextInt();
  String sql ="delete from task where task_id = '"+choice+"'";
  pst = DataBase.con.prepareStatement(sql);
  int dele = pst.executeUpdate();
  if(dele>0){
    System.out.println("delete success");
  }
  }
// display all task   
    public static void display() throws Exception{

      String sql = "SELECT Task_Detail,Task_Priority FROM task ORDER BY FIELD(Task_Priority, 'High', 'Medium', 'Low');";
        pst = DataBase.con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
          String desc =rs.getString(1);
          String pr = rs.getString(2);
          senior_list.add(desc, pr);
          }
          senior_list.display();
          senior_list.head = null;
    }
// adding abug
    public static void addBug() throws Exception{
        Tester.addBug();
    }
// 
}

    

// class Senior{
// //method to add person
//   //method to add developer
// //method to add tester
// void addTester() throws Exception
// {
//   boolean flag =true;
//   while (flag) {
//   System.out.println("enter the name of Tester");
//   String testerName =sc_Senior.nextLine();
//   String sql = "insert into tester(Tester_name) values('"+testerName+"') ";
//   Main.st =Main.con.createStatement();
//   int rs = Main.st.executeUpdate(sql);
//   if(rs>0)
//   {
//     System.out.println("insert success");
//   }
//   else
//   {
//     System.out.println("insert unsuccessfull");
//   }
//   System.out.println("Press any key to add bug again or else press enter to exit");
//     String readstring = sc_Senior.nextLine();
//     if(!(readstring.equals(""))){
//       continue;
//     }
//     else{
//       flag = false;
//     }

// }
// }


