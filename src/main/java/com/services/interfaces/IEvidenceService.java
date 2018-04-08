package com.services.interfaces;

import com.DTO.AddResult;
import com.logic.Evidence;

import java.util.List;

public interface IEvidenceService {
    Evidence getEvidenceById(long id);
    AddResult addEvidence(String name, String description);
    boolean updateEvidence(long id, String name, String description);
    List<Evidence> getAllEvidences();
}
