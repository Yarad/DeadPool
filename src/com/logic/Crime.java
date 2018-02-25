package com.logic;

import java.time.LocalDate;
import java.time.LocalTime;

public class Crime {
    private int crimeId = -1;
    private int criminalCaseId = -1;
    private String crimePlace = "noAddress";
    private LocalDate crimeDate = LocalDate.now();
    private LocalTime crimeTime = null;
    private CriminalCase parentCriminalCase;

    public int getCrimeId() {
        return crimeId;
    }

    public void setCrimeId(int crimeId) {
        this.crimeId = Math.abs(crimeId);
    }

    public void setCrimeId(Object crimeId) {
        try {
            this.crimeId = Math.abs(Integer.parseInt(crimeId.toString()));
        } catch (Exception e) {
            LogicLog.log(e.toString());
        }
    }

    public int getCriminalCaseId() {
        return criminalCaseId;
    }

    public void setCriminalCaseId(int criminalCaseId) {
        this.criminalCaseId = Math.abs(criminalCaseId);
    }

    public void setCriminalCaseId(Object criminalCaseId) {
        try {
            this.criminalCaseId = Math.abs(Integer.parseInt(criminalCaseId.toString()));
        } catch (Exception e) {
            LogicLog.log(e.toString());
        }
    }

    public String getCrimePlace() {
        return crimePlace;
    }

    public void setCrimePlace(Object crimePlace) {
        this.crimePlace = crimePlace.toString();
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

    public void setCrimeDate(Object crimeDate) throws Exception {
        try {
            this.crimeDate = LocalDate.parse(crimeDate.toString());
        } catch (Exception e) {
            LogicLog.log(e.toString());
        }
    }

    public LocalTime getCrimeTime() {
        return crimeTime;
    }

    public void setCrimeTime(LocalTime crimeTime) {
        this.crimeTime = crimeTime;
    }

    public void setCrimeTime(Object crimeTime) {
        try {
            this.crimeTime = LocalTime.parse(crimeTime.toString());
        } catch (Exception e) {
            LogicLog.log(e.toString());
        }
    }
}