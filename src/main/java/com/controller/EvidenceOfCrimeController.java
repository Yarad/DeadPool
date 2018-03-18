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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    //TODO: изменить, чтобы принимал id из get-параметров
    @CrossOrigin
    @RequestMapping(path = "/evidence_id/crime_id", method = RequestMethod.GET)
    public EvidenceOfCrimeExtendedDTO getEvidenceOfCrimeByEvidenceAndCrime() {
        EvidenceOfCrime evidenceOfCrime = evidenceOfCrimeService.getEvidenceOfCrimeByEvidenceAndCrime(1, 3);
        return EvidenceOfCrimeParser.parseEvidenceOfCrimeExtended(evidenceOfCrime);
    }

    //TODO: изменить, чтобы принимал id из get-параметров
    //TODO: потестировать, когда будет реализован метод получения списка EvidenceOfCrime
    @CrossOrigin
    @RequestMapping(path = "/evidence_id", method = RequestMethod.GET)
    public EvidenceExtendedDTO getEvidenceById() {
        Evidence evidence = evidenceService.getEvidenceById(1);
        List<EvidenceOfCrime> evidencesOfCrime = evidenceOfCrimeService.getEvidencesOfCrimeByEvidenceId(1);
        return EvidenceParser.parseEvidenceExtended(evidence, evidencesOfCrime);
    }
}
