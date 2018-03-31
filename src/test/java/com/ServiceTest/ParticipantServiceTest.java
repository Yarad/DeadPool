package com.ServiceTest;

import com.Additionals.LogicAdditionals;
import com.DAO.interfaces.IDAOMan;
import com.DAO.interfaces.IDAOParticipant;
import com.logic.Man;
import com.logic.Participant;
import com.services.ManService;
import com.services.ParticipantService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class ParticipantServiceTest {
    @Mock
    private IDAOParticipant daoParticipant;

    @InjectMocks
    private ParticipantService service;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getParticipantsByCrimeId() throws Exception {
        List<Participant> participants = LogicAdditionals.getParticipantList();
        when(daoParticipant.getAllParticipantsByCrime(anyLong())).thenReturn(participants);

        List<Participant> actualParticipants = service.getParticipantsByCrimeId(1);

        assertEquals(participants, actualParticipants);
    }

    @Test
    public void getParticipantsByManId() throws Exception {
        List<Participant> participants = LogicAdditionals.getParticipantList();
        when(daoParticipant.getAllParticipantsByMan(anyLong())).thenReturn(participants);

        List<Participant> actualParticipants = service.getParticipantsByManId(1);

        assertEquals(participants, actualParticipants);
    }

    @Test
    public void getParticipantByCrimeAndMan() throws Exception {
        Participant participant = LogicAdditionals.getCustomParticipant();
        when(daoParticipant.getParticipantById(anyLong(), anyLong())).thenReturn(participant);

        Participant actualParticipant = service.getParticipantByCrimeAndMan(1, 1);

        assertEquals(participant, actualParticipant);
    }

    @Test
    public void addParticipant() throws Exception {
        Participant participant = LogicAdditionals.getCustomParticipantWithDate();
        boolean expectedResult = true;
        when(daoParticipant.addParticipant(any(Participant.class))).thenReturn(expectedResult);

        boolean actualResult = service.addParticipant(
                participant.getManId(),
                participant.getCrimeId(),
                participant.getParticipantStatus().toString(),
                (participant.getDateAdded() != null) ? participant.getDateAdded().toLocalDate() : null,
                (participant.getDateAdded() != null) ? participant.getDateAdded().toLocalTime() : null,
                participant.getAlibi(),
                participant.getWitnessReport()
        );

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void addParticipant_NotCorrectDate() throws Exception {
        Participant participant = LogicAdditionals.getCustomParticipantWithDate();
        boolean expectedResult = false;
        when(daoParticipant.addParticipant(any(Participant.class))).thenReturn(expectedResult);

        boolean actualResult = service.addParticipant(
                participant.getManId(),
                participant.getCrimeId(),
                participant.getParticipantStatus().toString(),
                null,
                (participant.getDateAdded() != null) ? participant.getDateAdded().toLocalTime() : null,
                participant.getAlibi(),
                participant.getWitnessReport()
        );

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void addParticipant_NotCorrectStatus() throws Exception {
        Participant participant = LogicAdditionals.getCustomParticipantWithDate();
        boolean expectedResult = false;
        when(daoParticipant.addParticipant(any(Participant.class))).thenReturn(expectedResult);

        boolean actualResult = service.addParticipant(
                participant.getManId(),
                participant.getCrimeId(),
                participant.getParticipantStatus().getName(),
                (participant.getDateAdded() != null) ? participant.getDateAdded().toLocalDate() : null,
                (participant.getDateAdded() != null) ? participant.getDateAdded().toLocalTime() : null,
                participant.getAlibi(),
                participant.getWitnessReport()
        );

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void updateParticipant() throws Exception {
        Participant participant = LogicAdditionals.getCustomParticipantWithDate();
        boolean expectedResult = true;
        when(daoParticipant.updateParticipant(any(Participant.class))).thenReturn(expectedResult);

        boolean actualResult = service.updateParticipant(
                participant.getManId(),
                participant.getCrimeId(),
                participant.getParticipantStatus().toString(),
                (participant.getDateAdded() != null) ? participant.getDateAdded().toLocalDate() : null,
                (participant.getDateAdded() != null) ? participant.getDateAdded().toLocalTime() : null,
                participant.getAlibi(),
                participant.getWitnessReport()
        );

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void updateParticipant_NotCorrectDate() throws Exception {
        Participant participant = LogicAdditionals.getCustomParticipantWithDate();
        boolean expectedResult = false;
        when(daoParticipant.updateParticipant(any(Participant.class))).thenReturn(expectedResult);

        boolean actualResult = service.updateParticipant(
                participant.getManId(),
                participant.getCrimeId(),
                participant.getParticipantStatus().toString(),
                null,
                (participant.getDateAdded() != null) ? participant.getDateAdded().toLocalTime() : null,
                participant.getAlibi(),
                participant.getWitnessReport()
        );

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void updateParticipant_NotCorrectStatus() throws Exception {
        Participant participant = LogicAdditionals.getCustomParticipantWithDate();
        boolean expectedResult = false;
        when(daoParticipant.updateParticipant(any(Participant.class))).thenReturn(expectedResult);

        boolean actualResult = service.updateParticipant(
                participant.getManId(),
                participant.getCrimeId(),
                participant.getParticipantStatus().getName(),
                (participant.getDateAdded() != null) ? participant.getDateAdded().toLocalDate() : null,
                (participant.getDateAdded() != null) ? participant.getDateAdded().toLocalTime() : null,
                participant.getAlibi(),
                participant.getWitnessReport()
        );

        assertEquals(expectedResult, actualResult);
    }
}
