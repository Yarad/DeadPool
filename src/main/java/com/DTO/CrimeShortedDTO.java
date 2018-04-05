package com.DTO;

import java.time.LocalDate;

public class CrimeShortedDTO {
    private long id;
    private EnumDTO type;
    private LocalDate date;
    private String place;

    public CrimeShortedDTO(long id, EnumDTO type, LocalDate date, String place) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.place = place;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EnumDTO getType() {
        return type;
    }

    public void setType(EnumDTO type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
