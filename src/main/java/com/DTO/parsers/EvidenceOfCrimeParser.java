package com.DTO.parsers;

import com.DTO.EvidenceOfCrimeShortedDTO;
import com.logic.EvidenceOfCrime;

public final class EvidenceOfCrimeParser {
    private EvidenceOfCrimeParser () {}

    public static EvidenceOfCrimeShortedDTO parseEvidenceOfCrimeShorted(EvidenceOfCrime evidenceOfCrime) {
        if (evidenceOfCrime != null) {
            return new EvidenceOfCrimeShortedDTO(
                    EvidenceParser.parseEvidenceShorted(evidenceOfCrime.parentEvidence),
                    evidenceOfCrime.evidenceType.toString(),
                    evidenceOfCrime.getPhotoPath()
            );
        } else {
            return null;
        }
    }
}
