package com.services.interfaces;

import com.logic.Evidence;

public interface IEvidenceService {
    Evidence getEvidenceById(long id);
    boolean addEvidence(String name, String description);
    boolean updateEvidence(long id, String name, String description);
}
