package com.logic;

public class EvidenceOfCrime {
    public Crime parentCrime = null;
    public Evidence parentEvidence = null;
    private String photoPath;
    private String details;
    public EvidenceType evidenceType;

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
}
