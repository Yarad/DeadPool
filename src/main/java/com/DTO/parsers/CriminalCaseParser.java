package com.DTO.parsers;

import com.DTO.CriminalCaseObjectDTO;
import com.logic.CriminalCase;

public final class CriminalCaseParser {
    private CriminalCaseParser () {}

    public static CriminalCaseObjectDTO parseCriminalCase(CriminalCase crimeCase) {
        String type;
        if (!crimeCase.isClosed()) {
            type = "открыто";
        } else {
            if (crimeCase.getCloseDate() != null) {
                type = "раскрыто";
            } else {
                type = "не раскрыто";
            }
        }
        return new CriminalCaseObjectDTO(
                crimeCase.getCriminalCaseId(),
                crimeCase.getCriminalCaseNumber(),
                type,
                crimeCase.getCreateDate(),
                crimeCase.getCloseDate(),
                (crimeCase.getParentDetective() != null)
                        ? DetectiveParser.parseDetectivePerson(crimeCase.getParentDetective())
                        : null
        );
    }
}
