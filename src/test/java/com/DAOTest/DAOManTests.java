package com.DAOTest;

import com.Additionals.AllClassesList;
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

            assertManEquals(man, actualMan);
        } finally {
            daoAdditionals.deleteMan(man);
        }
    }

    @Test
    public void addMan()  {
        Man man = LogicAdditionals.getCustomMan();
        boolean expectedResult = true;

        try {
            boolean actualResult = daoMan.addMan(man);

            assertEquals(actualResult, expectedResult);
        } finally {
            daoAdditionals.deleteMan(man);
        }
    }

    @Test
    public void addMan_NotCorrectValue()  {
        Man man = new Man();
        man.setName("someNameWithoutSurname");
        boolean expectedResult = false;

        boolean actualResult = daoMan.addMan(man);

        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void addMan_LimitField()  {
        Man man = new Man();
        man.setName("10letters_20letters_30letters_40letters_LIMIT!!!!!!");
        man.setSurname("Correct");
        boolean expectedResult = false;

        boolean actualResult = daoMan.addMan(man);

        assertEquals(actualResult, expectedResult);
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
        boolean expectedResult = true;

        try {
            boolean actualResult = daoMan.updateMan(man);
            Man actualMan = daoMan.getFullManInfo(man.getManId());

            assertEquals(actualResult, expectedResult);
            assertManEquals(man, actualMan);
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
        boolean expectedResult = false;

        try {
            boolean actualResult = daoMan.updateMan(man);

            assertEquals(actualResult, expectedResult);
        } finally {
            daoAdditionals.deleteMan(man);
        }
    }

    //TODO: look after method realization
    @Test
    public void getAllManWithCrimeAmount_NoCrimes() throws Exception {
        Man man = LogicAdditionals.getCustomMan();
        if (!daoMan.addMan(man))
            throw new Exception();

        try {
            Map<Man,Long> mapping = daoMan.getAllManWithCrimeAmount();

            assertNotNull(mapping);
            assertNotEquals(0, mapping.size());
            long crimesAmount = mapping.get(man);
            assertEquals(0, crimesAmount);
        } finally {
            daoAdditionals.deleteMan(man);
        }
    }

    //TODO: look after method realization
    @Test
    public void getAllManWithCrimeAmount_CrimesExist() throws Exception {
        AllClassesList entities = new AllClassesList();
        entities.addCustomParticipantToDatabase();

        try {
            Map<Man,Long> mapping = daoMan.getAllManWithCrimeAmount();

            assertNotNull(mapping);
            assertNotEquals(0, mapping.size());
            long crimesAmount = mapping.get(entities.getMan());
            assertEquals(1, crimesAmount);
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
        assertEquals(true, wasThorwn);
    }

    private void assertManEquals(Man expectedMan, Man actualMan) {
        assertEquals(expectedMan.getManId(), actualMan.getManId());
        assertEquals(expectedMan.getName(), actualMan.getName());
        assertEquals(expectedMan.getPhotoPath(), actualMan.getPhotoPath());
        assertEquals(expectedMan.getSurname(), actualMan.getSurname());
        assertEquals(expectedMan.getBirthDay(), actualMan.getBirthDay());
        assertEquals(expectedMan.getHomeAddress(), actualMan.getHomeAddress());
    }
}
