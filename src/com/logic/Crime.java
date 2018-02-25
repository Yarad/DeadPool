package com.logic;

import java.time.LocalDate;
import java.time.LocalTime;

public class Crime {
    private long crimeId = -1;
    private long criminalCaseId = -1;
    private String crimePlace = "noAddress";
    private LocalDate crimeDate = LocalDate.now();
    private LocalTime crimeTime = null;
    private CriminalCase parentCriminalCase;

    public long getCrimeId() {
        return crimeId;
    }

    public void setCrimeId(long crimeId) {
        this.crimeId = Math.abs(crimeId);
    }

    public long getCriminalCaseId() {
        return criminalCaseId;
    }

    public void setCriminalCaseId(long criminalCaseId) {
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