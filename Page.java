package DSDB;
import java.util.Scanner;

import DSDB.*;
public class Page {
  
public static String wlLine ="|      Select from below Options     |"+"\n";
public static String  options= "---1.Log in----" + "\n"+"---2.Add Person---" + "\n"+ "----3.EXIT----"+ "\n";
public static Scanner page_scanner = new Scanner(System.in);
//First Page
  public static void firstPage() throws Exception{
    
    Thread t = Thread.currentThread();
    
    Misc.cls();
    System.out.println(Misc.ANSI_WHITE +"-------------------------------------");
      for(int i =0;i<wlLine.length();i++)
          {
             System.out.print( wlLine.charAt(i));
             t.sleep(20);
          }
    System.out.println(Misc.ANSI_RESET);
      for(int i =0;i<options.length();i++)
          {
              System.out.print(options.charAt(i));
              t.sleep(20);
          }
          secondPage();
    }

// Second Page
  public static void secondPage() throws Exception{
   while (true) {
    System.out.print("\n"+Misc.ANSI_GRAY+" Enter Choice = " + Misc.ANSI_RESET);
      int choice = page_scanner.nextInt();
      switch (choice) {
        case 1:
          login(); 
          break;

        case 2:
          Senior.addPerson();
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

// login method
  public static void login() throws Exception {
    
    while (true) {
      System.out.println("1.Developer\n2.Tester\n3.Senior\n4.Back To Previous section\n5.exit");
      int choice = page_scanner.nextInt();
      switch (choice) {
        case 1:
          Developer.login_Developer();
          break;
      
        case 2:
          Tester.login_tester();
          break;
      
        case 3:
          if(Senior.seniorLogin()){
            Senior.seniorPage();
          }
          else{
            System.out.println("login failed");
          }
          break;
      
        case 4:
          firstPage();
          break;
      
        case 5:
          System.out.println(Misc.BRIGHT_BACKGROUND_WHITE+ Misc.ANSI_GRAY+"Thank You For using it ");
          System.out.println(Misc.ANSI_RESET);
          Misc.cls();
          System.exit(0);
          break;
      
        default:
          System.out.println(Misc.BRIGHT_BACKGROUND_RED+Misc.ANSI_WHITE+"Please select valid values" + Misc.ANSI_RESET);
          Misc.cls();
          break;
      }
    
    }
  }
  //
  
}
