package com.services.interfaces;

import com.logic.CriminalCase;

import java.util.List;

public interface ICriminalCaseService {
    List<CriminalCase> getAllCrimes();
    List<CriminalCase> getAllSolvedCrimes();
    List<CriminalCase> getAllUnsolvedCrimes();
    List<CriminalCase> getAllOpenCrimes();
}
