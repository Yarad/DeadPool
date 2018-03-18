package com.DTO;

public class ParticipantByManDTO {
    private CrimeForOthersShortedDTO crime;
    private String status;

    public ParticipantByManDTO(CrimeForOthersShortedDTO crime, String status) {
        this.crime = crime;
        this.status = status;
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
}
