package com.logic;

import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

public class ProjectConstants {
    public static String DATE_FORMAT = "yyyy-MM-dd";
    public static String TIME_FORMAT = "HH:mm:ss";
    public static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss.0";
    public static DateTimeFormatter myDateFormatter = ofPattern(ProjectConstants.DATE_FORMAT);
    public static DateTimeFormatter myTimeFormatter = ofPattern(ProjectConstants.TIME_FORMAT);
    public static DateTimeFormatter myDateTimeFormatter = ofPattern(ProjectConstants.DATETIME_FORMAT);

    public static DateTimeFormatter fileDateTimeFormatter = ofPattern("yyyy-MM-dd_HH:mm:ss");
    public static DateTimeFormatter reportTitleDateTimeFormatter = ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final DateTimeFormatter JSON_FORMATTER_DATE = ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter JSON_FORMATTER_TIME = ofPattern("HH:mm");
    public static final DateTimeFormatter JSON_FORMATTER_DATETIME = ofPattern("yyyy-MM-dd HH:mm");

/*
    public static void fillObjectFieldByArrayOfValues(Object objectToFill, List<HashMap<String, Object>> sourceArray) {
        Field[] fields = objectToFill.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++)
            LogicLog.log(fields[i].toString());
    }*/
}
