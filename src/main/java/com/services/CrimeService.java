package com.services;

import com.DAO.interfaces.IDAOCrime;
import com.logic.Crime;
import com.services.interfaces.ICrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
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
