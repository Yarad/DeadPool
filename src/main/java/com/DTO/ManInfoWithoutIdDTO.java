package com.DTO;

import java.time.LocalDate;

public class ManInfoWithoutIdDTO {
    private String name;
    private String surname;
    private LocalDate birthday;
    private String homeAddress;
    private String photoPath;

    public ManInfoWithoutIdDTO(String name, String surname, LocalDate birthday, String homeAddress, String photoPath) {
        this.name = name;
        this.surname = surname;
        this.photoPath = photoPath;
        this.birthday = birthday;
        this.homeAddress = homeAddress;
    }

    public ManInfoWithoutIdDTO() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
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
}
