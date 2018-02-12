package com.DAO.interfaces;

import com.logic.Participant;

import java.util.List;

public interface IDAOParticipant {
    boolean addParticipant(Participant participantToAdd);

    Participant getParticipantById(int manId, int crimeId);

    //получить список всех преступлений, в которых фигурирует participant c id
    List<Participant> getParticipantInCrimesByManId(int participantId);
}
