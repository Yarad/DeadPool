package com.controller;

import com.DTO.*;
import com.DTO.parsers.ManParser;
import com.DTO.parsers.ParticipantParser;
import com.logic.Man;
import com.logic.Participant;
import com.services.interfaces.IManService;
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
    public GenericDTO<ParticipantFullInfoDTO> getParticipantByManAndCrime(
            @PathVariable("man_id") long manId,
            @PathVariable("crime_id") long crimeId
    ) {
        Participant participant = participantService.getParticipantByCrimeAndMan(manId, crimeId);
        return (participant != null)
                ? new GenericDTO<ParticipantFullInfoDTO>(false, ParticipantParser.parseParticipantFullInfo(participant))
                : new GenericDTO<ParticipantFullInfoDTO>(true, null);
    }

    @CrossOrigin
    @RequestMapping(path = "/status_list", method = RequestMethod.GET)
    public ListEnumDTO getParticipantStatuses() {
        return ParticipantParser.getParticipantStatuses();
    }
}
