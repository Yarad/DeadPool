package com.DTO;

public class ParticipantFullInfoDTO {
    private ManOnlyPersonDTO man;
    private CrimeForOthersShortedDTO crime;
    private String status;
    private String photoPath;
    private String alibi;
    private String witnessReport;

    public ParticipantFullInfoDTO(ManOnlyPersonDTO man, CrimeForOthersShortedDTO crime, String status,
                                  String photoPath, String alibi, String witnessReport) {
        this.man = man;
        this.crime = crime;
        this.status = status;
        this.photoPath = photoPath;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
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
