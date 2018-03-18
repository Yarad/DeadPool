package com.DTO.parsers;

import com.DTO.ManExtendedDTO;
import com.DTO.ManOnlyPersonDTO;
import com.DTO.ManShortedDTO;
import com.logic.Man;
import com.logic.Participant;

import java.util.List;
import java.util.stream.Collectors;

public final class ManParser {
    private ManParser () {}

    public static ManOnlyPersonDTO parseManOnlyPerson(Man man) {
        if (man != null) {
            return new ManOnlyPersonDTO(man.getManId(), man.getName(), man.getSurname());
        } else {
            return null;
        }
    }

    public static ManShortedDTO parseManShorted(Man man) {
        if (man != null) {
            return new ManShortedDTO(man.getManId(), man.getName(), man.getSurname(), man.getPhotoPath());
        } else {
            return null;
        }
    }

    //ManExtendedDTO
    public static ManExtendedDTO parseManExtended(Man man, List<Participant> participants) {
        if (man != null) {
            return new ManExtendedDTO(
                    man.getManId(),
                    man.getName(),
                    man.getSurname(),
                    man.getPhotoPath(),
                    man.getBirthDay(),
                    man.getHomeAddress(),
                    participants.stream()
                            .map(p -> ParticipantParser.parseParticipantByMan(p))
                            .collect(Collectors.toList())
            );
        } else {
            return null;
        }
    }
}
