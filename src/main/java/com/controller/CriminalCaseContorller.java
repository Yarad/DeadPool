package com.controller;

import com.DTO.CriminalCaseObjectDTO;
import com.DTO.ListCriminalCasesDTO;
import com.DTO.parsers.CriminalCaseParser;
import com.logic.CriminalCase;
import com.services.interfaces.ICriminalCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication(scanBasePackages={"com", "com.controller", "com.services.*"})
@RestController
@RequestMapping(value = "/criminal_cases")
public class CriminalCaseContorller {
    public static void main(String[] args) {
        SpringApplication.run(CriminalCaseContorller.class, args);
    }

    @Autowired
    private ICriminalCaseService crimeCasesService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public ListCriminalCasesDTO getAllCrimes() {
        List<CriminalCase> inputCrimeCases = crimeCasesService.getAllCriminalCases();
        List<CriminalCaseObjectDTO> results = inputCrimeCases.stream()
                .map(cc -> CriminalCaseParser.parseCriminalCase(cc))
                .collect(Collectors.toList());
        return new ListCriminalCasesDTO(results);
    }

    @CrossOrigin
    @RequestMapping(path = "/solved", method = RequestMethod.GET)
    public ListCriminalCasesDTO getAllSolvedCrimes() {
        List<CriminalCase> inputCrimeCases = crimeCasesService.getAllSolvedCriminalCases();
        List<CriminalCaseObjectDTO> results = inputCrimeCases.stream()
                .map(cc -> CriminalCaseParser.parseCriminalCase(cc))
                .collect(Collectors.toList());
        return new ListCriminalCasesDTO(results);
    }

    @CrossOrigin
    @RequestMapping(path = "/unsolved", method = RequestMethod.GET)
    public ListCriminalCasesDTO getAllUnsolvedCrimes() {
        List<CriminalCase> inputCrimeCases = crimeCasesService.getAllUnsolvedCriminalCases();
        List<CriminalCaseObjectDTO> results = inputCrimeCases.stream()
                .map(cc -> CriminalCaseParser.parseCriminalCase(cc))
                .collect(Collectors.toList());
        return new ListCriminalCasesDTO(results);
    }

    @CrossOrigin
    @RequestMapping(path = "/open", method = RequestMethod.GET)
    public ListCriminalCasesDTO getAllOpenCrimes() {
        List<CriminalCase> inputCrimeCases = crimeCasesService.getAllOpenCriminalCases();
        List<CriminalCaseObjectDTO> results = inputCrimeCases.stream()
                .map(cc -> CriminalCaseParser.parseCriminalCase(cc))
                .collect(Collectors.toList());
        return new ListCriminalCasesDTO(results);
    }
}
