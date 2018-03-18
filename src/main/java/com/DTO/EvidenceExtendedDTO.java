package com.DTO;

import java.util.List;

public class EvidenceExtendedDTO extends EvidenceObjectDTO {
    private List<EvidenceOfCrimeForListOfEvidenceDTO> evidencesOfCrime;

    public EvidenceExtendedDTO(long id, String name, String description,
                               List<EvidenceOfCrimeForListOfEvidenceDTO> evidencesOfCrime) {
        super(id, name, description);
        this.evidencesOfCrime = evidencesOfCrime;
    }

    public List<EvidenceOfCrimeForListOfEvidenceDTO> getEvidencesOfCrime() {
        return evidencesOfCrime;
    }

    public void setEvidencesOfCrime(List<EvidenceOfCrimeForListOfEvidenceDTO> evidencesOfCrime) {
        this.evidencesOfCrime = evidencesOfCrime;
    }
}
