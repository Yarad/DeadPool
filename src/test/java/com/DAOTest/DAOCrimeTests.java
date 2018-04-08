package com.DAOTest;

import com.Additionals.AllClassesList;
import com.Additionals.ClassEqualsAsserts;
import com.Additionals.DAOAdditionals;
import com.Additionals.LogicAdditionals;
import com.DAO.DAOCrime;
import com.DAO.interfaces.IDAOCrime;
import com.logic.Crime;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;

public class DAOCrimeTests {
    private static IDAOCrime daoCrime;
    private static DAOAdditionals daoAdditionals;

    @BeforeClass
    public static void getDAO() {
        daoAdditionals = new DAOAdditionals();
        daoCrime = new DAOCrime();
    }

    @Test
    public void addCrime_NullInput()  {
        assertEquals(false, daoCrime.addCrime(null));
    }

    @Test
    public void updateCrime_NullInput()  {
        assertEquals(false, daoCrime.updateCrime(null));
    }

    @Test
    public void getCriminalCaseById() throws Exception  {
        AllClassesList entities = new AllClassesList();
        entities.addCrimeToDatabase(UUID.randomUUID().toString().substring(0,30));

        try {
            Crime actualCrime = daoCrime.getCrimeById(entities.getCrime().getCrimeId());
            assertNotNull(actualCrime);
            ClassEqualsAsserts.assertCrimesEquals(entities.getCrime(), actualCrime);
        } finally {
            entities.deleteAllAddedEntities();
        }
    }

    @Test
    public void getCriminalCaseById_NoCrime() {
        Crime actualCrime = daoCrime.getCrimeById(-1);
        assertNull(actualCrime);
    }

    @Test
    public void addCrime() throws Exception  {
        AllClassesList entities = new AllClassesList();
        entities.addSolvedCriminalCaseToDatabase(UUID.randomUUID().toString().substring(0,30));

        Crime crime = LogicAdditionals.getCrimeWithDates();
        crime.setCriminalCaseId(entities.getCriminalCase().getCriminalCaseId());

        try {
            boolean actualResult = daoCrime.addCrime(crime);

            assertTrue(actualResult);
            Crime actualCrime = daoCrime.getCrimeById(crime.getCrimeId());
            ClassEqualsAsserts.assertCrimesEquals(crime, actualCrime);
        } finally {
            daoAdditionals.deleteCrime(crime);
            entities.deleteAllAddedEntities();
        }
    }

    @Test
    public void addCrime_NoCriminalCase() throws Exception  {
        Crime crime = LogicAdditionals.getCrimeWithDates();
        crime.setCriminalCaseId(-1);
        crime.setCrimeTime(null);

        boolean actualResult = daoCrime.addCrime(crime);

        assertFalse(actualResult);
    }

    @Test
    public void updateCrime() throws Exception  {
        AllClassesList entities = new AllClassesList();
        entities.addCrimeToDatabase(UUID.randomUUID().toString().substring(0,30));
        AllClassesList otherEntities = new AllClassesList();
        otherEntities.addSolvedCriminalCaseToDatabase(UUID.randomUUID().toString().substring(0,30));

        entities.getCrime().setCriminalCaseId(otherEntities.getCriminalCase().getCriminalCaseId());
        entities.getCrime().setCrimeTime(LocalTime.MIDNIGHT);
        entities.getCrime().setCrimeDate(LocalDate.now());
        entities.getCrime().setDescription("newDescription for update test");
        entities.getCrime().setCrimePlace("update method");
        entities.getCrime().setCrimeType("SUICIDE");

        try {
            boolean actualResult = daoCrime.updateCrime(entities.getCrime());

            assertTrue(actualResult);
            Crime actualCrime = daoCrime.getCrimeById(entities.getCrime().getCrimeId());
            ClassEqualsAsserts.assertCrimesEquals(entities.getCrime(), actualCrime);
        } finally {
            entities.deleteAllAddedEntities();
            otherEntities.deleteAllAddedEntities();
        }
    }

    @Test
    public void updateCrime_NoCriminalCase() throws Exception  {
        AllClassesList entities = new AllClassesList();
        entities.addCrimeToDatabase(UUID.randomUUID().toString().substring(0,30));

        entities.getCrime().setCriminalCaseId(-1);
        entities.getCrime().setCrimeTime(null);

        try {
            boolean actualResult = daoCrime.updateCrime(entities.getCrime());

            assertFalse(actualResult);
        } finally {
            entities.deleteAllAddedEntities();
        }
    }

    @Test
    public void getAllCrimes() throws Exception  {
        AllClassesList entities = new AllClassesList();
        entities.addCrimeToDatabase(UUID.randomUUID().toString().substring(0,30));

        try {
            List<Crime> crimes = daoCrime.getAllCrimes();

            assertNotNull(crimes);
            assertFalse(crimes.isEmpty());

            assertTrue(crimes.stream().anyMatch(o -> o.getCrimeId() == entities.getCrime().getCrimeId()));
            Optional<Crime> optional = crimes.stream().
                    filter(p -> p.getCrimeId() == entities.getCrime().getCrimeId()).findFirst();
            ClassEqualsAsserts.assertCrimesEquals(entities.getCrime(), optional.orElse(null));
        } finally {
            entities.deleteAllAddedEntities();
        }
    }

