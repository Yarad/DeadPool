package com.DTO.parsers;

import com.DTO.*;
import com.logic.Crime;
import com.logic.CriminalCase;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
        if (crimeCase != null) {
            return new CriminalCaseShortedDTO(
                    crimeCase.getCriminalCaseId(),
                    crimeCase.getCriminalCaseNumber(),
                    getStatusAsString(crimeCase.isClosed(), crimeCase.getCloseDate())
            );
        } else {
            return null;
        }
    }

    public static CriminalCaseObjectDTO parseCriminalCase(CriminalCase crimeCase) {
        if (crimeCase != null) {
            return new CriminalCaseObjectDTO(
                    crimeCase.getCriminalCaseId(),
                    crimeCase.getCriminalCaseNumber(),
                    getStatusAsString(crimeCase.isClosed(), crimeCase.getCloseDate()),
                    DetectiveParser.parseDetectivePerson(crimeCase.getParentDetective()),
                    crimeCase.getCreateDate(),
                    crimeCase.getCloseDate()
            );
        } else {
            return null;
        }
    }

    public static CriminalCaseExtendedDTO parseExtendedCriminalCase(CriminalCase crimeCase, List<Crime> crimes) {
        if (crimeCase != null) {
            return new CriminalCaseExtendedDTO(
                    crimeCase.getCriminalCaseId(),
                    crimeCase.getCriminalCaseNumber(),
                    getStatusAsString(crimeCase.isClosed(), crimeCase.getCloseDate()),
                    crimeCase.getCreateDate(),
                    crimeCase.getCloseDate(),
                    DetectiveParser.parseDetectivePerson(crimeCase.getParentDetective()),
                    crimes.stream()
                            .map(cc -> CrimeParser.parseShortedCrime(cc))
                            .collect(Collectors.toList())
            );
        } else {
            return null;
        }
    }
}
