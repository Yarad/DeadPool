package com.DTO.parsers;

import com.DTO.CrimeObjectDTO;
import com.DTO.CrimeShortedDTO;
import com.logic.Crime;

public final class CrimeParser {
    private CrimeParser () {}

    public static CrimeObjectDTO parseCrime(Crime crime) {
        if (crime != null) {
            return new CrimeObjectDTO(
                    crime.getCrimeId(),
                    crime.getCrimeType(),
                    crime.getCrimeDate(),
                    crime.getCrimePlace(),
                    CriminalCaseParser.parseShortedCriminalCase(crime.getParentCriminalCase())
            );
        } else {
            return null;
        }
    }

    public static CrimeShortedDTO parseShortedCrime(Crime crime) {
        if (crime != null) {
            return new CrimeShortedDTO(
                    crime.getCrimeId(),
                    crime.getCrimeType(),
                    crime.getCrimeDate(),
                    crime.getCrimePlace()
            );
        } else {
            return null;
        }
    }
}
