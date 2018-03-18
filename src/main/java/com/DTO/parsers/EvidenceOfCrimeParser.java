package com.DTO.parsers;

import com.DTO.EvidenceOfCrimeShortedDTO;
import com.DTO.EvidenceOfCrimeShortedWithCrimeDTO;
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

    public static EvidenceOfCrimeShortedWithCrimeDTO parseEvidenceOfCrimeShortedWithCrime(EvidenceOfCrime evidenceOfCrime) {
        if (evidenceOfCrime != null) {
            return new EvidenceOfCrimeShortedWithCrimeDTO(
                    EvidenceParser.parseEvidenceShorted(evidenceOfCrime.parentEvidence),
                    evidenceOfCrime.evidenceType.toString(),
                    evidenceOfCrime.getPhotoPath(),
                    CrimeParser.parseShortedCrimeForOthers(evidenceOfCrime.parentCrime)
            );
        } else {
            return null;
        }
    }
}
