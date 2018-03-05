package com.controller;

import com.DTO.CriminalCaseObjectDTO;
import com.DTO.DetectivePersonDTO;
import com.DTO.ListCriminalCasesDTO;
import com.logic.CriminalCase;
import com.logic.Detective;
import com.services.CriminalCaseService;
import com.services.interfaces.ICriminalCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication(scanBasePackages = "com")
@RestController
@RequestMapping(value = "/criminal_cases")
public class CriminalCaseContorller {
    public static void main(String[] args) {
        SpringApplication.run(CriminalCaseContorller.class, args);
    }

    @Autowired
    private ICriminalCaseService crimeCasesService;

    @RequestMapping(method = RequestMethod.GET)
    public ListCriminalCasesDTO getAllCrimes() {
        List<CriminalCaseObjectDTO> results = new ArrayList<>();
        if (crimeCasesService == null)
            crimeCasesService = new CriminalCaseService();
        try {
            List<CriminalCase> inputCrimeCases = crimeCasesService.getAllCrimes();
            results = inputCrimeCases.stream()
                    .map( cc -> parseCriminalCase(cc))
                    .collect(Collectors.toList());
        } catch (Exception e) {

        }
        return new ListCriminalCasesDTO(results);
    }

    private DetectivePersonDTO parseDetectivePerson(Detective detective) {
        return new DetectivePersonDTO(detective.getManId(), detective.getName(), detective.getPassword());
    }

    private CriminalCaseObjectDTO parseCriminalCase(CriminalCase crimeCase) {
        String type;
        if (!crimeCase.isClosed()) {
            type = "открыто";
        } else {
            if (crimeCase.getCloseDate() != null) {
                type = "раскрыто";
            } else {
                type = "не раскрыто";
            }
        }
        return new CriminalCaseObjectDTO(
                crimeCase.getCriminalCaseId(),
                crimeCase.getCriminalCaseNumber(),
                type,
                crimeCase.getCreateDate(),
                crimeCase.getCloseDate(),
                (crimeCase.getParentDetective() != null) ? parseDetectivePerson(crimeCase.getParentDetective()) : null
        );
    }
}
