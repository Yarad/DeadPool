package com.DAOTest;

import com.Additionals.AllClassesList;
import com.Additionals.ClassEqualsAsserts;
import com.Additionals.DAOAdditionals;
import com.Additionals.LogicAdditionals;
import com.DAO.DAOCrime;
import com.DAO.DAOMan;
import com.DAO.DAOParticipant;
import com.DAO.interfaces.IDAOCrime;
import com.DAO.interfaces.IDAOMan;
import com.DAO.interfaces.IDAOParticipant;
import com.logic.Crime;
import com.logic.Man;
import com.logic.Participant;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.*;

public class DAOManTests {
    private static IDAOMan daoMan;
    private static IDAOCrime daoCrime;
    private static IDAOParticipant daoParticipant;
    private static DAOAdditionals daoAdditionals;

    @BeforeClass
    public static void getDAO() {
        daoMan = new DAOMan();
        daoCrime = new DAOCrime();
        daoParticipant = new DAOParticipant();
        daoAdditionals = new DAOAdditionals();
    }

    @Test
    public void addMan_NullInput()  {
        assertEquals(false, daoMan.addMan(null));
    }

    @Test
    public void updateMan_NullInput()  {
        assertEquals(false, daoMan.updateMan(null));
    }

    @Test
    public void getFullManInfo() throws Exception  {
        Man man = LogicAdditionals.getCustomMan();
        if (!daoMan.addMan(man))
            throw new Exception();

        try {
            Man actualMan = daoMan.getFullManInfo(man.getManId());
            assertNotNull(actualMan);
            ClassEqualsAsserts.assertManEquals(man, actualMan);
        } finally {
            daoAdditionals.deleteMan(man);
        }
    }

    @Test
    public void getFullManInfo_NoMan() throws Exception  {
        Man actualMan = daoMan.getFullManInfo(-1);
        assertNull(actualMan);
    }

    @Test
    public void addMan()  {
        Man man = LogicAdditionals.getCustomMan();

        try {
            boolean actualResult = daoMan.addMan(man);

            assertTrue(actualResult);
        } finally {
            daoAdditionals.deleteMan(man);
        }
    }

    @Test
    public void addMan_NotCorrectValue()  {
        Man man = new Man();
        man.setName("someNameWithoutSurname");

        boolean actualResult = daoMan.addMan(man);

        assertFalse(actualResult);
    }

    @Test
    public void addMan_LimitField()  {
        Man man = new Man();
        man.setName("10letters_20letters_30letters_40letters_LIMIT!!!!!!");
        man.setSurname("Correct");

        boolean actualResult = daoMan.addMan(man);

        assertFalse(actualResult);
    }

    @Test
    public void updateMan() throws Exception {
        Man man = LogicAdditionals.getCustomMan();
        if (!daoMan.addMan(man))
            throw new Exception();
        man.setHomeAddress("Виздзор Гарден, Лондон");
        man.setName("Sherlock");
        man.setPhotoPath("my best photo will be created soon");
        man.setBirthDay(LocalDate.of(2015,12,25));
        man.setSurname("Holms, sir!");

        try {
            boolean actualResult = daoMan.updateMan(man);
            Man actualMan = daoMan.getFullManInfo(man.getManId());

            assertTrue(actualResult);
            ClassEqualsAsserts.assertManEquals(man, actualMan);
        } finally {
            daoAdditionals.deleteMan(man);
        }
    }

    @Test
    public void updateMan_LimitOfField() throws Exception {
        Man man = LogicAdditionals.getCustomMan();
        if (!daoMan.addMan(man))
            throw new Exception();
        man.setName("10letters_20letters_30letters_40letters_LIMIT!!!!!!");

        try {
            boolean actualResult = daoMan.updateMan(man);

            assertFalse(actualResult);
        } finally {
            daoAdditionals.deleteMan(man);
        }
    }

    @Test
    public void getAllManWithCrimeAmount_NoCrimes() throws Exception {
        Man man = LogicAdditionals.getCustomMan();
        if (!daoMan.addMan(man))
            throw new Exception();

        try {
            Map<Man,Long> mapping = daoMan.getAllManWithCrimeAmount();

            assertNotNull(mapping);
            Long crimesAmount = mapping.get(man);
            assertNull(crimesAmount);
        } finally {
            daoAdditionals.deleteMan(man);
        }
    }

    @Test
    public void getAllManWithCrimeAmount_CrimesExist() throws Exception {
        AllClassesList entities = new AllClassesList();
        entities.addCustomParticipantToDatabase(UUID.randomUUID().toString().substring(0,30));

        try {
            Map<Man,Long> mapping = daoMan.getAllManWithCrimeAmount();

            assertNotNull(mapping);
            assertNotEquals(0, mapping.size());
            Long crimesAmount = mapping.get(entities.getMan());
            assertEquals(1, crimesAmount.longValue());
        } finally {
            entities.deleteAllAddedEntities();
        }
    }

    @Test
    public void getAllManWithCrimeAmount_NoManInMap() throws Exception {
        Man man = LogicAdditionals.getCustomMan();

        Map<Man,Long> mapping = daoMan.getAllManWithCrimeAmount();

        assertNotNull(mapping);
        boolean wasThorwn = false;
        try {
            long value = mapping.get(man);
        } catch (Exception ex) {
            wasThorwn = true;
        }
        assertTrue(wasThorwn);
    }
}
