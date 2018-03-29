package com.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class EvidenceOfCrimeExtendedDTO extends EvidenceOfCrimeForListOfEvidenceDTO {
    private EvidenceObjectDTO evidence;

    public EvidenceOfCrimeExtendedDTO(EvidenceObjectDTO evidence, CrimeForOthersShortedDTO crime,
                                      String type, String photoPath, LocalDate dateAdded, LocalTime timeAdded, String details) {
        super(crime, type, photoPath, dateAdded, timeAdded, details);
        this.evidence = evidence;
    }

    public EvidenceShortedDTO getEvidence() {
        return evidence;
    }

    public void setEvidence(EvidenceObjectDTO evidence) {
        this.evidence = evidence;
    }
}
