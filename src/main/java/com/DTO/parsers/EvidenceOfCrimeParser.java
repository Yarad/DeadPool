package com.DTO.parsers;

import com.DTO.EvidenceOfCrimeExtendedDTO;
import com.DTO.EvidenceOfCrimeForListOfEvidenceDTO;
import com.DTO.EvidenceOfCrimeShortedDTO;
import com.DTO.EvidenceOfCrimeShortedWithCrimeDTO;
import com.logic.EvidenceOfCrime;

public final class EvidenceOfCrimeParser {
    private EvidenceOfCrimeParser () {}

    public static EvidenceOfCrimeShortedDTO parseEvidenceOfCrimeShorted(EvidenceOfCrime evidenceOfCrime) {
        if (evidenceOfCrime != null) {
            return new EvidenceOfCrimeShortedDTO(
                    EvidenceParser.parseEvidenceShorted(evidenceOfCrime.parentEvidence),
                    evidenceOfCrime.evidenceType.getName(),
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
                    evidenceOfCrime.evidenceType.getName(),
                    evidenceOfCrime.getPhotoPath(),
                    CrimeParser.parseShortedCrimeForOthers(evidenceOfCrime.parentCrime)
            );
        } else {
            return null;
        }
    }

    public static EvidenceOfCrimeForListOfEvidenceDTO parseEvidenceOfCrimeForList(EvidenceOfCrime evidenceOfCrime) {
        if (evidenceOfCrime != null) {
            return new EvidenceOfCrimeForListOfEvidenceDTO(
                    CrimeParser.parseShortedCrimeForOthers(evidenceOfCrime.parentCrime),
                    evidenceOfCrime.evidenceType.getName(),
                    evidenceOfCrime.getPhotoPath(),
                    evidenceOfCrime.getDateAdded(),
                    evidenceOfCrime.getDetails()
            );
        } else {
            return null;
        }
    }

    public static EvidenceOfCrimeExtendedDTO parseEvidenceOfCrimeExtended(EvidenceOfCrime evidenceOfCrime) {
        if (evidenceOfCrime != null) {
            return new EvidenceOfCrimeExtendedDTO(
                    EvidenceParser.parseEvidence(evidenceOfCrime.parentEvidence),
                    CrimeParser.parseShortedCrimeForOthers(evidenceOfCrime.parentCrime),
                    evidenceOfCrime.evidenceType.getName(),
                    evidenceOfCrime.getPhotoPath(),
                    evidenceOfCrime.getDateAdded(),
                    evidenceOfCrime.getDetails()
            );
        } else {
            return null;
        }
    }
}
