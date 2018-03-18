package com.logic;

import java.time.LocalDateTime;

public class EvidenceOfCrime {
    public Crime parentCrime = null;
    public Evidence parentEvidence = null;
    private String photoPath;
    private String details;
    public EvidenceType evidenceType = EvidenceType.CRIME_INSTRUMENT;
    private LocalDateTime dateAdded;

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }
}
