package com.services.interfaces;

import com.logic.Evidence;

import java.util.List;

public interface IEvidenceService {
    Evidence getEvidenceById(long id);
    boolean addEvidence(String name, String description);
    boolean updateEvidence(long id, String name, String description);
    List<Evidence> getAllEvidences();
}
