package com.DTO;

import java.util.List;

public class ListEvidenceOfCrimeShortedWithCrimeList {
    private List<EvidenceOfCrimeShortedWithCrimeDTO> evidencesOfCrime;

    public ListEvidenceOfCrimeShortedWithCrimeList(List<EvidenceOfCrimeShortedWithCrimeDTO> evidencesOfCrime) {
        this.evidencesOfCrime = evidencesOfCrime;
    }

    public List<EvidenceOfCrimeShortedWithCrimeDTO> getEvidencesOfCrime() {
        return evidencesOfCrime;
    }

    public void setEvidencesOfCrime(List<EvidenceOfCrimeShortedWithCrimeDTO> evidencesOfCrime) {
        this.evidencesOfCrime = evidencesOfCrime;
    }
}
