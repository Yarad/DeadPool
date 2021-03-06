package com.services;

import com.DAO.interfaces.IDAOParticipant;
import com.logic.Participant;
import com.services.interfaces.IParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    public boolean addParticipant(long manId, long crimeId, String status, LocalDate dateAdded, LocalTime timeAdded, String alibi, String witnessReport) {
        Participant participant = new Participant();
        participant.setManId(manId);
        participant.setCrimeId(crimeId);
        try {
            participant.setParticipantStatus(status);
        } catch (IllegalArgumentException ex) {
            return false;
        }
        if (dateAdded != null && timeAdded != null) {
            participant.setDateAdded(LocalDateTime.of(dateAdded, timeAdded));
        } else {
            return false;
        }
        participant.setAlibi(alibi);
        participant.setWitnessReport(witnessReport);
        return daoParticipant.addParticipant(participant);
    }

    @Transactional
    @Override
    public boolean updateParticipant(long manId, long crimeId, String status, LocalDate dateAdded, LocalTime timeAdded, String alibi, String witnessReport) {
        Participant participant = new Participant();
        participant.setManId(manId);
        participant.setCrimeId(crimeId);
        try {
            participant.setParticipantStatus(status);
        } catch (IllegalArgumentException ex) {
            return false;
        }
        if (dateAdded != null && timeAdded != null) {
            participant.setDateAdded(LocalDateTime.of(dateAdded, timeAdded));
        } else {
            return false;
        }
        participant.setAlibi(alibi);
        participant.setWitnessReport(witnessReport);
        return daoParticipant.updateParticipant(participant);
    }
}
