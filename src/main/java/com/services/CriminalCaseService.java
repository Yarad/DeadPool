package com.services;

import com.DAO.DAOCriminalCase;
import com.DAO.SQLConnection;
import com.DAO.interfaces.IDAOCriminalCase;
import com.logic.CriminalCase;
import com.services.interfaces.ICriminalCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import java.util.List;

@Service
public class CriminalCaseService implements ICriminalCaseService {
    //TODO: change to better when Andrew will finish his work
    private DAOCriminalCase daoCriminalCase;

    public CriminalCaseService() {
        daoCriminalCase = new DAOCriminalCase();
        daoCriminalCase.setConnectionToUse(new SQLConnection());
    }

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
}
