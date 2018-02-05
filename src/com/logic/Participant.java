package com.logic;

public class Participant extends Man {
    private String alibi = "NoAlibi";
    private String witnessReport = "NoWitnessReport";
    public  ParticipantStatus participantStatus = ParticipantStatus.ADMIN;

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
