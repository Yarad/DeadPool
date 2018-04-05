package com.DAOTest;

import com.Additionals.AllClassesList;
import com.Additionals.ClassEqualsAsserts;
import com.Additionals.DAOAdditionals;
import com.Additionals.LogicAdditionals;
import com.DAO.DAOCrime;
import com.DAO.DAOEvidenceOfCrime;
import com.DAO.interfaces.IDAOCrime;
import com.DAO.interfaces.IDAOEvidenceOfCrime;
import com.logic.Crime;
import com.logic.EvidenceOfCrime;
import com.logic.EvidenceType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;

public class DAOEvidenceOfCrimeTests {
    private static IDAOEvidenceOfCrime daoEvidenceOfCrime;
    private static DAOAdditionals daoAdditionals;

    @BeforeClass
    public static void getDAO() {
        daoAdditionals = new DAOAdditionals();
        daoEvidenceOfCrime = new DAOEvidenceOfCrime();
    }

    @Test
    public void addEvidenceOfCrime_NullInput()  {
        assertEquals(false, daoEvidenceOfCrime.addEvidenceOfCrime(null));
    }

    @Test
    public void updateEvidenceOfCrime_NullInput()  {
        assertEquals(false, daoEvidenceOfCrime.updateEvidenceOfCrime(null));
    }

    @Test
    public void getEvidenceOfCrime() throws Exception  {
        AllClassesList entities = new AllClassesList();
        entities.addCustomEvidenceOfCrimeToDatabase(UUID.randomUUID().toString().substring(0,30));

        try {
            EvidenceOfCrime actualEvidenceOfCrime = daoEvidenceOfCrime.
                    getEvidenceOfCrime(entities.getCrime().getCrimeId(), entities.getEvidence().getEvidenceId());
            assertNotNull(actualEvidenceOfCrime);
            ClassEqualsAsserts.assertEvidencesOfCrimeEquals(entities.getEvidenceOfCrime(), actualEvidenceOfCrime);
        } finally {
            entities.deleteAllAddedEntities();
        }
    }

    @Test
    public void getEvidenceOfCrime_NoEvidenceOfCrime() throws Exception  {
        EvidenceOfCrime actualEvidenceOfCrime = daoEvidenceOfCrime.getEvidenceOfCrime(-1,-1);
        assertNull(actualEvidenceOfCrime);
    }

    @Test
    public void addCriminalCase() throws Exception  {
        AllClassesList entities = new AllClassesList();
        entities.addCrimeToDatabase(UUID.randomUUID().toString().substring(0,30));
        entities.addCustomEvidenceToDatabase();

        EvidenceOfCrime evidenceOfCrime = LogicAdditionals.getEvidenceOfCrimeWithDates();
        evidenceOfCrime.setEvidenceId(entities.getEvidence().getEvidenceId());
        evidenceOfCrime.setCrimeId(entities.getCrime().getCrimeId());

        try {
            boolean actualResult = daoEvidenceOfCrime.addEvidenceOfCrime(evidenceOfCrime);

            assertTrue(actualResult);
            EvidenceOfCrime actualEvidenceOfCrime = daoEvidenceOfCrime.
                    getEvidenceOfCrime(evidenceOfCrime.getCrimeId(), evidenceOfCrime.getEvidenceId());
            ClassEqualsAsserts.assertEvidencesOfCrimeEquals(evidenceOfCrime, actualEvidenceOfCrime);
        } finally {
            daoAdditionals.deleteEvidenceOfCrime(evidenceOfCrime);
            entities.deleteAllAddedEntities();
        }
    }

    @Test
    public void addCriminalCase_NoCrime() {
        EvidenceOfCrime evidenceOfCrime = LogicAdditionals.getEvidenceOfCrimeWithDates();
        evidenceOfCrime.setCrimeId(-1);

        boolean actualResult = daoEvidenceOfCrime.addEvidenceOfCrime(evidenceOfCrime);

        assertFalse(actualResult);
    }

    @Test
    public void updateEvidenceOfCrime() throws Exception  {
        AllClassesList entities = new AllClassesList();
        entities.addCustomEvidenceOfCrimeToDatabase(UUID.randomUUID().toString().substring(0,30));

        entities.getEvidenceOfCrime().setDateAdded(LocalDateTime.of(2015,12,14,22,25));
        entities.getEvidenceOfCrime().setDetails("frggu");
        entities.getEvidenceOfCrime().setPhotoPath("phhhhhhhhhhooogbdfkuj rsvksdv,jkubsd");
        entities.getEvidenceOfCrime().setEvidenceType("PRINT");

        try {
            boolean actualResult = daoEvidenceOfCrime.updateEvidenceOfCrime(entities.getEvidenceOfCrime());

            assertTrue(actualResult);
            EvidenceOfCrime actualEvidenceOfCrime = daoEvidenceOfCrime.
                    getEvidenceOfCrime(entities.getCrime().getCrimeId(), entities.getEvidence().getEvidenceId());
            ClassEqualsAsserts.assertEvidencesOfCrimeEquals(entities.getEvidenceOfCrime(), actualEvidenceOfCrime);
        } finally {
            entities.deleteAllAddedEntities();
        }
    }

