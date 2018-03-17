package com.DTO;

public class ManShortedDTO {
    private long id;
    private String name;
    private String surname;
    private String photoPath;

    public ManShortedDTO(long id, String name, String surname, String photoPath) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.photoPath = photoPath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
