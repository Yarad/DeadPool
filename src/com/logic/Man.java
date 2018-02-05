package com.logic;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public abstract class Man {
    protected int manId = -1;
    protected String name = "NoName";
    protected String surname = "NoSurname";
    protected LocalDate birthDay = LocalDate.parse("08.09.1998", ProjectConstants.myDateTimeFormatter);
    protected String homeAddress = "NoHomeAddress";

    public int getManId() {
        return manId;
    }

    public void setManId(int manId) {
        this.manId = Math.abs(manId);
    }

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
