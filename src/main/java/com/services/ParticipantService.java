package com.services;

import com.DAO.interfaces.IDAOParticipant;
import com.logic.Participant;
import com.services.interfaces.IParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantService implements IParticipantService {
    @Autowired
    private IDAOParticipant daoParticipant;

    @Override
    public List<Participant> getParticipantsByCrimeId(long id) {
        return daoParticipant.getAllParticipantsByCrime(id);
    }

    @Override
    public Participant getParticipantByCrimeAndMan(long manId, long crimeId) {
        return daoParticipant.getParticipantById(manId, crimeId);
    }
}
