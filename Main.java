import java.sql.*;
import java.util.*;

import DSDB.*;
public class Main {
    
    public static void main(String[] args) throws Exception {
        DataBase d= new DataBase();
        if(d.con!=null){
          System.out.println("connection success");
          System.out.println("----welcome to the Bug reporting software----" +'\n');
          Page p = new Page();
            p.firstPage();
          
        }else{
          System.out.println("connection failed");
        }

  }
}
      
