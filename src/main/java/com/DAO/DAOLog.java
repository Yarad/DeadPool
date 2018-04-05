package com.DAO;

import java.util.Date;

public class DAOLog {
    private static boolean isEnabled = true;
/*
    public static void enable() {
        DAOLog.isEnabled = true;
    }

    public static void disable() {
        DAOLog.isEnabled = false;
    }
*/
    public static void log(String logString) {
        System.out.print(new Date().toString() + ": " + logString);
    }

}
