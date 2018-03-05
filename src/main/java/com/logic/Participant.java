package com.logic;

public class Participant extends Man {
    private long crimeId = -1; //временно
    private String alibi;
    private String witnessReport;
    public ParticipantStatus participantStatus = ParticipantStatus.SUSPECTED;
    private Crime parentCrime;

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
