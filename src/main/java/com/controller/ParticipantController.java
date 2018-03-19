package com.controller;

import com.DTO.ManExtendedDTO;
import com.DTO.OperationResultDTO;
import com.DTO.ParticipantFullInfoDTO;
import com.DTO.ParticipantInputDTO;
import com.DTO.parsers.ManParser;
import com.DTO.parsers.ParticipantParser;
import com.logic.Man;
import com.logic.Participant;
import com.services.interfaces.IParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/participants")
public class ParticipantController {
    @Autowired
    private IParticipantService participantService;

    @CrossOrigin
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public OperationResultDTO addParticipant(@RequestBody ParticipantInputDTO participant) {
        boolean result = participantService.addParticipant(
                participant.getMan().getId(),
                participant.getCrime().getId(),
                participant.getStatus(),
                participant.getDateAdded(),
                participant.getAlibi(),
                participant.getWitnessReport()
        );
        return new OperationResultDTO(result);
    }

    @CrossOrigin
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public OperationResultDTO updateParticipant(@RequestBody ParticipantInputDTO participant) {
        boolean result = participantService.updateParticipant(
                participant.getMan().getId(),
                participant.getCrime().getId(),
                participant.getStatus(),
                participant.getDateAdded(),
                participant.getAlibi(),
                participant.getWitnessReport()
        );
        return new OperationResultDTO(result);
    }

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

    //TODO: потестить
    @CrossOrigin
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ManExtendedDTO getManFullInfo(@PathVariable("id") long id) {
        Man man = participantService.getFullManInfo(id);
        List<Participant> participants = participantService.getParticipantsByManId(id);
        return ManParser.parseManExtended(man, participants);
    }
}
