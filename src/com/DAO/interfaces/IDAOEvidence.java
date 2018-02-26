package com.DAO.interfaces;

import com.logic.Evidence;

import java.util.List;

public interface IDAOEvidence {
    Evidence getEvidenceById(long id);

    List<Evidence> getAllEvidencesByCrime(long crimeId);
}
