package com.logic;

import java.time.format.DateTimeFormatter;

public class ProjectConstants {
    public static String DATE_FORMAT = "dd.MM.yyyy";
    public static DateTimeFormatter myDateTimeFormatter = DateTimeFormatter.ofPattern(ProjectConstants.DATE_FORMAT);
}
