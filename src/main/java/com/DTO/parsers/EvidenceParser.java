package com.DTO.parsers;

import com.DTO.EvidenceShortedDTO;
import com.logic.Evidence;

public final class EvidenceParser {
    private EvidenceParser () {}

    public static EvidenceShortedDTO parseEvidenceShorted(Evidence evidence) {
        if (evidence != null) {
            return new EvidenceShortedDTO(evidence.getEvidenceId(), evidence.getName());
        } else {
            return null;
        }
    }
}
