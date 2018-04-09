package com.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ParticipantFullInfoDTO {
    private ManOnlyPersonDTO man;
    private CrimeForOthersShortedDTO crime;
    private EnumDTO status;
    private String photoPath;
    private LocalDate dateAdded;
    private LocalTime timeAdded;
    private String alibi;
    private String witnessReport;

    public ParticipantFullInfoDTO(ManOnlyPersonDTO man, CrimeForOthersShortedDTO crime, EnumDTO status,
                                  String photoPath, LocalDate dateAdded, LocalTime timeAdded, String alibi, String witnessReport) {
        this.man = man;
        this.crime = crime;
        this.status = status;
        this.photoPath = photoPath;
        this.dateAdded = dateAdded;
        this.timeAdded = timeAdded;
        this.alibi = alibi;
        this.witnessReport = witnessReport;
    }

    public ManOnlyPersonDTO getMan() {
        return man;
    }

    public void setMan(ManOnlyPersonDTO man) {
        this.man = man;
    }

    public CrimeForOthersShortedDTO getCrime() {
        return crime;
    }

    public void setCrime(CrimeForOthersShortedDTO crime) {
        this.crime = crime;
    }

    public EnumDTO getStatus() {
        return status;
    }

    public void setStatus(EnumDTO status) {
        this.status = status;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public LocalTime getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(LocalTime timeAdded) {
        this.timeAdded = timeAdded;
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
