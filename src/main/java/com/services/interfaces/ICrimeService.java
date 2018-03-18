package com.services.interfaces;

import com.logic.Crime;

import java.util.List;

public interface ICrimeService {
    List<Crime> getAllCrimes();
    List<Crime> getCrimesByCriminalCase(long id);
    Crime getCrimeById(long id);
}
