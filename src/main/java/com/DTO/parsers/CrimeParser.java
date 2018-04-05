package com.DTO.parsers;

import com.DTO.*;
import com.logic.Crime;
import com.logic.CrimeType;
import com.logic.EvidenceOfCrime;
import com.logic.Participant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class CrimeParser {
    private CrimeParser () {}

    public static CrimeObjectDTO parseCrime(Crime crime) {
        if (crime != null) {
            return new CrimeObjectDTO(
                    crime.getCrimeId(),
                    new EnumDTO(crime.getCrimeType().toString(), crime.getCrimeType().getName()),
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
                    new EnumDTO(crime.getCrimeType().toString(), crime.getCrimeType().getName()),
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
                    new EnumDTO(crime.getCrimeType().toString(), crime.getCrimeType().getName()),
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
                    new EnumDTO(crime.getCrimeType().toString(), crime.getCrimeType().getName()),
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

    public static ListEnumDTO getEvidenceTypes() {
        CrimeType[] types = CrimeType.class.getEnumConstants();
        List<EnumDTO> results = new ArrayList<>();
        for(CrimeType type: types) {
            results.add(new EnumDTO(type.toString(), type.getName()));
        }
        return new ListEnumDTO(results);
    }
}
