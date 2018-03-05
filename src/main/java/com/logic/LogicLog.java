package com.logic;
import java.util.Date;

public class LogicLog {

    private static boolean isEnabled = true;

    public static void enable() {
        isEnabled = true;
    }

    public static void disable() {
        isEnabled = false;
    }

    public static void log(String logString) {
        System.out.print(new Date().toString() + ": " + logString);
    }
}
