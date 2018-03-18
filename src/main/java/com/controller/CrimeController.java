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
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public CrimeExtendedDTO getCrimeById(@PathVariable("id") long id) {
        Crime crime = crimeService.getCrimeById(id);
        List<Participant> participants = participantService.getParticipantsByCrimeId(id);
        List<EvidenceOfCrime> evidencesOfCrime = evidenceOfCrimeService.getEvidencesOfCrimeByCrimeId(id);
        return CrimeParser.parseCrimeFullInformation(crime, participants, evidencesOfCrime);
    }
}
