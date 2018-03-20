package com.logic;

import java.time.format.DateTimeFormatter;

public class ProjectConstants {
    public static String DATE_FORMAT = "yyyy-MM-dd";
    public static String TIME_FORMAT = "HH:mm:ss";
    public static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern(ProjectConstants.DATE_FORMAT);
    public static DateTimeFormatter myTimeFormatter = DateTimeFormatter.ofPattern(ProjectConstants.TIME_FORMAT);
    public static DateTimeFormatter myDateTimeFormatter = DateTimeFormatter.ofPattern(ProjectConstants.DATETIME_FORMAT);

/*
    public static void fillObjectFieldByArrayOfValues(Object objectToFill, List<HashMap<String, Object>> sourceArray) {
        Field[] fields = objectToFill.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++)
            LogicLog.log(fields[i].toString());
    }*/
}
