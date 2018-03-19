package com.services.interfaces;

import com.logic.EvidenceOfCrime;

import java.time.LocalDateTime;
import java.util.List;

public interface IEvidenceOfCrimeService {
    List<EvidenceOfCrime> getEvidencesOfCrimeByCrimeId(long id);
    List<EvidenceOfCrime> getAllEvidencesOfCrime();
    EvidenceOfCrime getEvidenceOfCrimeByEvidenceAndCrime(long evidenceId, long crimeId);
    List<EvidenceOfCrime> getEvidencesOfCrimeByEvidenceId(long id);
    boolean addEvidenceOfCrime(long evidenceId, long crimeId, String type, LocalDateTime dateAdded, String details, String photoPath);
    boolean updateEvidenceOfCrime(long evidenceId, long crimeId, String type, LocalDateTime dateAdded, String details, String photoPath);
}
