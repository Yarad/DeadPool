package com.DTO;

import java.time.LocalDateTime;

public class EvidenceOfCrimeForListOfEvidenceDTO {
    private CrimeForOthersShortedDTO crime;
    private String type;
    private String photoPath;
    private LocalDateTime dateAdded;
    private String details;

    public EvidenceOfCrimeForListOfEvidenceDTO(CrimeForOthersShortedDTO crime, String type,
                                               String photoPath, LocalDateTime dateAdded, String details) {
        this.crime = crime;
        this.type = type;
        this.photoPath = photoPath;
        this.dateAdded = dateAdded;
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

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
