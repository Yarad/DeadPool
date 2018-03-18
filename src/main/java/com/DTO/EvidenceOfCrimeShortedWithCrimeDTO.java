package com.DTO;

public class EvidenceOfCrimeShortedWithCrimeDTO extends EvidenceOfCrimeShortedDTO {
    private CrimeForOthersShortedDTO crime;

    public EvidenceOfCrimeShortedWithCrimeDTO(EvidenceShortedDTO evidence, String type, String photoPath,
                                              CrimeForOthersShortedDTO crime) {
        super(evidence, type, photoPath);
        this.crime = crime;
    }

    public CrimeForOthersShortedDTO getCrime() {
        return crime;
    }

    public void setCrime(CrimeForOthersShortedDTO crime) {
        this.crime = crime;
    }
}
