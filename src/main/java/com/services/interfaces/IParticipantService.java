package com.services.interfaces;

import com.logic.Man;
import com.logic.Participant;

import java.time.LocalDateTime;
import java.util.List;

public interface IParticipantService {
    List<Participant> getParticipantsByCrimeId(long id);
    List<Participant> getParticipantsByManId(long id);
    Participant getParticipantByCrimeAndMan(long manId, long crimeId);
    Man getFullManInfo(long id);
    boolean addParticipant(long manId, long crimeId, String status, LocalDateTime dateAdded, String alibi, String witnessReport);
    boolean updateParticipant(long manId, long crimeId, String status, LocalDateTime dateAdded, String alibi, String witnessReport);
}
