package com.services;

import com.DAO.interfaces.IDAOCriminalCase;
import com.logic.CriminalCase;
import com.services.interfaces.ICriminalCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CriminalCaseService implements ICriminalCaseService {
    @Autowired
    private IDAOCriminalCase daoCriminalCase;

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

    @Override
    public boolean addCriminalCase(long detectiveId, String number, LocalDate createDate, boolean closed, LocalDate closeDate) {
        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setDetectiveId(detectiveId);
        criminalCase.setCriminalCaseNumber(number);
        criminalCase.setClosed(closed);
        criminalCase.setCreateDate(createDate);
        criminalCase.setCloseDate(closeDate);
        return daoCriminalCase.addCriminalCase(criminalCase);
    }

    @Override
    public boolean updateCriminalCase(long id, long detectiveId, String number, LocalDate createDate, boolean closed, LocalDate closeDate) {
        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setCriminalCaseId(id);
        criminalCase.setDetectiveId(detectiveId);
        criminalCase.setCriminalCaseNumber(number);
        criminalCase.setClosed(closed);
        criminalCase.setCreateDate(createDate);
        criminalCase.setCloseDate(closeDate);
        return daoCriminalCase.updateCriminalCase(criminalCase);
    }
}
