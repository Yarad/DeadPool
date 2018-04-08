package com.logic;

import com.DAO.DAOCrime;
import com.DAO.DAOEvidence;

import java.time.LocalDateTime;

public class EvidenceOfCrime {
    private long crimeId = -1;
    private long evidenceId = -1;

    private Crime parentCrime;
    private Evidence parentEvidence;
    private String photoPath;
    private String details;
    private EvidenceType evidenceType;
    private LocalDateTime dateAdded;

    public long getCrimeId() {
        return crimeId;
    }

    public long getEvidenceId() {
        return evidenceId;
    }

    public void setCrimeId(long crimeId) {
        this.crimeId = crimeId;
        parentCrime = null;
    }

    public void setEvidenceId(long evidenceId) {
        this.evidenceId = evidenceId;
        parentEvidence = null;
    }

    public Crime getParentCrime() {
        if (parentCrime == null) {
            DAOCrime daoCrime = new DAOCrime();
            parentCrime = daoCrime.getCrimeById(getCrimeId());
        }

        return parentCrime;
    }

    public void setParentCrime(Crime parentCrime) {
        this.parentCrime = parentCrime;
        crimeId = parentCrime.getCrimeId();
    }

    public Evidence getParentEvidence() {
        if (parentEvidence == null) {
            DAOEvidence daoEvidence = new DAOEvidence();
            parentEvidence = daoEvidence.getEvidenceById(getEvidenceId());
        }

        return parentEvidence;
    }

    public void setParentEvidence(Evidence parentEvidence) {
        this.parentEvidence = parentEvidence;
        evidenceId = parentEvidence.getEvidenceId();
    }

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
