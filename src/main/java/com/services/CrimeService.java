package com.services;

import com.DAO.interfaces.IDAOCrime;
import com.logic.Crime;
import com.services.interfaces.ICrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrimeService implements ICrimeService {
    @Autowired
    private IDAOCrime daoCrime;

    @Override
    public List<Crime> getAllCrimes() {
        return daoCrime.getAllCrimes();
    }

    @Override
    public List<Crime> getCrimesByCriminalCase(long id) {
        return daoCrime.getCrimesByCriminalCase(id);
    }

    @Override
    public Crime getCrimeById(long id) {
        return daoCrime.getCrimeById(id);
    }
}
