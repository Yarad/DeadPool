package com.DTO.parsers;

import com.DTO.ParticipantByCrimeDTO;
import com.logic.Participant;

public final class ParticipantParser {
    private ParticipantParser () {}

    public static ParticipantByCrimeDTO parseParticipantByCrime(Participant participant) {
        if (participant != null) {
            return new ParticipantByCrimeDTO(ManParser.parseManShorted(participant), participant.participantStatus.toString());
        } else {
            return null;
        }
    }
}
