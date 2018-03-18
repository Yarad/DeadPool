package com.DTO.parsers;

import com.DTO.ParticipantByCrimeDTO;
import com.DTO.ParticipantFullInfoDTO;
import com.logic.Participant;

public final class ParticipantParser {
    private ParticipantParser () {}

    public static ParticipantByCrimeDTO parseParticipantByCrime(Participant participant) {
        if (participant != null) {
            return new ParticipantByCrimeDTO(
                    ManParser.parseManShorted(participant),
                    participant.participantStatus.toString()
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
                    participant.participantStatus.toString(),
                    participant.getPhotoPath(),
                    participant.getAlibi(),
                    participant.getWitnessReport()
            );
        } else {
            return null;
        }
    }
}
