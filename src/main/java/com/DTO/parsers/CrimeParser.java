package com.DTO.parsers;

import com.DTO.CrimeObjectDTO;
import com.logic.Crime;

public final class CrimeParser {
    private CrimeParser () {}

    //TODO: replace checks on null for parent entities
    public static CrimeObjectDTO parseCrime(Crime crime) {
        return new CrimeObjectDTO(
                crime.getCrimeId(),
                (crime.getParentCriminalCase() != null)
                        ? CriminalCaseParser.parseShortedCriminalCase(crime.getParentCriminalCase())
                        : null,
                crime.getCrimeType(),
                crime.getCrimeDate(),
                crime.getCrimePlace()
        );
    }
}