    @Test
    public void getCrimesBetweenDates_NullDates() throws Exception  {
        AllClassesList entities = new AllClassesList();
        entities.addCrimeToDatabase(UUID.randomUUID().toString().substring(0,30));

        try {
            List<Crime> crimes = daoCrime.getCrimesBetweenDates(null,null);

            assertNotNull(crimes);
            assertFalse(crimes.isEmpty());

            assertTrue(crimes.stream().anyMatch(o -> o.getCrimeId() == entities.getCrime().getCrimeId()));
            Optional<Crime> optional = crimes.stream().
                    filter(p -> p.getCrimeId() == entities.getCrime().getCrimeId()).findFirst();
            ClassEqualsAsserts.assertCrimesEquals(entities.getCrime(), optional.orElse(null));
        } finally {
            entities.deleteAllAddedEntities();
        }
    }

    @Test
    public void getCrimesBetweenDates() throws Exception  {
        AllClassesList entities = new AllClassesList();
        entities.addCrimeToDatabase(UUID.randomUUID().toString().substring(0,30));
        entities.getCrime().setCrimeDate(LocalDate.of(2012,02,25));
        if (!daoCrime.updateCrime(entities.getCrime()))
            throw new Exception();

        AllClassesList entities2 = new AllClassesList();
        entities2.addCrimeToDatabase(UUID.randomUUID().toString().substring(0,30));
        entities2.getCrime().setCrimeDate(LocalDate.of(200,01,01));
        if (!daoCrime.updateCrime(entities2.getCrime()))
            throw new Exception();

        AllClassesList entities3 = new AllClassesList();
        entities3.addCrimeToDatabase(UUID.randomUUID().toString().substring(0,30));
        entities3.getCrime().setCrimeDate(LocalDate.of(2018,04,05));
        if (!daoCrime.updateCrime(entities3.getCrime()))
            throw new Exception();

        try {
            List<Crime> crimes = daoCrime.getCrimesBetweenDates(LocalDate.of(2010,01,01),
                    LocalDate.of(2015,12,31));

            assertNotNull(crimes);
            assertFalse(crimes.isEmpty());

            assertTrue(crimes.stream().anyMatch(o -> o.getCrimeId() == entities.getCrime().getCrimeId()));
            Optional<Crime> optional = crimes.stream().
                    filter(p -> p.getCrimeId() == entities.getCrime().getCrimeId()).findFirst();
            ClassEqualsAsserts.assertCrimesEquals(entities.getCrime(), optional.orElse(null));

            assertFalse(crimes.stream().anyMatch(o -> o.getCrimeId() == entities2.getCrime().getCrimeId()));

            assertFalse(crimes.stream().anyMatch(o -> o.getCrimeId() == entities3.getCrime().getCrimeId()));
        } finally {
            entities.deleteAllAddedEntities();
            entities2.deleteAllAddedEntities();
            entities3.deleteAllAddedEntities();
        }
    }

    @Test
    public void getCrimesByCriminalCase() throws Exception  {
        AllClassesList entities = new AllClassesList();
        entities.addCrimeToDatabase(UUID.randomUUID().toString().substring(0,30));
        Crime crime = LogicAdditionals.getCrimeWithDates();
        crime.setCriminalCaseId(entities.getCriminalCase().getCriminalCaseId());
        if (!daoCrime.addCrime(crime))
            throw new Exception();

        try {
            List<Crime> crimes = daoCrime.getCrimesByCriminalCase(entities.getCriminalCase().getCriminalCaseId());

            assertNotNull(crimes);
            assertFalse(crimes.isEmpty());

            assertTrue(crimes.stream().anyMatch(o -> o.getCrimeId() == entities.getCrime().getCrimeId()));
            Optional<Crime> optional = crimes.stream().
                    filter(p -> p.getCrimeId() == entities.getCrime().getCrimeId()).findFirst();
            ClassEqualsAsserts.assertCrimesEquals(entities.getCrime(), optional.orElse(null));

            assertTrue(crimes.stream().anyMatch(o -> o.getCrimeId() == crime.getCrimeId()));
            optional = crimes.stream().
                    filter(p -> p.getCrimeId() == crime.getCrimeId()).findFirst();
            ClassEqualsAsserts.assertCrimesEquals(crime, optional.orElse(null));
        } finally {
            daoAdditionals.deleteCrime(crime);
            entities.deleteAllAddedEntities();
        }
    }

    @Test
    public void getCrimesByCriminalCase_NoCrimes() {
        List<Crime> crimes = daoCrime.getCrimesByCriminalCase(-1);

        assertNotNull(crimes);
        assertTrue(crimes.isEmpty());
    }

    @Test
    public void getCrimesWhereEvidenceExists() throws Exception  {
        AllClassesList entities = new AllClassesList();
        entities.addCustomEvidenceOfCrimeToDatabase(UUID.randomUUID().toString().substring(0,30));

        try {
            List<Crime> crimes = daoCrime.getCrimesWhereEvidenceExists(entities.getEvidence().getEvidenceId());

            assertNotNull(crimes);
            assertEquals(1,crimes.size());

            Crime actualCrime = crimes.get(0);
            ClassEqualsAsserts.assertCrimesEquals(entities.getCrime(), actualCrime);
        } finally {
            entities.deleteAllAddedEntities();
        }
    }

    @Test
    public void getCrimesWhereEvidenceExists_NoCrimes() {
        List<Crime> crimes = daoCrime.getCrimesWhereEvidenceExists(-1);

        assertNotNull(crimes);
        assertTrue(crimes.isEmpty());
    }
}
