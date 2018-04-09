package com.logic;

import com.DAO.DAOCrime;

import java.time.LocalDateTime;

public class Participant extends Man {
    private long crimeId = -1;
    private String alibi;
    private String witnessReport;
    private LocalDateTime dateAdded;

    private Crime parentCrime;
    private ParticipantStatus participantStatus;

    public Crime getParentCrime() {
        if (parentCrime == null) {
            DAOCrime daoCrime = new DAOCrime();
            parentCrime = daoCrime.getCrimeById(crimeId);
        }

        return parentCrime;
    }

    public void setParentCrime(Crime parentCrime) {
        this.parentCrime = parentCrime;
    }

    public ParticipantStatus getParticipantStatus() {
        return participantStatus;
    }

    public void setParticipantStatus(String participantStatus) {
        this.participantStatus = ParticipantStatus.valueOf(participantStatus);
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setCrime(Crime crime) {
        this.parentCrime = crime;
        this.crimeId = crime.getCrimeId();
    }

    public long getCrimeId() {
        return crimeId;
    }

    public void setCrimeId(long crimeId) {
        this.crimeId = crimeId;
        this.parentCrime = null;
    }

    public String getAlibi() {
        return alibi;
    }

    public void setAlibi(String alibi) {
        this.alibi = alibi;
    }

    public String getWitnessReport() {
        return witnessReport;
    }

    public void setWitnessReport(String witnessReport) {
        this.witnessReport = witnessReport;
    }

    @Override
    public long getManId() {
        return manId;
    }

    @Override
    public void setManId(long manId) {
        this.manId = Math.abs(manId);
    }
}
