package com.services.interfaces;

import com.logic.CriminalCase;

import java.util.List;

public interface ICriminalCaseService {
    List<CriminalCase> getAllCriminalCases();
    List<CriminalCase> getAllSolvedCriminalCases();
    List<CriminalCase> getAllUnsolvedCriminalCases();
    List<CriminalCase> getAllOpenCriminalCases();
    CriminalCase getCriminalCaseById(long id);
}
