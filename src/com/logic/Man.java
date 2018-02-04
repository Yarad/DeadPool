package com.logic;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public abstract class Man {
    protected String name = "NoName";
    protected String surname = "NoSurname";
    protected String middleName = "NoMiddleName";
    protected LocalDate birthDay = LocalDate.parse("08.09.1998", ProjectConstants.myDateTimeFormatter);
    protected String homeAddress = "NoHomeAddress";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.equals(""))
            this.name = "NoName";
        else
            this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (surname.equals(""))
            this.surname = "NoSurname";
        else
            this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        if (middleName.equals(""))
            this.middleName = "NoMiddleName";
        else
            this.middleName = middleName;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public void setBirthDay(String birthDay) {
        try {
            this.birthDay = LocalDate.parse(birthDay, ProjectConstants.myDateTimeFormatter);
        } catch (DateTimeParseException e) {
            LogicLog.log(e.toString());
        }
    }

    public String getHomeAddress() {
        return homeAddress;
    }
    public void setHomeAddress(String homeAddress) {
        if (!homeAddress.isEmpty())
            this.homeAddress = homeAddress;
    }
}
