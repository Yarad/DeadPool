package com.logic;

import com.DAO.DAOCriminalCase;

import java.time.LocalDate;
import java.time.LocalTime;

public class Crime {
    private long crimeId = -1;
    private long criminalCaseId = -1;
    private CrimeType crimeType;
    private String crimePlace;
    private String description;
    private LocalDate crimeDate;
    private LocalTime crimeTime;
    private CriminalCase parentCriminalCase;

    public CrimeType getCrimeType() {
        return crimeType;
    }

    public void setCrimeType(String crimeType) {
        this.crimeType = CrimeType.valueOf(crimeType);
    }

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
        this.criminalCaseId = criminalCaseId;
        this.parentCriminalCase = null;
    }

    public String getCrimePlace() {
        return crimePlace;
    }

    public void setCrimePlace(String crimePlace) {
        this.crimePlace = crimePlace;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public CriminalCase getParentCriminalCase() {
        if (parentCriminalCase == null) {
            DAOCriminalCase daoCriminalCase = new DAOCriminalCase();
            parentCriminalCase = daoCriminalCase.getCriminalCaseById(criminalCaseId);
        }
        return parentCriminalCase;
    }

    public void setParentCriminalCase(CriminalCase parentCriminalCase) {
        this.parentCriminalCase = parentCriminalCase;
        this.criminalCaseId = parentCriminalCase.getCriminalCaseId();
    }
}