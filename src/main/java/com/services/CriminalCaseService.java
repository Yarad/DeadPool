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

    private DAOCriminalCase daoCriminalCase;

    public List<CriminalCase> getAllCrimes() {
        if (daoCriminalCase == null) {
            daoCriminalCase = new DAOCriminalCase();
            daoCriminalCase.setConnectionToUse(new SQLConnection());
        }
        return daoCriminalCase.getAllCriminalCases();
    }
}
