package com.DTO;

import java.time.LocalDateTime;

public class ParticipantInputDTO {
    private IdOnlyDTO man;
    private IdOnlyDTO crime;
    private String status;
    private LocalDateTime dateAdded;
    private String alibi;
    private String witnessReport;

    public ParticipantInputDTO() { }

    public IdOnlyDTO getMan() {
        return man;
    }

    public void setMan(IdOnlyDTO man) {
        this.man = man;
    }

    public IdOnlyDTO getCrime() {
        return crime;
    }

    public void setCrime(IdOnlyDTO crime) {
        this.crime = crime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
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
