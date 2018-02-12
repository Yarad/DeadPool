package com.logic;

import java.time.LocalDate;
import java.time.LocalTime;

public class Crime {
    private int crimeId = -1;
    private int criminalCaseId = -1;
    private String crimePlace = "noAddress";
    private LocalDate crimeDate = LocalDate.now();
    private LocalTime crimeTime = null;

    public int getCrimeId() {
        return crimeId;
    }

    public void setCrimeId(int crimeId) {
        this.crimeId = Math.abs(crimeId);
    }

    public int getCriminalCaseId() {
        return criminalCaseId;
    }

    public void setCriminalCaseId(int criminalCaseId) {
        this.criminalCaseId = Math.abs(criminalCaseId);
    }

    public String getCrimePlace() {
        return crimePlace;
    }

    public void setCrimePlace(String crimePlace) {
        this.crimePlace = crimePlace;
    }

    public LocalDate getCrimeDate() {
        return crimeDate;
    }

    public void setCrimeDate(LocalDate crimeDate) {
        this.crimeDate = crimeDate;
    }

    public LocalTime getCrimeTime() {
        return crimeTime;
    }

    public void setCrimeTime(LocalTime crimeTime) {
        this.crimeTime = crimeTime;
    }
}
