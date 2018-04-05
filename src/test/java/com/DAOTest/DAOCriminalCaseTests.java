package com.DAOTest;

import com.Additionals.AllClassesList;
import com.Additionals.ClassEqualsAsserts;
import com.Additionals.DAOAdditionals;
import com.Additionals.LogicAdditionals;
import com.DAO.DAOCriminalCase;
import com.DAO.DAODetective;
import com.DAO.interfaces.IDAOCriminalCase;
import com.DAO.interfaces.IDAODetective;
import com.logic.CriminalCase;
import com.logic.Detective;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class DAOCriminalCaseTests {
    private static IDAOCriminalCase daoCriminalCase;
    private static DAOAdditionals daoAdditionals;
    private static IDAODetective daoDetective;

    @BeforeClass
    public static void getDAO() {
        daoCriminalCase = new DAOCriminalCase();
        daoAdditionals = new DAOAdditionals();
        daoDetective = new DAODetective();
    }

    @Test
    public void addCriminalCase_NullInput()  {
        assertEquals(false, daoCriminalCase.addCriminalCase(null));
    }

    @Test
    public void updateCriminalCase_NullInput()  {
        assertEquals(false, daoCriminalCase.updateCriminalCase(null));
    }

    @Test
    public void getCriminalCaseById() throws Exception  {
        AllClassesList entities = new AllClassesList();
        entities.addSolvedCriminalCaseToDatabase(UUID.randomUUID().toString().substring(0,30));

        try {
            CriminalCase actualCriminalCase = daoCriminalCase.getCriminalCaseById(entities.getCriminalCase().getCriminalCaseId());
            assertNotNull(actualCriminalCase);
            ClassEqualsAsserts.assertCriminalCasesEquals(entities.getCriminalCase(), actualCriminalCase);
        } finally {
            entities.deleteAllAddedEntities();
        }
    }

    @Test
    public void getCriminalCaseById_NoCriminalCase()  {
        CriminalCase actualCriminalCase = daoCriminalCase.getCriminalCaseById(-1);
        assertNull(actualCriminalCase);
    }

    @Test
    public void addCriminalCase() throws Exception  {
        AllClassesList entities = new AllClassesList();
        entities.addCustomDetectiveToDatabase(UUID.randomUUID().toString().substring(0,30));

        CriminalCase criminalCase = LogicAdditionals.getCriminalCaseSolved();
        criminalCase.setDetectiveId(entities.getDetective().getManId());

        try {
            boolean actualResult = daoCriminalCase.addCriminalCase(criminalCase);

            assertTrue(actualResult);
            CriminalCase actualCriminalCase = daoCriminalCase.getCriminalCaseById(criminalCase.getCriminalCaseId());
            ClassEqualsAsserts.assertCriminalCasesEquals(criminalCase, actualCriminalCase);
        } finally {
            daoAdditionals.deleteCriminalCase(criminalCase);
            entities.deleteAllAddedEntities();
        }
    }

    @Test
    public void addCriminalCase_NoDetective() {
        CriminalCase criminalCase = LogicAdditionals.getCriminalCaseSolved();
        criminalCase.setDetectiveId(-1);
        criminalCase.setCloseDate(null);

        boolean actualResult = daoCriminalCase.addCriminalCase(criminalCase);

        assertFalse(actualResult);
    }

    @Test
    public void updateCriminalCase() throws Exception  {
        AllClassesList entities = new AllClassesList();
        entities.addSolvedCriminalCaseToDatabase(UUID.randomUUID().toString().substring(0,30));
        AllClassesList otherEntities = new AllClassesList();
        otherEntities.addCustomDetectiveToDatabase(UUID.randomUUID().toString().substring(0,30));

        entities.getCriminalCase().setDetectiveId(otherEntities.getDetective().getManId());
        entities.getCriminalCase().setCriminalCaseNumber("newNumber");
        entities.getCriminalCase().setClosed(false);
        entities.getCriminalCase().setCloseDate(null);
        entities.getCriminalCase().setCreateDate(LocalDate.now());

        try {
            boolean actualResult = daoCriminalCase.updateCriminalCase(entities.getCriminalCase());

            assertTrue(actualResult);
            CriminalCase actualCriminalCase = daoCriminalCase.getCriminalCaseById(entities.getCriminalCase().getCriminalCaseId());
            ClassEqualsAsserts.assertCriminalCasesEquals(entities.getCriminalCase(), actualCriminalCase);
        } finally {
            entities.deleteAllAddedEntities();
            otherEntities.deleteAllAddedEntities();
        }
    }

    @Test
    public void updateCriminalCase_NoDetective() throws Exception {
        AllClassesList entities = new AllClassesList();
        entities.addSolvedCriminalCaseToDatabase(UUID.randomUUID().toString().substring(0,30));

        CriminalCase criminalCase = entities.getCriminalCase();
        criminalCase.setDetectiveId(-1);

        try {
            boolean actualResult = daoCriminalCase.updateCriminalCase(criminalCase);

            assertFalse(actualResult);
        } finally {
            entities.deleteAllAddedEntities();
        }
    }

    @Test
    public void getAllCriminalCases() throws Exception  {
        AllClassesList entities = new AllClassesList();
        entities.addSolvedCriminalCaseToDatabase(UUID.randomUUID().toString().substring(0,30));
        AllClassesList entities2 = new AllClassesList();
        entities2.addUnsolvedCriminalCaseToDatabase(UUID.randomUUID().toString().substring(0,30));
        AllClassesList entities3 = new AllClassesList();
        entities3.addOpenCriminalCaseToDatabase(UUID.randomUUID().toString().substring(0,30));

        try {
            List<CriminalCase> criminalCases = daoCriminalCase.getAllCriminalCases();

            assertNotNull(criminalCases);
            assertFalse(criminalCases.isEmpty());

            assertTrue(criminalCases.stream().anyMatch(o -> o.getCriminalCaseId() == entities.getCriminalCase().getCriminalCaseId()));
            Optional<CriminalCase> optional = criminalCases.stream().
                    filter(p -> p.getCriminalCaseId() == entities.getCriminalCase().getCriminalCaseId()).findFirst();
            ClassEqualsAsserts.assertCriminalCasesEquals(entities.getCriminalCase(), optional.orElse(null));

            assertTrue(criminalCases.stream().anyMatch(o -> o.getCriminalCaseId() == entities2.getCriminalCase().getCriminalCaseId()));
            optional = criminalCases.stream().
                    filter(p -> p.getCriminalCaseId() == entities2.getCriminalCase().getCriminalCaseId()).findFirst();
            ClassEqualsAsserts.assertCriminalCasesEquals(entities2.getCriminalCase(), optional.orElse(null));

            assertTrue(criminalCases.stream().anyMatch(o -> o.getCriminalCaseId() == entities3.getCriminalCase().getCriminalCaseId()));
            optional = criminalCases.stream().
                    filter(p -> p.getCriminalCaseId() == entities3.getCriminalCase().getCriminalCaseId()).findFirst();
            ClassEqualsAsserts.assertCriminalCasesEquals(entities3.getCriminalCase(), optional.orElse(null));
        } finally {
            entities.deleteAllAddedEntities();
            entities2.deleteAllAddedEntities();
            entities3.deleteAllAddedEntities();
        }
    }

    @Test
    public void getAllClosedSolvedCrimes() throws Exception  {
        AllClassesList entities = new AllClassesList();
        entities.addSolvedCriminalCaseToDatabase(UUID.randomUUID().toString().substring(0,30));
        AllClassesList entities2 = new AllClassesList();
        entities2.addUnsolvedCriminalCaseToDatabase(UUID.randomUUID().toString().substring(0,30));
        AllClassesList entities3 = new AllClassesList();
        entities3.addOpenCriminalCaseToDatabase(UUID.randomUUID().toString().substring(0,30));

        try {
            List<CriminalCase> criminalCases = daoCriminalCase.getAllClosedSolvedCrimes();

            assertNotNull(criminalCases);
            assertFalse(criminalCases.isEmpty());

            assertTrue(criminalCases.stream().anyMatch(o -> o.getCriminalCaseId() == entities.getCriminalCase().getCriminalCaseId()));
            Optional<CriminalCase> optional = criminalCases.stream().
                    filter(p -> p.getCriminalCaseId() == entities.getCriminalCase().getCriminalCaseId()).findFirst();
            ClassEqualsAsserts.assertCriminalCasesEquals(entities.getCriminalCase(), optional.orElse(null));

            assertFalse(criminalCases.stream().anyMatch(o -> o.getCriminalCaseId() == entities2.getCriminalCase().getCriminalCaseId()));

            assertFalse(criminalCases.stream().anyMatch(o -> o.getCriminalCaseId() == entities3.getCriminalCase().getCriminalCaseId()));
        } finally {
            entities.deleteAllAddedEntities();
            entities2.deleteAllAddedEntities();
            entities3.deleteAllAddedEntities();
        }
    }

    @Test
    public void getAllClosedUnsolvedCrimes() throws Exception  {
        AllClassesList entities = new AllClassesList();
        entities.addSolvedCriminalCaseToDatabase(UUID.randomUUID().toString().substring(0,30));
        AllClassesList entities2 = new AllClassesList();
        entities2.addUnsolvedCriminalCaseToDatabase(UUID.randomUUID().toString().substring(0,30));
        AllClassesList entities3 = new AllClassesList();
        entities3.addOpenCriminalCaseToDatabase(UUID.randomUUID().toString().substring(0,30));

        try {
            List<CriminalCase> criminalCases = daoCriminalCase.getAllClosedUnsolvedCrimes();

            assertNotNull(criminalCases);
            assertFalse(criminalCases.isEmpty());

            assertFalse(criminalCases.stream().anyMatch(o -> o.getCriminalCaseId() == entities.getCriminalCase().getCriminalCaseId()));

            assertTrue(criminalCases.stream().anyMatch(o -> o.getCriminalCaseId() == entities2.getCriminalCase().getCriminalCaseId()));
            Optional<CriminalCase> optional = criminalCases.stream().
                    filter(p -> p.getCriminalCaseId() == entities2.getCriminalCase().getCriminalCaseId()).findFirst();
            ClassEqualsAsserts.assertCriminalCasesEquals(entities2.getCriminalCase(), optional.orElse(null));

            assertFalse(criminalCases.stream().anyMatch(o -> o.getCriminalCaseId() == entities3.getCriminalCase().getCriminalCaseId()));
        } finally {
            entities.deleteAllAddedEntities();
            entities2.deleteAllAddedEntities();
            entities3.deleteAllAddedEntities();
        }
    }

    @Test
    public void getAllOpenCrimes() throws Exception  {
        AllClassesList entities = new AllClassesList();
        entities.addSolvedCriminalCaseToDatabase(UUID.randomUUID().toString().substring(0,30));
        AllClassesList entities2 = new AllClassesList();
        entities2.addUnsolvedCriminalCaseToDatabase(UUID.randomUUID().toString().substring(0,30));
        AllClassesList entities3 = new AllClassesList();
        entities3.addOpenCriminalCaseToDatabase(UUID.randomUUID().toString().substring(0,30));

        try {
            List<CriminalCase> criminalCases = daoCriminalCase.getAllOpenCrimes();

            assertNotNull(criminalCases);
            assertFalse(criminalCases.isEmpty());

            assertFalse(criminalCases.stream().anyMatch(o -> o.getCriminalCaseId() == entities.getCriminalCase().getCriminalCaseId()));

            assertFalse(criminalCases.stream().anyMatch(o -> o.getCriminalCaseId() == entities2.getCriminalCase().getCriminalCaseId()));

            assertTrue(criminalCases.stream().anyMatch(o -> o.getCriminalCaseId() == entities3.getCriminalCase().getCriminalCaseId()));
            Optional<CriminalCase>  optional = criminalCases.stream().
                    filter(p -> p.getCriminalCaseId() == entities3.getCriminalCase().getCriminalCaseId()).findFirst();
            ClassEqualsAsserts.assertCriminalCasesEquals(entities3.getCriminalCase(), optional.orElse(null));
        } finally {
            entities.deleteAllAddedEntities();
            entities2.deleteAllAddedEntities();
            entities3.deleteAllAddedEntities();
        }
    }

    @Test
    public void getAllCrimesOfDetective() throws Exception  {
        AllClassesList entities = new AllClassesList();
        entities.addSolvedCriminalCaseToDatabase(UUID.randomUUID().toString().substring(0,30));
        CriminalCase criminalCase = LogicAdditionals.getCriminalCaseOpen();
        criminalCase.setDetectiveId(entities.getDetective().getManId());
        if (!daoCriminalCase.addCriminalCase(criminalCase))
            throw new Exception();

        try {
            List<CriminalCase> criminalCases = daoCriminalCase.getAllCrimesOfDetective(entities.getDetective().getManId());

            assertNotNull(criminalCases);
            assertEquals(2,criminalCases.size());

            assertTrue(criminalCases.stream().anyMatch(o -> o.getCriminalCaseId() == entities.getCriminalCase().getCriminalCaseId()));
            Optional<CriminalCase> optional = criminalCases.stream().
                    filter(p -> p.getCriminalCaseId() == entities.getCriminalCase().getCriminalCaseId()).findFirst();
            ClassEqualsAsserts.assertCriminalCasesEquals(entities.getCriminalCase(), optional.orElse(null));

            assertTrue(criminalCases.stream().anyMatch(o -> o.getCriminalCaseId() == criminalCase.getCriminalCaseId()));
            optional = criminalCases.stream().
                    filter(p -> p.getCriminalCaseId() == criminalCase.getCriminalCaseId()).findFirst();
            ClassEqualsAsserts.assertCriminalCasesEquals(criminalCase, optional.orElse(null));
        } finally {
            daoAdditionals.deleteCriminalCase(criminalCase);
            entities.deleteAllAddedEntities();
        }
    }

    @Test
    public void getAllCrimesOfDetective_NoCriminalCases() throws Exception  {
        AllClassesList entities = new AllClassesList();
        entities.addCustomDetectiveToDatabase(UUID.randomUUID().toString().substring(0,30));

        try {
            List<CriminalCase> criminalCases = daoCriminalCase.getAllCrimesOfDetective(entities.getDetective().getManId());

            assertNotNull(criminalCases);
            assertTrue(criminalCases.isEmpty());
        } finally {
            entities.deleteAllAddedEntities();
        }
    }
}
