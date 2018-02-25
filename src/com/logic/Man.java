package com.logic;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static com.mysql.jdbc.StringUtils.isNullOrEmpty;

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

    public void setManId(Object manId) {
        try {
            this.manId = Math.abs(Integer.parseInt(manId.toString()));
        } catch (Exception e) {
            LogicLog.log(e.toString());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (isNullOrEmpty(name))  // почему это плохо?
            this.name = "NoName";
        else
            this.name = name;
    }

    public void setName(Object name) {
        setName(name.toString());
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

    public void setSurname(Object surname) {
        setSurname(surname.toString());
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

    public void setBirthDay(Object birthDay) {
        setBirthDay(birthDay.toString());
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        if (!homeAddress.isEmpty())
            this.homeAddress = homeAddress;
    }

    public void setHomeAddress(Object homeAddress) {
        setHomeAddress(homeAddress.toString());
    }
}
