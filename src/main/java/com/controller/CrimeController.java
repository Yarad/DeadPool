package com.controller;

import com.DTO.CrimeExtendedDTO;
import com.DTO.CrimeObjectDTO;
import com.DTO.ListCrimesDTO;
import com.DTO.parsers.CrimeParser;
import com.logic.Crime;
import com.logic.EvidenceOfCrime;
import com.logic.Participant;
import com.services.CrimeService;
import com.services.interfaces.ICrimeService;
import com.services.interfaces.IEvidenceOfCrimeService;
import com.services.interfaces.IParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/crimes")
public class CrimeController {
    @Autowired
    private ICrimeService crimeService;

    @Autowired
    private IParticipantService participantService;

    @Autowired
    private IEvidenceOfCrimeService evidenceOfCrimeService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public ListCrimesDTO getAllCrimes() {
        List<Crime> inputCrimes = crimeService.getAllCrimes();
        List<CrimeObjectDTO> results = inputCrimes.stream()
                .map(curCrime -> CrimeParser.parseCrime(curCrime))
                .collect(Collectors.toList());
        return new ListCrimesDTO(results);
    }

    //TODO: изменить, чтобы принимал id из get-параметров
    @CrossOrigin
    @RequestMapping(path = "/id", method = RequestMethod.GET)
    public CrimeExtendedDTO getCrimeById() {
        Crime crime = crimeService.getCrimeById(2);
        List<Participant> participants = participantService.getParticipantsByCrimeId(2);
        List<EvidenceOfCrime> evidencesOfCrime = evidenceOfCrimeService.getEvidencesOfCrimeByCrimeId(2);
        return CrimeParser.parseCrimeFullInformation(crime, participants, evidencesOfCrime);
    }
}
