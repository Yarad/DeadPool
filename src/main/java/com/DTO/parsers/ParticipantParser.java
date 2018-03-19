package com.DTO.parsers;

import com.DTO.ParticipantByCrimeDTO;
import com.DTO.ParticipantByManDTO;
import com.DTO.ParticipantFullInfoDTO;
import com.logic.Participant;

public final class ParticipantParser {
    private ParticipantParser () {}

    public static ParticipantByCrimeDTO parseParticipantByCrime(Participant participant) {
        if (participant != null) {
            return new ParticipantByCrimeDTO(
                    ManParser.parseManShorted(participant),
                    participant.participantStatus.getName()
            );
        } else {
            return null;
        }
    }

    public static ParticipantByManDTO parseParticipantByMan(Participant participant) {
        if (participant != null) {
            return new ParticipantByManDTO(
                    CrimeParser.parseShortedCrimeForOthers(participant.getCrime()),
                    participant.participantStatus.getName()
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
                    participant.participantStatus.getName(),
                    participant.getPhotoPath(),
                    participant.getDateAdded(),
                    participant.getAlibi(),
                    participant.getWitnessReport()
            );
        } else {
            return null;
        }
    }
}
