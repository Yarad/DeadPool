package com.DTO.parsers;

import com.DTO.CrimeExtendedDTO;
import com.DTO.CrimeForOthersShortedDTO;
import com.DTO.CrimeObjectDTO;
import com.DTO.CrimeShortedDTO;
import com.logic.Crime;
import com.logic.EvidenceOfCrime;
import com.logic.Participant;

import java.util.List;
import java.util.stream.Collectors;

public final class CrimeParser {
    private CrimeParser () {}

    public static CrimeObjectDTO parseCrime(Crime crime) {
        if (crime != null) {
            return new CrimeObjectDTO(
                    crime.getCrimeId(),
                    crime.getCrimeType().toString(),
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
                    crime.getCrimeType().toString(),
                    crime.getCrimeDate(),
                    crime.getCrimePlace()
            );
        } else {
            return null;
        }
    }

    public static CrimeForOthersShortedDTO parseShortedCrimeForOthers(Crime crime) {
        if (crime != null) {
            return new CrimeForOthersShortedDTO(
                    crime.getCrimeId(),
                    crime.getCrimeType().toString(),
                    CriminalCaseParser.parseCriminalCaseOnlyNumber(crime.getParentCriminalCase())
            );
        } else {
            return null;
        }
    }

    public static CrimeExtendedDTO parseCrimeFullInformation(Crime crime, List<Participant> participants,
                                                             List<EvidenceOfCrime> evidencesOfCrime) {
        if (crime != null) {
            return new CrimeExtendedDTO(
                    crime.getCrimeId(),
                    crime.getCrimeType().toString(),
                    crime.getCrimeDate(),
                    crime.getCrimePlace(),
                    CriminalCaseParser.parseShortedCriminalCaseWithDetective(crime.getParentCriminalCase()),
                    crime.getDescription(),
                    crime.getCrimeTime(),
                    participants.stream()
                            .map(p -> ParticipantParser.parseParticipantByCrime(p))
                            .collect(Collectors.toList()),
                    evidencesOfCrime.stream()
                            .map(ec -> EvidenceOfCrimeParser.parseEvidenceOfCrimeShorted(ec))
                            .collect(Collectors.toList())
            );
        } else {
            return null;
        }
    }
}
