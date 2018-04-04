package com.logic;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static com.mysql.jdbc.StringUtils.isNullOrEmpty;

public class Man {
    protected long manId;
    protected String name;
    protected String surname;
    protected LocalDate birthDay;
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
        if (isNullOrEmpty(name))
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

    @Override
    public int hashCode() {
        return (int) (101 + (isNullOrEmpty(name) ? 313 : 31 * name.hashCode())
                + (isNullOrEmpty(name) ? 181 : 29 * surname.hashCode())) % Integer.MAX_VALUE;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Man man = (Man)obj;
        if (manId != man.getManId() || !name.equals(man.getName()) || !surname.equals(man.getSurname()) ||
                !birthDay.equals(man.getBirthDay()) || !photoPath.equals(man.getPhotoPath())
                || !homeAddress.equals(man.getHomeAddress())) {
            return false;
        }
        return true;
    }
}
