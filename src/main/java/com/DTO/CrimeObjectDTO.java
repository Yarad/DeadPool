package com.DTO;

import java.time.LocalDate;

public class CrimeObjectDTO {
    private long id;
    private CriminalCaseShortedDTO criminalCase;
    private String type;
    private LocalDate date;
    private String place;

    public CrimeObjectDTO(long id, CriminalCaseShortedDTO criminalCase, String type,
                          LocalDate date, String place) {
        this.id = id;
        this.criminalCase = criminalCase;
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

    public CriminalCaseShortedDTO getCriminalCase() {
        return criminalCase;
    }

    public void setCriminalCase(CriminalCaseShortedDTO criminalCase) {
        this.criminalCase = criminalCase;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
