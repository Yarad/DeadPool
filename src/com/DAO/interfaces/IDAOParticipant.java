package com.DAO.interfaces;

import com.logic.Participant;

public interface IDAOParticipant  {
    boolean addParticipant(Participant participantToAdd);
    Participant getParticipantById(int manId, int crimeId);
}
