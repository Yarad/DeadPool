package com.services.interfaces;

import com.DTO.AddResult;
import com.logic.Participant;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface IParticipantService {
    List<Participant> getParticipantsByCrimeId(long id);
    List<Participant> getParticipantsByManId(long id);
    Participant getParticipantByCrimeAndMan(long manId, long crimeId);
    boolean addParticipant(long manId, long crimeId, String status, LocalDate dateAdded, LocalTime timeAdded, String alibi, String witnessReport);
    boolean updateParticipant(long manId, long crimeId, String status, LocalDate dateAdded, LocalTime timeAdded, String alibi, String witnessReport);
}
