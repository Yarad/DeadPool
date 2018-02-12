package com.DAO.interfaces;

import com.logic.Crime;

import java.util.HashMap;

public interface IDAOCrime {
    boolean addCrime(Crime crimeToAdd);
    Crime getCrimeById(int crimeId);
}
