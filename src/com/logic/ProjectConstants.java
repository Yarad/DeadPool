package com.logic;

import java.lang.reflect.Field;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

public class ProjectConstants {
    public static String DATE_FORMAT = "dd.MM.yyyy";
    public static DateTimeFormatter myDateTimeFormatter = DateTimeFormatter.ofPattern(ProjectConstants.DATE_FORMAT);
/*
    public static void fillObjectFieldByArrayOfValues(Object objectToFill, List<HashMap<String, Object>> sourceArray) {
        Field[] fields = objectToFill.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++)
            LogicLog.log(fields[i].toString());
    }*/
}
