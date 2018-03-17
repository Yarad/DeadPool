package com.controller;

import com.DTO.CrimeObjectDTO;
import com.DTO.ListCrimesDTO;
import com.DTO.parsers.CrimeParser;
import com.logic.Crime;
import com.services.CrimeService;
import com.services.interfaces.ICrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/crimes")
public class CrimeController {
    @Autowired
    private ICrimeService crimeService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public ListCrimesDTO getAllCrimes() {
        List<Crime> inputCrimes = crimeService.getAllCrimes();
        List<CrimeObjectDTO> results = inputCrimes.stream()
                .map(curCrime -> CrimeParser.parseCrime(curCrime))
                .collect(Collectors.toList());
        return new ListCrimesDTO(results);
    }
}
