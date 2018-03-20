package com.DAO.interfaces;

import java.util.List;

import com.logic.EvidenceOfCrime;

public interface IDAOEvidenceOfCrime {
    EvidenceOfCrime getEvidenceOfCrime(long crimeId, long evidenceId);
    
    /*
     * Должен возвращать все записи в таблице.
     * Для каждого EvidenceOfCrime должен быть заполнен EvidenceType, Evidence, Crime.
     * У Crime обязательно должен быть заполнен родительский CriminalCase.
     */
    List<EvidenceOfCrime> getAllEvidencesOfCrime();
    
    /*
     * Должен возвращать все улики, связанные с преступлением
     * Для каждого EvidenceOfCrime должен быть заполнен EvidenceType, Evidence.
     */
    List<EvidenceOfCrime> getAllEvidencesOfCrimeByCrimeId(long crimeId);
    
    /*
     * Должен возвращать все evidence_of_crime по evidence_id
     * Для каждого EvidenceOfCrime должен быть заполнен EvidenceType, Crime.
     * У Crime обязательно должен быть заполнен родительский CriminalCase.
     */
    List<EvidenceOfCrime> getAllEvidencesOfCrimeByEvidenceId(long evidenceId);

    boolean addEvidenceOfCrime(EvidenceOfCrime evidenceOfCrime);
    boolean updateEvidenceOfCrime(EvidenceOfCrime evidenceOfCrime);
}
