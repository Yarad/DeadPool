package com.services;

import com.DAO.DAOCriminalCase;
import com.logic.CriminalCase;
import com.services.interfaces.ICriminalCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriminalCaseService implements ICriminalCaseService {
    @Autowired
    private DAOCriminalCase daoCriminalCase;

    @Override
    public List<CriminalCase> getAllCriminalCases() {
        return daoCriminalCase.getAllCriminalCases();
    }

    @Override
    public List<CriminalCase> getAllSolvedCriminalCases() {
        return daoCriminalCase.getAllClosedSolvedCrimes();
    }

    @Override
    public List<CriminalCase> getAllUnsolvedCriminalCases() {
        return daoCriminalCase.getAllClosedUnsolvedCrimes();
    }

    @Override
    public List<CriminalCase> getAllOpenCriminalCases() {
        return daoCriminalCase.getAllOpenCrimes();
    }

    @Override
    public CriminalCase getCriminalCaseById(long id) {
        return daoCriminalCase.getCriminalCaseById(id);
    }
}
