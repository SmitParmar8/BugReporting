package DSDB;

import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Misc {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_WHITE = "\u001B[37;1m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GRAY = "\u001B[90m";
    public static final String BRIGHT_BACKGROUND_WHITE = "\u001B[47;1m";
    public static final String BRIGHT_BACKGROUND_RED = "\u001B[41;1m";

    public static void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("Exception in cls()");
            System.out.println(e);
        }
    }

    public static String padAllRight(String[] arr, int L) {
        String res = "";
        for (String string : arr) {
            string = String.format("%-" + L + "s", string);
            res += string;
        }
        return res;
    }

    public static String padAllRight(String[] arr, int L, String color) {
        String res = "";
        for (String string : arr) {
            string = String.format("%-" + L + "s", string);
            res += string;
        }
        return color + res + "\u001B[0m";
    }

    public static int checkInt(Scanner inp, String s) {
        int n = 0;
        try {
            System.out.print(s);
            n = inp.nextInt();
        } catch (Exception e) {
            System.out.println("Input Type not supported");
            inp.nextLine();
            return checkInt(inp, s);
        }
        return n;
    }
}


