package com.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class EvidenceOfCrimeInputDTO {
    private IdOnlyDTO evidence;
    private IdOnlyDTO crime;
    private String type;
    private String photoPath;
    private LocalDate dateAdded;
    private LocalTime timeAdded;
    private String details;

    public EvidenceOfCrimeInputDTO() { }

    public IdOnlyDTO getEvidence() {
        return evidence;
    }

    public void setEvidence(IdOnlyDTO evidence) {
        this.evidence = evidence;
    }

    public IdOnlyDTO getCrime() {
        return crime;
    }

    public void setCrime(IdOnlyDTO crime) {
        this.crime = crime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
