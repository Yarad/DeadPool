package com.DAOTest;

import com.Additionals.AllClassesList;
import com.Additionals.ClassEqualsAsserts;
import com.Additionals.DAOAdditionals;
import com.Additionals.LogicAdditionals;
import com.DAO.DAOCrime;
import com.DAO.DAOParticipant;
import com.DAO.interfaces.IDAOCrime;
import com.DAO.interfaces.IDAOParticipant;
import com.logic.Crime;
import com.logic.Participant;
import com.logic.ParticipantStatus;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;

public class DAOParticipantTests {
    private static IDAOParticipant daoParticipant;
    private static DAOAdditionals daoAdditionals;

    @BeforeClass
    public static void getDAO() {
        daoAdditionals = new DAOAdditionals();
        daoParticipant = new DAOParticipant();
    }

    @Test
    public void addParticipant_NullInput()  {
        assertEquals(false, daoParticipant.addParticipant(null));
    }

    @Test
    public void updateParticipant_NullInput()  {
        assertEquals(false, daoParticipant.updateParticipant(null));
    }

    @Test
    public void getParticipantById() throws Exception {
        AllClassesList entities = new AllClassesList();
        entities.addCustomParticipantToDatabase(UUID.randomUUID().toString().substring(0,30));

        try {
            Participant actualParticipant = daoParticipant.getParticipantById(entities.getParticipant().getManId(),
                    entities.getParticipant().getCrimeId());
            assertNotNull(actualParticipant);
            ClassEqualsAsserts.assertParticipantsEquals(entities.getParticipant(), actualParticipant);
        } finally {
            entities.deleteAllAddedEntities();
        }
    }

    @Test
    public void getParticipantById_NoParticipant() {
        Participant actualParticipant = daoParticipant.getParticipantById(-1,-1);
        assertNull(actualParticipant);
    }

    @Test
    public void addParticipant() throws Exception {
        AllClassesList entities = new AllClassesList();
        entities.addCrimeToDatabase(UUID.randomUUID().toString().substring(0,30));
        entities.addCustomManToDatabase();

        Participant participant = LogicAdditionals.getParticipantWithDates();
        participant.setManId(entities.getMan().getManId());
        participant.setCrimeId(entities.getCrime().getCrimeId());

        try {
            boolean actualResult = daoParticipant.addParticipant(participant);

            assertTrue(actualResult);
            Participant actualParticipant = daoParticipant.getParticipantById(participant.getManId(), participant.getCrimeId());
            ClassEqualsAsserts.assertParticipantsEquals(participant, actualParticipant);
        } finally {
            daoAdditionals.deleteParticipant(participant);
            entities.deleteAllAddedEntities();
        }
    }

    @Test
    public void addParticipant_NoMan() {
        Participant participant = LogicAdditionals.getParticipantWithDates();
        participant.setManId(-1);

        boolean actualResult = daoParticipant.addParticipant(participant);

        assertFalse(actualResult);
    }

    @Test
    public void updateParticipant() throws Exception {
        AllClassesList entities = new AllClassesList();
        entities.addCustomParticipantToDatabase(UUID.randomUUID().toString().substring(0,30));

        entities.getParticipant().setAlibi("alibi for update");
        entities.getParticipant().setWitnessReport("report for Puzdrov");
        entities.getParticipant().setDateAdded(LocalDateTime.of(2010,5,26,19,58));
        entities.getParticipant().setParticipantStatus("SPECTATOR");

        try {
            boolean actualResult = daoParticipant.updateParticipant(entities.getParticipant());

            assertTrue(actualResult);
            Participant actualParticipant = daoParticipant.getParticipantById(entities.getMan().getManId(), entities.getCrime().getCrimeId());
            ClassEqualsAsserts.assertParticipantsEquals(entities.getParticipant(), actualParticipant);
        } finally {
            entities.deleteAllAddedEntities();
        }
    }

    @Test
    public void updateParticipant_NoCrime() throws Exception {
        AllClassesList entities = new AllClassesList();
        entities.addCustomParticipantToDatabase(UUID.randomUUID().toString().substring(0,30));

        entities.getParticipant().setCrimeId(-1);

        try {
            boolean actualResult = daoParticipant.updateParticipant(entities.getParticipant());

            assertFalse(actualResult);
        } finally {
            entities.getParticipant().setCrimeId(entities.getCrime().getCrimeId());
            entities.deleteAllAddedEntities();
        }
    }

    @Test
    public void getAllParticipantsByMan() throws Exception {
        AllClassesList entities = new AllClassesList();
        entities.addCustomParticipantToDatabase(UUID.randomUUID().toString().substring(0,30));

        try {
            List<Participant> participants = daoParticipant.getAllParticipantsByMan(entities.getMan().getManId());

            assertNotNull(participants);
            assertEquals(1, participants.size());

            ClassEqualsAsserts.assertParticipantsEquals(entities.getParticipant(), participants.get(0));
        } finally {
            entities.deleteAllAddedEntities();
        }
    }

    @Test
    public void getAllParticipantsByMan_NoParticipants() {
        List<Participant> participants = daoParticipant.getAllParticipantsByMan(-1);

        assertNotNull(participants);
        assertTrue(participants.isEmpty());
    }

    @Test
    public void getAllParticipantsByCrime() throws Exception {
        AllClassesList entities = new AllClassesList();
        entities.addCustomParticipantToDatabase(UUID.randomUUID().toString().substring(0,30));

        try {
            List<Participant> participants = daoParticipant.getAllParticipantsByCrime(entities.getCrime().getCrimeId());

            assertNotNull(participants);
            assertEquals(1, participants.size());

            ClassEqualsAsserts.assertParticipantsEquals(entities.getParticipant(), participants.get(0));
        } finally {
            entities.deleteAllAddedEntities();
        }
    }

    @Test
    public void getAllParticipantsByCrime_NoParticipants() {
        List<Participant> participants = daoParticipant.getAllParticipantsByCrime(-1);

        assertNotNull(participants);
        assertTrue(participants.isEmpty());
    }
}
