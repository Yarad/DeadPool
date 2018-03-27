package com.services;

import com.DAO.interfaces.IDAOParticipant;
import com.logic.Man;
import com.logic.Participant;
import com.logic.ParticipantStatus;
import com.services.interfaces.IParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParticipantService implements IParticipantService {
    @Autowired
    private IDAOParticipant daoParticipant;

    @Transactional
    @Override
    public List<Participant> getParticipantsByCrimeId(long id) {
        return daoParticipant.getAllParticipantsByCrime(id);
    }

    @Transactional
    @Override
    public List<Participant> getParticipantsByManId(long id) {
        return daoParticipant.getAllParticipantsByMan(id);
    }

    @Transactional
    @Override
    public Participant getParticipantByCrimeAndMan(long manId, long crimeId) {
        return daoParticipant.getParticipantById(manId, crimeId);
    }

    @Transactional
    @Override
    public boolean addParticipant(long manId, long crimeId, String status, LocalDateTime dateAdded, String alibi, String witnessReport) {
        Participant participant = new Participant();
        participant.setManId(manId);
        participant.setCrimeId(crimeId);
        participant.setParticipantStatus(status);
        participant.setDateAdded(dateAdded);
        participant.setAlibi(alibi);
        participant.setWitnessReport(witnessReport);
        return daoParticipant.addParticipant(participant);
    }

    @Transactional
    @Override
    public boolean updateParticipant(long manId, long crimeId, String status, LocalDateTime dateAdded, String alibi, String witnessReport) {
        Participant participant = new Participant();
        participant.setManId(manId);
        participant.setCrimeId(crimeId);
        participant.setParticipantStatus(status);
        participant.setDateAdded(dateAdded);
        participant.setAlibi(alibi);
        participant.setWitnessReport(witnessReport);
        return daoParticipant.updateParticipant(participant);
    }
}
