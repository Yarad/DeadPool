package com.controller;

import com.DTO.CriminalCaseExtendedDTO;
import com.DTO.CriminalCaseObjectDTO;
import com.DTO.ListCriminalCasesDTO;
import com.DTO.parsers.CriminalCaseParser;
import com.logic.Crime;
import com.logic.CriminalCase;
import com.services.interfaces.ICrimeService;
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

@SpringBootApplication(scanBasePackages={"com"})
@RestController
@RequestMapping(value = "/criminal_cases")
public class CriminalCaseController {
    public static void main(String[] args) {
        SpringApplication.run(CriminalCaseController.class, args);
    }

    @Autowired
    private ICriminalCaseService crimeCasesService;

    @Autowired
    private ICrimeService crimeService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public ListCriminalCasesDTO getAllCriminalCases() {
        List<CriminalCase> inputCrimeCases = crimeCasesService.getAllCriminalCases();
        List<CriminalCaseObjectDTO> results = inputCrimeCases.stream()
                .map(cc -> CriminalCaseParser.parseCriminalCase(cc))
                .collect(Collectors.toList());
        return new ListCriminalCasesDTO(results);
    }

    @CrossOrigin
    @RequestMapping(path = "/solved", method = RequestMethod.GET)
    public ListCriminalCasesDTO getAllSolvedCriminalCases() {
        List<CriminalCase> inputCrimeCases = crimeCasesService.getAllSolvedCriminalCases();
        List<CriminalCaseObjectDTO> results = inputCrimeCases.stream()
                .map(cc -> CriminalCaseParser.parseCriminalCase(cc))
                .collect(Collectors.toList());
        return new ListCriminalCasesDTO(results);
    }

    @CrossOrigin
    @RequestMapping(path = "/unsolved", method = RequestMethod.GET)
    public ListCriminalCasesDTO getAllUnsolvedCriminalCases() {
        List<CriminalCase> inputCrimeCases = crimeCasesService.getAllUnsolvedCriminalCases();
        List<CriminalCaseObjectDTO> results = inputCrimeCases.stream()
                .map(cc -> CriminalCaseParser.parseCriminalCase(cc))
                .collect(Collectors.toList());
        return new ListCriminalCasesDTO(results);
    }

    @CrossOrigin
    @RequestMapping(path = "/open", method = RequestMethod.GET)
    public ListCriminalCasesDTO getAllOpenCriminalCases() {
        List<CriminalCase> inputCrimeCases = crimeCasesService.getAllOpenCriminalCases();
        List<CriminalCaseObjectDTO> results = inputCrimeCases.stream()
                .map(cc -> CriminalCaseParser.parseCriminalCase(cc))
                .collect(Collectors.toList());
        return new ListCriminalCasesDTO(results);
    }

    //TODO: изменить, чтобы принимал id из get-параметров
    @CrossOrigin
    @RequestMapping(path = "/id", method = RequestMethod.GET)
    public CriminalCaseExtendedDTO getCriminalCaseById() {
        CriminalCase criminalCase = crimeCasesService.getCriminalCaseById(2);
        List<Crime> crimes = crimeService.getCrimesByCriminalCase(2);
        return CriminalCaseParser.parseExtendedCriminalCase(criminalCase, crimes);
    }
}
