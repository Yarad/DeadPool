package com.services;

import com.DAO.interfaces.IDAOCrime;
import com.logic.Crime;
import com.services.interfaces.ICrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class CrimeService implements ICrimeService {
    @Autowired
    private IDAOCrime daoCrime;

    @Transactional
    @Override
    public List<Crime> getAllCrimes() {
        return daoCrime.getAllCrimes();
    }

    @Transactional
    @Override
    public List<Crime> getCrimesByCriminalCase(long id) {
        return daoCrime.getCrimesByCriminalCase(id);
    }

    @Transactional
    @Override
    public Crime getCrimeById(long id) {
        return daoCrime.getCrimeById(id);
    }

    @Transactional
    @Override
    public boolean addCrime(long criminalCaseId, String type, String description, LocalDate date, LocalTime time, String place) {
        Crime crime = new Crime();
        crime.setCriminalCaseId(criminalCaseId);
        crime.setCrimeType(type);
        crime.setDescription(description);
        crime.setCrimeDate(date);
        crime.setCrimeTime(time);
        crime.setCrimePlace(place);
        return daoCrime.addCrime(crime);
    }

    @Transactional
    @Override
    public boolean updateCrime(long id, long criminalCaseId, String type, String description, LocalDate date, LocalTime time, String place) {
        Crime crime = new Crime();
        crime.setCrimeId(id);
        crime.setCriminalCaseId(criminalCaseId);
        crime.setCrimeType(type);
        crime.setDescription(description);
        crime.setCrimeDate(date);
        crime.setCrimeTime(time);
        crime.setCrimePlace(place);
        return daoCrime.updateCrime(crime);
    }
}
