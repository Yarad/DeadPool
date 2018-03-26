package com.logic;

import java.time.LocalDateTime;

public class EvidenceOfCrime {
    public Crime parentCrime = null;
    public Evidence parentEvidence = null;
    private String photoPath;
    private String details;
    private EvidenceType evidenceType = EvidenceType.OBJECT_FROM_CRIME_SCENE;
    private LocalDateTime dateAdded;

    public EvidenceType getEvidenceType() {
        return evidenceType;
    }

    public void setEvidenceType(String evidenceType) {
        this.evidenceType = EvidenceType.valueOf(evidenceType);
    }

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
