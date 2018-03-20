package com.services;

import com.DAO.interfaces.IDAOEvidence;
import com.logic.Evidence;
import com.services.interfaces.IEvidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvidenceService implements IEvidenceService {
    @Autowired
    private IDAOEvidence daoEvidence;

    @Override
    public Evidence getEvidenceById(long id) {
        return daoEvidence.getEvidenceById(id);
    }

    @Override
    public boolean addEvidence(String name, String description) {
        Evidence evidence = new Evidence();
        evidence.setName(name);
        evidence.setDescription(description);
        return daoEvidence.addEvidence(evidence);
    }

    @Override
    public boolean updateEvidence(long id, String name, String description) {
        Evidence evidence = new Evidence();
        evidence.setEvidenceId(id);
        evidence.setName(name);
        evidence.setDescription(description);
        return daoEvidence.updateEvidence(evidence);
    }
}
