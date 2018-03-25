package com.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class CrimeInputDTO {
    private IdOnlyDTO criminalCase;
    private String type;
    private String description;
    private LocalDate date;
    private LocalTime time;
    private String place;

    public CrimeInputDTO() { }

    public IdOnlyDTO getCriminalCase() {
        return criminalCase;
    }

    public void setCriminalCase(IdOnlyDTO criminalCase) {
        this.criminalCase = criminalCase;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
