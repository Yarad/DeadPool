package com.DTO;

import java.time.LocalDateTime;

public class EvidenceOfCrimeExtendedDTO extends EvidenceOfCrimeForListOfEvidenceDTO {
    private EvidenceObjectDTO evidence;

    public EvidenceOfCrimeExtendedDTO(EvidenceObjectDTO evidence, CrimeForOthersShortedDTO crime,
                                      String type, String photoPath, LocalDateTime dateAdded, String details) {
        super(crime, type, photoPath, dateAdded, details);
        this.evidence = evidence;
    }

    public EvidenceShortedDTO getEvidence() {
        return evidence;
    }

    public void setEvidence(EvidenceObjectDTO evidence) {
        this.evidence = evidence;
    }
}
