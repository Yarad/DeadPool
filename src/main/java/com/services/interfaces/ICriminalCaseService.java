package com.services.interfaces;

import com.DTO.AddResult;
import com.logic.CriminalCase;

import java.time.LocalDate;
import java.util.List;

public interface ICriminalCaseService {
    List<CriminalCase> getAllCriminalCases();
    List<CriminalCase> getAllSolvedCriminalCases();
    List<CriminalCase> getAllUnsolvedCriminalCases();
    List<CriminalCase> getAllOpenCriminalCases();
    List<CriminalCase> getCriminalCasesByDetectiveId(long detectiveId);
    CriminalCase getCriminalCaseById(long id);
    AddResult addCriminalCase(long detectiveId, String number, LocalDate createDate, boolean closed, LocalDate closeDate);
    boolean updateCriminalCase(long id, long detectiveId, String number, LocalDate createDate, boolean closed, LocalDate closeDate);
}
