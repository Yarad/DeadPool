package com.DAO.interfaces;

import com.logic.CriminalCase;

public interface IDAOCriminalCase {
    boolean addCriminalCase(CriminalCase criminalCase);
    CriminalCase getCriminalCaseById(int id);
}
