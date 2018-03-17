package com.services.interfaces;

import com.logic.Participant;

import java.util.List;

public interface IParticipantService {
    List<Participant> getParticipantsByCrimeId(long id);
}
