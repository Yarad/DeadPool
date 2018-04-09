package com.DTO;

public class ParticipantByManDTO {
    private CrimeForOthersShortedDTO crime;
    private EnumDTO status;

    public ParticipantByManDTO(CrimeForOthersShortedDTO crime, EnumDTO status) {
        this.crime = crime;
        this.status = status;
    }

    public CrimeForOthersShortedDTO getCrime() {
        return crime;
    }

    public void setCrime(CrimeForOthersShortedDTO crime) {
        this.crime = crime;
    }

    public EnumDTO getStatus() {
        return status;
    }

    public void setStatus(EnumDTO status) {
        this.status = status;
    }
}
