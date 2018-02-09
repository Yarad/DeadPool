package com.DAO.interfaces;

import com.logic.Evidence;

public interface IDAOEvidence {
    Evidence getEvidenceById(int id);
    boolean addEvidence(Evidence evidenceToAdd);
}
