package com.controller;

import com.DTO.EvidenceOfCrimeShortedWithCrimeDTO;
import com.DTO.ListEvidenceOfCrimeShortedWithCrimeList;
import com.DTO.parsers.EvidenceOfCrimeParser;
import com.logic.EvidenceOfCrime;
import com.services.interfaces.IEvidenceOfCrimeService;
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
}
