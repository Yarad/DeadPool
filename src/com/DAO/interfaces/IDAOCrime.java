package com.DAO.interfaces;

import com.logic.Crime;

public interface IDAOCrime {
    boolean addCrime(Crime crimeToAdd);
    Crime getCrimeById(int crimeId);
}
