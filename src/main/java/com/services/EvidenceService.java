package com.services;

import com.DAO.interfaces.IDAOEvidence;
import com.DTO.AddResult;
import com.logic.Evidence;
import com.services.interfaces.IEvidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EvidenceService implements IEvidenceService {
    @Autowired
    private IDAOEvidence daoEvidence;

    @Transactional
    @Override
    public Evidence getEvidenceById(long id) {
        return daoEvidence.getEvidenceById(id);
    }

    @Transactional
    @Override
    public AddResult addEvidence(String name, String description) {
        Evidence evidence = new Evidence();
        evidence.setName(name);
        evidence.setDescription(description);
        return new AddResult(daoEvidence.addEvidence(evidence), evidence.getEvidenceId());
    }

    @Transactional
    @Override
    public boolean updateEvidence(long id, String name, String description) {
        Evidence evidence = new Evidence();
        evidence.setEvidenceId(id);
        evidence.setName(name);
        evidence.setDescription(description);
        return daoEvidence.updateEvidence(evidence);
    }

    @Override
    public List<Evidence> getAllEvidences() {
        return daoEvidence.getAllEvidences();
    }
}
