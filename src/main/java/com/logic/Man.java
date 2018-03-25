package com.logic;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static com.mysql.jdbc.StringUtils.isNullOrEmpty;

public class Man {
    protected long manId = -1;
    protected String name = "NoName";
    protected String surname = "NoSurname";
    protected LocalDate birthDay/* = LocalDate.of(2012, 12, 31)*/;
    protected String homeAddress;
    protected String photoPath;

    public long getManId() {
        return manId;
    }

    public void setManId(long manId) {
        this.manId = Math.abs(manId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (isNullOrEmpty(name))  // почему это плохо?
            this.name = null;
        else
            this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (isNullOrEmpty(surname))
            this.surname = null;
        else
            this.surname = surname;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }
/*
    public void setBirthDay(String birthDay) {
        try {
            this.birthDay = LocalDate.parse(birthDay, ProjectConstants.myDateFormatter);
        } catch (DateTimeParseException e) {
            LogicLog.log(e.toString());
        }
    }
*/
    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
