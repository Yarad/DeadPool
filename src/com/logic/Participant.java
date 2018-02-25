package com.logic;

public class Participant extends Man {
    private int manId = -1;
    private int crimeId = -1;
    private String alibi;
    private String witnessReport;
    public ParticipantStatus participantStatus = ParticipantStatus.SUSPECTED;
    private Crime parentCrime;

    public void setCrime(Crime crime) {
        this.parentCrime = crime;
    }

    public void setCrime(Object crime) {
        try {
            this.parentCrime = (Crime) crime;
        } catch (Exception e) {
            LogicLog.log(e.toString());
        }
    }

    public Crime getCrime() {
        return parentCrime;
    }

    public int getCrimeId() {
        return crimeId;
    }

    public void setCrimeId(int crimeId) {
        this.crimeId = crimeId;
    }

    public void setCrimeId(Object crimeId) {
        try {
            this.crimeId = Integer.parseInt(crimeId.toString());
        } catch (Exception e) {
            LogicLog.log(e.toString());
        }
    }

    public String getAlibi() {
        return alibi;
    }

    public void setAlibi(String alibi) {
        this.alibi = alibi;
    }

    public void setAlibi(Object alibi) {
        this.alibi = alibi.toString();
    }

    public String getWitnessReport() {
        return witnessReport;
    }

    public void setWitnessReport(String witnessReport) {
        this.witnessReport = witnessReport;
    }

    public void setWitnessReport(Object witnessReport) {
        this.witnessReport = witnessReport.toString();
    }

    @Override
    public int getManId() {
        return manId;
    }

    @Override
    public void setManId(int manId) {
        this.manId = Math.abs(manId);
    }

    public void setManId(Object manId) {
        try {
            this.manId = Math.abs(Integer.parseInt(manId.toString()));
        } catch (Exception e) {
            LogicLog.log(e.toString());
        }
    }
}
