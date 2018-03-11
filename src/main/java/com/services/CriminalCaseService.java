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
    private static final Logger LOGGER = Logger.getLogger(CriminalCaseService.class);

    //TODO: change to better when Andrew will finish his work
    private DAOCriminalCase daoCriminalCase;

    @Override
    public List<CriminalCase> getAllCrimes() {
        if (daoCriminalCase == null) {
            daoCriminalCase = new DAOCriminalCase();
            daoCriminalCase.setConnectionToUse(new SQLConnection());
        }
        return daoCriminalCase.getAllCriminalCases();
    }

    @Override
    public List<CriminalCase> getAllSolvedCrimes() {
        if (daoCriminalCase == null) {
            daoCriminalCase = new DAOCriminalCase();
            daoCriminalCase.setConnectionToUse(new SQLConnection());
        }
        return daoCriminalCase.getAllClosedSolvedCrimes();
    }

    @Override
    public List<CriminalCase> getAllUnsolvedCrimes() {
        if (daoCriminalCase == null) {
            daoCriminalCase = new DAOCriminalCase();
            daoCriminalCase.setConnectionToUse(new SQLConnection());
        }
        return daoCriminalCase.getAllClosedUnsolvedCrimes();
    }

    @Override
    public List<CriminalCase> getAllOpenCrimes() {
        if (daoCriminalCase == null) {
            daoCriminalCase = new DAOCriminalCase();
            daoCriminalCase.setConnectionToUse(new SQLConnection());
        }
        return daoCriminalCase.getAllOpenCrimes();
    }
}
