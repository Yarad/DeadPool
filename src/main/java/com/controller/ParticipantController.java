package com.controller;

import com.DTO.ParticipantFullInfoDTO;
import com.DTO.parsers.ParticipantParser;
import com.logic.Participant;
import com.services.interfaces.IParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/participants")
public class ParticipantController {
    @Autowired
    private IParticipantService participantService;

    //TODO: потестить
    @CrossOrigin
    @RequestMapping(path = "/{man_id}/{crime_id}", method = RequestMethod.GET)
    public ParticipantFullInfoDTO getParticipantByManAndCrime(
            @PathVariable("man_id") long manId,
            @PathVariable("crime_id") long crimeId
    ) {
        Participant participant = participantService.getParticipantByCrimeAndMan(manId, crimeId);
        return ParticipantParser.parseParticipantFullInfo(participant);
    }
}
