package com.services;

import com.DAO.interfaces.IDAOEvidenceOfCrime;
import com.logic.Crime;
import com.logic.Evidence;
import com.logic.EvidenceOfCrime;
import com.services.interfaces.IEvidenceOfCrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class EvidenceOfCrimeService implements IEvidenceOfCrimeService {
    @Autowired
    private IDAOEvidenceOfCrime daoEvidenceOfCrime;

    @Transactional
    @Override
    public List<EvidenceOfCrime> getEvidencesOfCrimeByCrimeId(long id) {
        return daoEvidenceOfCrime.getAllEvidencesOfCrimeByCrimeId(id);
    }

    @Transactional
    @Override
    public List<EvidenceOfCrime> getAllEvidencesOfCrime() {
        return daoEvidenceOfCrime.getAllEvidencesOfCrime();
    }

    @Transactional
    @Override
    public EvidenceOfCrime getEvidenceOfCrimeByEvidenceAndCrime(long evidenceId, long crimeId) {
        return daoEvidenceOfCrime.getEvidenceOfCrime(crimeId, evidenceId);
    }

    @Transactional
    @Override
    public List<EvidenceOfCrime> getEvidencesOfCrimeByEvidenceId(long id) {
        return daoEvidenceOfCrime.getAllEvidencesOfCrimeByEvidenceId(id);
    }

    @Transactional
    @Override
    public boolean addEvidenceOfCrime(long evidenceId, long crimeId, String type, LocalDate dateAdded, LocalTime timeAdded, String details, String photoPath) {
        EvidenceOfCrime evidenceOfCrime = new EvidenceOfCrime();
        evidenceOfCrime.parentEvidence = new Evidence();
        evidenceOfCrime.parentCrime = new Crime();
        evidenceOfCrime.parentEvidence.setEvidenceId(evidenceId);
        evidenceOfCrime.parentCrime.setCrimeId(crimeId);
        try {
            evidenceOfCrime.setEvidenceType(type);
        } catch (IllegalArgumentException ex) {
            return false;
        }
        if (dateAdded != null && timeAdded != null) {
            evidenceOfCrime.setDateAdded(LocalDateTime.of(dateAdded, timeAdded));
        } else {
            return false;
        }
        evidenceOfCrime.setDetails(details);
        evidenceOfCrime.setPhotoPath(photoPath);
        return daoEvidenceOfCrime.addEvidenceOfCrime(evidenceOfCrime);
    }

    @Transactional
    @Override
    public boolean updateEvidenceOfCrime(long evidenceId, long crimeId, String type, LocalDate dateAdded, LocalTime timeAdded, String details, String photoPath) {
        EvidenceOfCrime evidenceOfCrime = new EvidenceOfCrime();
        evidenceOfCrime.parentEvidence = new Evidence();
        evidenceOfCrime.parentCrime = new Crime();
        evidenceOfCrime.parentEvidence.setEvidenceId(evidenceId);
        evidenceOfCrime.parentCrime.setCrimeId(crimeId);
        try {
            evidenceOfCrime.setEvidenceType(type);
        } catch (IllegalArgumentException ex) {
            return false;
        }
        if (dateAdded != null && timeAdded != null) {
            evidenceOfCrime.setDateAdded(LocalDateTime.of(dateAdded, timeAdded));
        } else {
            return false;
        }
        evidenceOfCrime.setDetails(details);
        evidenceOfCrime.setPhotoPath(photoPath);
        return daoEvidenceOfCrime.updateEvidenceOfCrime(evidenceOfCrime);
    }
}
