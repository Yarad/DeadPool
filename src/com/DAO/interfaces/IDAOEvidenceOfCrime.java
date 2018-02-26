package com.DAO.interfaces;

import com.logic.EvidenceOfCrime;

public interface IDAOEvidenceOfCrime {
    EvidenceOfCrime getEvidenceOfCrime(long crimeId, long evidenceId);
}
