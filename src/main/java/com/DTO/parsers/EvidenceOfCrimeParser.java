package com.DTO.parsers;

import com.DTO.*;
import com.logic.EvidenceOfCrime;
import com.logic.EvidenceType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class EvidenceOfCrimeParser {
    private EvidenceOfCrimeParser () {}

    public static EvidenceOfCrimeShortedDTO parseEvidenceOfCrimeShorted(EvidenceOfCrime evidenceOfCrime) {
        if (evidenceOfCrime != null) {
            return new EvidenceOfCrimeShortedDTO(
                    EvidenceParser.parseEvidenceShorted(evidenceOfCrime.getParentEvidence()),
                    new EnumDTO(evidenceOfCrime.getEvidenceType().toString(), evidenceOfCrime.getEvidenceType().getName()),
                    evidenceOfCrime.getPhotoPath()
            );
        } else {
            return null;
        }
    }

    public static EvidenceOfCrimeShortedWithCrimeDTO parseEvidenceOfCrimeShortedWithCrime(EvidenceOfCrime evidenceOfCrime) {
        if (evidenceOfCrime != null) {
            return new EvidenceOfCrimeShortedWithCrimeDTO(
                    EvidenceParser.parseEvidenceShorted(evidenceOfCrime.getParentEvidence()),
                    new EnumDTO(evidenceOfCrime.getEvidenceType().toString(), evidenceOfCrime.getEvidenceType().getName()),
                    evidenceOfCrime.getPhotoPath(),
                    CrimeParser.parseShortedCrimeForOthers(evidenceOfCrime.getParentCrime())
            );
        } else {
            return null;
        }
    }

    public static EvidenceOfCrimeForListOfEvidenceDTO parseEvidenceOfCrimeForList(EvidenceOfCrime evidenceOfCrime) {
        if (evidenceOfCrime != null) {
            return new EvidenceOfCrimeForListOfEvidenceDTO(
                    CrimeParser.parseShortedCrimeForOthers(evidenceOfCrime.getParentCrime()),
                    new EnumDTO(evidenceOfCrime.getEvidenceType().toString(), evidenceOfCrime.getEvidenceType().getName()),
                    evidenceOfCrime.getPhotoPath(),
                    (evidenceOfCrime.getDateAdded() != null) ? evidenceOfCrime.getDateAdded().toLocalDate() : null,
                    (evidenceOfCrime.getDateAdded() != null) ? evidenceOfCrime.getDateAdded().toLocalTime() : null,
                    evidenceOfCrime.getDetails()
            );
        } else {
            return null;
        }
    }

    public static EvidenceOfCrimeExtendedDTO parseEvidenceOfCrimeExtended(EvidenceOfCrime evidenceOfCrime) {
        if (evidenceOfCrime != null) {
            return new EvidenceOfCrimeExtendedDTO(
                    EvidenceParser.parseEvidence(evidenceOfCrime.getParentEvidence()),
                    CrimeParser.parseShortedCrimeForOthers(evidenceOfCrime.getParentCrime()),
                    new EnumDTO(evidenceOfCrime.getEvidenceType().toString(), evidenceOfCrime.getEvidenceType().getName()),
                    evidenceOfCrime.getPhotoPath(),
                    (evidenceOfCrime.getDateAdded() != null) ? evidenceOfCrime.getDateAdded().toLocalDate() : null,
                    (evidenceOfCrime.getDateAdded() != null) ? evidenceOfCrime.getDateAdded().toLocalTime() : null,
                    evidenceOfCrime.getDetails()
            );
        } else {
            return null;
        }
    }

    public static ListEnumDTO getEvidenceTypes() {
        EvidenceType[] types = EvidenceType.class.getEnumConstants();
        List<EnumDTO> results = new ArrayList<>();
        for(EvidenceType type: types) {
            results.add(new EnumDTO(type.toString(), type.getName()));
        }
        return new ListEnumDTO(results);
    }
}
