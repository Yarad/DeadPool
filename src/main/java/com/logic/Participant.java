package com.logic;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Participant extends Man {
    private long crimeId = -1; //временно
    private String alibi;
    private String witnessReport;
    private LocalDateTime dateAdded = LocalDateTime.now();
    private Crime parentCrime;
    private ParticipantStatus participantStatus = ParticipantStatus.SUSPECTED;

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
    }

    public Crime getCrime() {
        return parentCrime;
    }

    public long getCrimeId() {
        return crimeId;
    }

    public void setCrimeId(long crimeId) {
        this.crimeId = crimeId;
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
