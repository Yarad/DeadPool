package com.DTO;

public class EvidenceOfCrimeShortedDTO {
    private EvidenceShortedDTO evidence;
    private EnumDTO type;
    private String photoPath;

    public EvidenceOfCrimeShortedDTO(EvidenceShortedDTO evidence, EnumDTO type, String photoPath) {
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

    public EnumDTO getType() {
        return type;
    }

    public void setType(EnumDTO type) {
        this.type = type;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