    @Test
    public void updateEvidenceOfCrime_NoCrime() throws Exception  {
        AllClassesList entities = new AllClassesList();
        entities.addCustomEvidenceOfCrimeToDatabase(UUID.randomUUID().toString().substring(0,30));

        long crimeId = entities.getEvidenceOfCrime().getCrimeId();
        entities.getEvidenceOfCrime().setCrimeId(-1);

        try {
            boolean actualResult = daoEvidenceOfCrime.updateEvidenceOfCrime(entities.getEvidenceOfCrime());

            assertFalse(actualResult);
        } finally {
            entities.getEvidenceOfCrime().setCrimeId(crimeId);
            entities.deleteAllAddedEntities();
        }
    }

    @Test
    public void getAllEvidencesOfCrime() throws Exception  {
        AllClassesList entities = new AllClassesList();
        entities.addCustomEvidenceOfCrimeToDatabase(UUID.randomUUID().toString().substring(0,30));

        try {
            List<EvidenceOfCrime> evidencesOfCrimes = daoEvidenceOfCrime.getAllEvidencesOfCrime();

            assertNotNull(evidencesOfCrimes);
            assertFalse(evidencesOfCrimes.isEmpty());

            assertTrue(evidencesOfCrimes.stream().
                    anyMatch(o -> (o.getEvidenceId() == entities.getEvidence().getEvidenceId() &&
                            o.getCrimeId() == entities.getCrime().getCrimeId())));
            Optional<EvidenceOfCrime> optional = evidencesOfCrimes.stream().
                    filter(o -> ( o.getEvidenceId() == entities.getEvidence().getEvidenceId() &&
                                    o.getCrimeId() == entities.getCrime().getCrimeId() )).findFirst();
            ClassEqualsAsserts.assertEvidencesOfCrimeEquals(entities.getEvidenceOfCrime(), optional.orElse(null));
        } finally {
            entities.deleteAllAddedEntities();
        }
    }

    @Test
    public void getAllEvidencesOfCrimeByCrimeId() throws Exception {
        AllClassesList entities = new AllClassesList();
        entities.addCustomEvidenceOfCrimeToDatabase(UUID.randomUUID().toString().substring(0,30));

        try {
            List<EvidenceOfCrime> evidencesOfCrimes = daoEvidenceOfCrime.getAllEvidencesOfCrimeByCrimeId(entities.getCrime().getCrimeId());

            assertNotNull(evidencesOfCrimes);
            assertEquals(1,evidencesOfCrimes.size());

            ClassEqualsAsserts.assertEvidencesOfCrimeEquals(entities.getEvidenceOfCrime(), evidencesOfCrimes.get(0));
        } finally {
            entities.deleteAllAddedEntities();
        }
    }

    @Test
    public void getAllEvidencesOfCrimeByCrimeId_NoEvidencesOfCrime() {
        List<EvidenceOfCrime> evidencesOfCrimes = daoEvidenceOfCrime.getAllEvidencesOfCrimeByCrimeId(-1);

        assertNotNull(evidencesOfCrimes);
        assertTrue(evidencesOfCrimes.isEmpty());
    }

    @Test
    public void getAllEvidencesOfCrimeByEvidenceId() throws Exception {
        AllClassesList entities = new AllClassesList();
        entities.addCustomEvidenceOfCrimeToDatabase(UUID.randomUUID().toString().substring(0,30));

        try {
            List<EvidenceOfCrime> evidencesOfCrimes = daoEvidenceOfCrime.
                    getAllEvidencesOfCrimeByEvidenceId(entities.getEvidence().getEvidenceId());

            assertNotNull(evidencesOfCrimes);
            assertEquals(1,evidencesOfCrimes.size());

            ClassEqualsAsserts.assertEvidencesOfCrimeEquals(entities.getEvidenceOfCrime(), evidencesOfCrimes.get(0));
        } finally {
            entities.deleteAllAddedEntities();
        }
    }

    @Test
    public void getAllEvidencesOfCrimeByEvidenceId_NoEvidencesOfCrime() {
        List<EvidenceOfCrime> evidencesOfCrimes = daoEvidenceOfCrime.getAllEvidencesOfCrimeByEvidenceId(-1);

        assertNotNull(evidencesOfCrimes);
        assertTrue(evidencesOfCrimes.isEmpty());
    }
}
