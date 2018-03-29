package com.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class EvidenceOfCrimeForListOfEvidenceDTO {
    private CrimeForOthersShortedDTO crime;
    private String type;
    private String photoPath;
    private LocalDate dateAdded;
    private LocalTime timeAdded;
    private String details;

    public EvidenceOfCrimeForListOfEvidenceDTO(CrimeForOthersShortedDTO crime, String type, String photoPath,
                                               LocalDate dateAdded, LocalTime timeAdded, String details) {
        this.crime = crime;
        this.type = type;
        this.photoPath = photoPath;
        this.dateAdded = dateAdded;
        this.timeAdded = timeAdded;
        this.details = details;
    }

    public CrimeForOthersShortedDTO getCrime() {
        return crime;
    }

    public void setCrime(CrimeForOthersShortedDTO crime) {
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
