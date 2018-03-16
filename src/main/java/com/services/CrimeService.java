package com.services;

import com.DAO.DAOCrime;
import com.DAO.SQLConnection;
import com.logic.Crime;
import com.services.interfaces.ICrimeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CrimeService implements ICrimeService {
    @Autowired
    private DAOCrime daoCrime;

    public CrimeService() {
        daoCrime = new DAOCrime();
        daoCrime.setConnectionToUse(new SQLConnection());
    }

    @Override
    public List<Crime> getAllCrimes() {
        return daoCrime.getAllCrimes();
    }
}
