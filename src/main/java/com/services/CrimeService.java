package com.services;

import com.DAO.interfaces.IDAOCrime;
import com.DTO.AddResult;
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

    @Override
    public List<Crime> getCrimesBetweenDates(LocalDate dateStart, LocalDate dateEnd) {
        return daoCrime.getCrimesBetweenDates(dateStart, dateEnd);
    }

    @Transactional
    @Override
    public Crime getCrimeById(long id) {
        return daoCrime.getCrimeById(id);
    }

    @Transactional
    @Override
    public AddResult addCrime(long criminalCaseId, String type, String description, LocalDate date, LocalTime time, String place) {
        Crime crime = new Crime();
        crime.setCriminalCaseId(criminalCaseId);
        try {
            crime.setCrimeType(type);
        } catch (IllegalArgumentException ex) {
            return new AddResult(false);
        }
        crime.setDescription(description);
        crime.setCrimeDate(date);
        crime.setCrimeTime(time);
        crime.setCrimePlace(place);
        return new AddResult(daoCrime.addCrime(crime), crime.getCrimeId());
    }

    @Transactional
    @Override
    public boolean updateCrime(long id, long criminalCaseId, String type, String description, LocalDate date, LocalTime time, String place) {
        Crime crime = new Crime();
        crime.setCrimeId(id);
        crime.setCriminalCaseId(criminalCaseId);
        try {
            crime.setCrimeType(type);
        } catch (IllegalArgumentException ex) {
            return false;
        }
        crime.setDescription(description);
        crime.setCrimeDate(date);
        crime.setCrimeTime(time);
        crime.setCrimePlace(place);
        return daoCrime.updateCrime(crime);
    }
}
