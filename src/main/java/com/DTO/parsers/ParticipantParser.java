package com.DTO.parsers;

import com.DTO.*;
import com.logic.Participant;
import com.logic.ParticipantStatus;

import java.util.ArrayList;
import java.util.List;

public final class ParticipantParser {
    private ParticipantParser () {}

    public static ParticipantByCrimeDTO parseParticipantByCrime(Participant participant) {
        if (participant != null) {
            return new ParticipantByCrimeDTO(
                    ManParser.parseManShorted(participant),
                    participant.getParticipantStatus().getName()
            );
        } else {
            return null;
        }
    }

    public static ParticipantByManDTO parseParticipantByMan(Participant participant) {
        if (participant != null) {
            return new ParticipantByManDTO(
                    CrimeParser.parseShortedCrimeForOthers(participant.getCrime()),
                    participant.getParticipantStatus().getName()
            );
        } else {
            return null;
        }
    }

    public static ParticipantFullInfoDTO parseParticipantFullInfo(Participant participant) {
        if (participant != null) {
            return new ParticipantFullInfoDTO(
                    ManParser.parseManOnlyPerson(participant),
                    CrimeParser.parseShortedCrimeForOthers(participant.getCrime()),
                    participant.getParticipantStatus().getName(),
                    participant.getPhotoPath(),
                    participant.getDateAdded(),
                    participant.getAlibi(),
                    participant.getWitnessReport()
            );
        } else {
            return null;
        }
    }

    public static ListEnumDTO getParticipantStatuses() {
        ParticipantStatus[] types = ParticipantStatus.class.getEnumConstants();
        List<EnumDTO> results = new ArrayList<>();
        for(ParticipantStatus type: types) {
            results.add(new EnumDTO(type.toString(), type.getName()));
        }
        return new ListEnumDTO(results);
    }
}
