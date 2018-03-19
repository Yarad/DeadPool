package com.controller;

import com.DTO.*;
import com.DTO.parsers.EvidenceOfCrimeParser;
import com.DTO.parsers.EvidenceParser;
import com.logic.Evidence;
import com.logic.EvidenceOfCrime;
import com.services.interfaces.IEvidenceOfCrimeService;
import com.services.interfaces.IEvidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/evidences")
public class EvidenceOfCrimeController {
    @Autowired
    private IEvidenceOfCrimeService evidenceOfCrimeService;

    @Autowired
    private IEvidenceService evidenceService;

    @CrossOrigin
    @RequestMapping(path = "/add_single", method = RequestMethod.POST)
    public OperationResultDTO addEvidenceSingle(@RequestBody EvidenceInputDTO evidence) {
        boolean result = evidenceService.addEvidence(
                evidence.getName(),
                evidence.getDescription()
        );
        return new OperationResultDTO(result);
    }

    @CrossOrigin
    @RequestMapping(path = "/update_single", method = RequestMethod.POST)
    public OperationResultDTO updateEvidenceSingle(@RequestBody EvidenceInputWithIdDTO evidence) {
        boolean result = evidenceService.updateEvidence(
                evidence.getId(),
                evidence.getName(),
                evidence.getDescription()
        );
        return new OperationResultDTO(result);
    }

    @CrossOrigin
    @RequestMapping(path = "/add_for_crime", method = RequestMethod.POST)
    public OperationResultDTO addEvidenceForCrime(@RequestBody EvidenceOfCrimeInputDTO evidenceOfCrime) {
        boolean result = evidenceOfCrimeService.addEvidenceOfCrime(
                evidenceOfCrime.getEvidence().getId(),
                evidenceOfCrime.getCrime().getId(),
                evidenceOfCrime.getType(),
                evidenceOfCrime.getDateAdded(),
                evidenceOfCrime.getDetails(),
                evidenceOfCrime.getPhotoPath()
        );
        return new OperationResultDTO(result);
    }

    @CrossOrigin
    @RequestMapping(path = "/update_for_crime", method = RequestMethod.POST)
    public OperationResultDTO updateEvidenceForCrime(@RequestBody EvidenceOfCrimeInputDTO evidenceOfCrime) {
        boolean result = evidenceOfCrimeService.updateEvidenceOfCrime(
                evidenceOfCrime.getEvidence().getId(),
                evidenceOfCrime.getCrime().getId(),
                evidenceOfCrime.getType(),
                evidenceOfCrime.getDateAdded(),
                evidenceOfCrime.getDetails(),
                evidenceOfCrime.getPhotoPath()
        );
        return new OperationResultDTO(result);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public ListEvidenceOfCrimeShortedWithCrimeList getAllCrimes() {
        List<EvidenceOfCrime> inputEvidencesOfCrime = evidenceOfCrimeService.getAllEvidencesOfCrime();
        List<EvidenceOfCrimeShortedWithCrimeDTO> results = inputEvidencesOfCrime.stream()
                .map(curEvidence -> EvidenceOfCrimeParser.parseEvidenceOfCrimeShortedWithCrime(curEvidence))
                .collect(Collectors.toList());
        return new ListEvidenceOfCrimeShortedWithCrimeList(results);
    }

    @CrossOrigin
    @RequestMapping(path = "/{evidence_id}/{crime_id}", method = RequestMethod.GET)
    public EvidenceOfCrimeExtendedDTO getEvidenceOfCrimeByEvidenceAndCrime(
            @PathVariable("evidence_id") long evidenceId,
            @PathVariable("crime_id") long crimeId
    ) {
        EvidenceOfCrime evidenceOfCrime = evidenceOfCrimeService.getEvidenceOfCrimeByEvidenceAndCrime(evidenceId, crimeId);
        return EvidenceOfCrimeParser.parseEvidenceOfCrimeExtended(evidenceOfCrime);
    }

    //TODO: потестировать, когда будет реализован метод получения списка EvidenceOfCrime
    @CrossOrigin
    @RequestMapping(path = "/{evidence_id}", method = RequestMethod.GET)
    public EvidenceExtendedDTO getEvidenceById(@PathVariable("evidence_id") long id) {
        Evidence evidence = evidenceService.getEvidenceById(id);
        List<EvidenceOfCrime> evidencesOfCrime = evidenceOfCrimeService.getEvidencesOfCrimeByEvidenceId(id);
        return EvidenceParser.parseEvidenceExtended(evidence, evidencesOfCrime);
    }
}
