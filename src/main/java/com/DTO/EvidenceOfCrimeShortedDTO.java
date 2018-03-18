package com.DTO;

public class EvidenceOfCrimeShortedDTO {
    private EvidenceShortedDTO evidence;
    private String type;
    private String photoPath;

    public EvidenceOfCrimeShortedDTO(EvidenceShortedDTO evidence, String type, String photoPath) {
        this.evidence = evidence;
        this.type = type;
        this.photoPath = photoPath;
    }

    public EvidenceShortedDTO getEvidence() {
        return evidence;
    }

    public void setEvidence(EvidenceShortedDTO evidence) {
        this.evidence = evidence;
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
}
