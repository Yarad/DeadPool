package com.DTO.parsers;

import com.DTO.EvidenceExtendedDTO;
import com.DTO.EvidenceObjectDTO;
import com.DTO.EvidenceShortedDTO;
import com.logic.Evidence;
import com.logic.EvidenceOfCrime;

import java.util.List;
import java.util.stream.Collectors;

public final class EvidenceParser {
    private EvidenceParser () {}

    public static EvidenceShortedDTO parseEvidenceShorted(Evidence evidence) {
        if (evidence != null) {
            return new EvidenceShortedDTO(evidence.getEvidenceId(), evidence.getName());
        } else {
            return null;
        }
    }

    public static EvidenceObjectDTO parseEvidence(Evidence evidence) {
        if (evidence != null) {
            return new EvidenceObjectDTO(
                    evidence.getEvidenceId(),
                    evidence.getName(),
                    evidence.getDescription()
            );
        } else {
            return null;
        }
    }

    public static EvidenceExtendedDTO parseEvidenceExtended(Evidence evidence,
                                                            List<EvidenceOfCrime> evidencesOfCrime) {
        if (evidence != null) {
            return new EvidenceExtendedDTO(
                    evidence.getEvidenceId(),
                    evidence.getName(),
                    evidence.getDescription(),
                    evidencesOfCrime.stream()
                            .map(eC -> EvidenceOfCrimeParser.parseEvidenceOfCrimeForList(eC))
                            .collect(Collectors.toList())
            );
        } else {
            return null;
        }
    }
}
