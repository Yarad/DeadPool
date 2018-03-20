package com.DAO.interfaces;

import com.logic.Evidence;

import java.util.List;

public interface IDAOEvidence {
    Evidence getEvidenceById(long id);

    List<Evidence> getAllEvidencesByCrime(long crimeId);

    boolean addEvidence(Evidence evidence);
    boolean updateEvidence(Evidence evidence);
}
