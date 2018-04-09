package com.controller;

import com.DTO.*;
import com.DTO.parsers.CrimeParser;
import com.logic.Crime;
import com.logic.EvidenceOfCrime;
import com.logic.Participant;
import com.security.annotations.IsDetective;
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

    @IsDetective
    @CrossOrigin
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public OperationResultAddDTO addCrime(@RequestBody CrimeInputDTO crime) {
        AddResult result = crimeService.addCrime(
                crime.getCriminalCase().getId(),
                crime.getType(),
                crime.getDescription(),
                crime.getDate(),
                crime.getTime(),
                crime.getPlace()
        );
        return new OperationResultAddDTO(result);
    }

    @IsDetective
    @CrossOrigin
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public OperationResultDTO updateCrime(@RequestBody CrimeInputWithIdDTO crime) {
        boolean result = crimeService.updateCrime(
                crime.getId(),
                crime.getCriminalCase().getId(),
                crime.getType(),
                crime.getDescription(),
                crime.getDate(),
                crime.getTime(),
                crime.getPlace()
        );
        return new OperationResultDTO(result);
    }

    @IsDetective
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public GenericDTO<ListCrimesDTO> getAllCrimes() {
        List<Crime> inputCrimes = crimeService.getAllCrimes();
        List<CrimeObjectDTO> results = inputCrimes.stream()
                .map(curCrime -> CrimeParser.parseCrime(curCrime))
                .collect(Collectors.toList());
        return new GenericDTO<>(false, new ListCrimesDTO(results));
    }

    @IsDetective
    @CrossOrigin
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public GenericDTO<CrimeExtendedDTO> getCrimeById(@PathVariable("id") long id) {
        Crime crime = crimeService.getCrimeById(id);
        List<Participant> participants = participantService.getParticipantsByCrimeId(id);
        List<EvidenceOfCrime> evidencesOfCrime = evidenceOfCrimeService.getEvidencesOfCrimeByCrimeId(id);
        return (crime != null)
                ? new GenericDTO<>(false, CrimeParser.parseCrimeFullInformation(crime, participants, evidencesOfCrime))
                : new GenericDTO<>(true, null);
    }

    @IsDetective
    @CrossOrigin
    @RequestMapping(path = "/types_list", method = RequestMethod.GET)
    public ListEnumDTO getParticipantStatuses() {
        return CrimeParser.getEvidenceTypes();
    }
}
