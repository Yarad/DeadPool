package com.controller;

import com.DTO.EvidenceExtendedDTO;
import com.DTO.EvidenceOfCrimeExtendedDTO;
import com.DTO.EvidenceOfCrimeShortedWithCrimeDTO;
import com.DTO.ListEvidenceOfCrimeShortedWithCrimeList;
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

    //TODO: потестить с реальными данными
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
