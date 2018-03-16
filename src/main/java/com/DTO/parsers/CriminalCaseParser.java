package com.DTO.parsers;

import com.DTO.CriminalCaseObjectDTO;
import com.DTO.CriminalCaseShortedDTO;
import com.logic.CriminalCase;

import java.time.LocalDate;

public final class CriminalCaseParser {
    private CriminalCaseParser () {}

    private static String getStatusAsString(boolean isClosed, LocalDate closeDate) {
        if (!isClosed) {
           return "открыто";
        } else {
            if (closeDate != null) {
                return "раскрыто";
            } else {
                return "не раскрыто";
            }
        }
    }

    public static CriminalCaseShortedDTO parseShortedCriminalCase(CriminalCase crimeCase) {
        return new CriminalCaseShortedDTO(
                crimeCase.getCriminalCaseId(),
                crimeCase.getCriminalCaseNumber(),
                getStatusAsString(crimeCase.isClosed(), crimeCase.getCloseDate())
        );
    }

    public static CriminalCaseObjectDTO parseCriminalCase(CriminalCase crimeCase) {
        return new CriminalCaseObjectDTO(
                crimeCase.getCriminalCaseId(),
                crimeCase.getCriminalCaseNumber(),
                getStatusAsString(crimeCase.isClosed(), crimeCase.getCloseDate()),
                crimeCase.getCreateDate(),
                crimeCase.getCloseDate(),
                (crimeCase.getParentDetective() != null)
                        ? DetectiveParser.parseDetectivePerson(crimeCase.getParentDetective())
                        : null
        );
    }
}
