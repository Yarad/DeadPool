package com.services.interfaces;

import com.DTO.AddResult;
import com.logic.EvidenceOfCrime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface IEvidenceOfCrimeService {
    List<EvidenceOfCrime> getEvidencesOfCrimeByCrimeId(long id);
    List<EvidenceOfCrime> getAllEvidencesOfCrime();
    EvidenceOfCrime getEvidenceOfCrimeByEvidenceAndCrime(long evidenceId, long crimeId);
    List<EvidenceOfCrime> getEvidencesOfCrimeByEvidenceId(long id);
    boolean addEvidenceOfCrime(long evidenceId, long crimeId, String type, LocalDate dateAdded, LocalTime timeAdded, String details, String photoPath);
    boolean updateEvidenceOfCrime(long evidenceId, long crimeId, String type, LocalDate dateAdded, LocalTime timeAdded, String details, String photoPath);
}
