package com.logic;

public class Participant extends Man {
    private int crimeId = -1;
    private String alibi = "NoAlibi";
    private String witnessReport = "NoWitnessReport";
    public  ParticipantStatus participantStatus = ParticipantStatus.SUSPECTED;

    public int getCrimeId() {
        return crimeId;
    }

    public void setCrimeId(int crimeId) {
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
}
