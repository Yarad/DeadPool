package com.services.interfaces;

import com.logic.EvidenceOfCrime;

import java.util.List;

public interface IEvidenceOfCrimeService {
    List<EvidenceOfCrime> getEvidencesOfCrimeByCrimeId(long id);
    List<EvidenceOfCrime> getAllEvidencesOfCrime();
    EvidenceOfCrime getEvidenceOfCrimeByEvidenceAndCrime(long evidenceId, long crimeId);
    List<EvidenceOfCrime> getEvidencesOfCrimeByEvidenceId(long id);
}
