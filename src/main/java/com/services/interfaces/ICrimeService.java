package com.services.interfaces;

import com.DTO.AddResult;
import com.logic.Crime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ICrimeService {
    List<Crime> getAllCrimes();
    List<Crime> getCrimesByCriminalCase(long id);
    List<Crime> getCrimesBetweenDates(LocalDate dateStart, LocalDate dateEnd);
    Crime getCrimeById(long id);
    AddResult addCrime(long criminalCaseId, String type, String description, LocalDate date, LocalTime time, String place);
    boolean updateCrime(long id, long criminalCaseId, String type, String description, LocalDate date, LocalTime time, String place);
}
