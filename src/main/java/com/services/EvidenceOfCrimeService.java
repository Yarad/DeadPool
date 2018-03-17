package com.services;

import com.DAO.interfaces.IDAOEvidenceOfCrime;
import com.logic.EvidenceOfCrime;
import com.services.interfaces.IEvidenceOfCrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvidenceOfCrimeService implements IEvidenceOfCrimeService {
    @Autowired
    private IDAOEvidenceOfCrime daoEvidenceOfCrime;

    @Override
    public List<EvidenceOfCrime> getEvidencesOfCrimeByCrimeId(long id) {
        return daoEvidenceOfCrime.getAllEvidencesOfCrimeByCrimeId(id);
    }
}
