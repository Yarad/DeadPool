package com.controller;

import com.DTO.ParticipantFullInfoDTO;
import com.DTO.parsers.ParticipantParser;
import com.logic.Participant;
import com.services.interfaces.IParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/participants")
public class ParticipantController {
    @Autowired
    private IParticipantService participantService;

    //TODO: изменить, чтобы принимал id из get-параметров
    //TODO: потестить
    @CrossOrigin
    @RequestMapping(path = "/man_id/crime_id", method = RequestMethod.GET)
    public ParticipantFullInfoDTO getCriminalCaseById() {
        Participant participant = participantService.getParticipantByCrimeAndMan(1, 2);
        return ParticipantParser.parseParticipantFullInfo(participant);
    }
}
