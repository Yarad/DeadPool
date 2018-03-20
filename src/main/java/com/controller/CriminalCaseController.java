package com.controller;

import com.DTO.*;
import com.DTO.parsers.CriminalCaseParser;
import com.logic.Crime;
import com.logic.CriminalCase;
import com.services.interfaces.ICrimeService;
import com.services.interfaces.ICriminalCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/criminal_cases")
public class CriminalCaseController {
    @Autowired
    private ICriminalCaseService crimeCasesService;

    @Autowired
    private ICrimeService crimeService;

    @CrossOrigin
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public OperationResultDTO addCriminalCase(@RequestBody CriminalCaseInputDTO criminalCase) {
        boolean result = crimeCasesService.addCriminalCase(
                criminalCase.getDetective().getId(),
                criminalCase.getNumber(),
                criminalCase.getCreateDate(),
                criminalCase.getClosed(),
                criminalCase.getCloseDate()
        );
        return new OperationResultDTO(result);
    }

    @CrossOrigin
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public OperationResultDTO updateCriminalCase(@RequestBody CriminalCaseInputWithIdDTO criminalCase) {
        boolean result = crimeCasesService.updateCriminalCase(
                criminalCase.getId(),
                criminalCase.getDetective().getId(),
                criminalCase.getNumber(),
                criminalCase.getCreateDate(),
                criminalCase.getClosed(),
                criminalCase.getCloseDate()
        );
        return new OperationResultDTO(result);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public GenericDTO<ListCriminalCasesDTO> getAllCriminalCases() {
        List<CriminalCase> inputCrimeCases = crimeCasesService.getAllCriminalCases();
        List<CriminalCaseObjectDTO> results = inputCrimeCases.stream()
                .map(cc -> CriminalCaseParser.parseCriminalCase(cc))
                .collect(Collectors.toList());
        return new GenericDTO<ListCriminalCasesDTO>(false, new ListCriminalCasesDTO(results));
    }

    @CrossOrigin
    @RequestMapping(path = "/solved", method = RequestMethod.GET)
    public GenericDTO<ListCriminalCasesDTO> getAllSolvedCriminalCases() {
        List<CriminalCase> inputCrimeCases = crimeCasesService.getAllSolvedCriminalCases();
        List<CriminalCaseObjectDTO> results = inputCrimeCases.stream()
                .map(cc -> CriminalCaseParser.parseCriminalCase(cc))
                .collect(Collectors.toList());
        return new GenericDTO<ListCriminalCasesDTO>(false, new ListCriminalCasesDTO(results));
    }

    @CrossOrigin
    @RequestMapping(path = "/unsolved", method = RequestMethod.GET)
    public GenericDTO<ListCriminalCasesDTO> getAllUnsolvedCriminalCases() {
        List<CriminalCase> inputCrimeCases = crimeCasesService.getAllUnsolvedCriminalCases();
        List<CriminalCaseObjectDTO> results = inputCrimeCases.stream()
                .map(cc -> CriminalCaseParser.parseCriminalCase(cc))
                .collect(Collectors.toList());
        return new GenericDTO<ListCriminalCasesDTO>(false, new ListCriminalCasesDTO(results));
    }

    @CrossOrigin
    @RequestMapping(path = "/open", method = RequestMethod.GET)
    public GenericDTO<ListCriminalCasesDTO> getAllOpenCriminalCases() {
        List<CriminalCase> inputCrimeCases = crimeCasesService.getAllOpenCriminalCases();
        List<CriminalCaseObjectDTO> results = inputCrimeCases.stream()
                .map(cc -> CriminalCaseParser.parseCriminalCase(cc))
                .collect(Collectors.toList());
        return new GenericDTO<ListCriminalCasesDTO>(false, new ListCriminalCasesDTO(results));
    }

    @CrossOrigin
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public GenericDTO<CriminalCaseExtendedDTO> getCriminalCaseById(@PathVariable("id") long id) {
        CriminalCase criminalCase = crimeCasesService.getCriminalCaseById(id);
        List<Crime> crimes = crimeService.getCrimesByCriminalCase(id);
        return (criminalCase != null)
                ? new GenericDTO<CriminalCaseExtendedDTO>(false, CriminalCaseParser.parseExtendedCriminalCase(criminalCase, crimes))
                : new GenericDTO<CriminalCaseExtendedDTO>(true, null);
    }
}
