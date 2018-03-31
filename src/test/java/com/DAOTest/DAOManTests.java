package com.DAOTest;

import com.Additionals.LogicAdditionals;
import com.DAO.DAOCrime;
import com.DAO.DAODetective;
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
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class DAOManTests {
    private static IDAOMan daoMan;
    private static IDAOCrime daoCrime;
    private static IDAOParticipant daoParticipant;

    @BeforeClass
    public static void getDAO() {
        daoMan = new DAOMan();
        daoCrime = new DAOCrime();
        daoParticipant = new DAOParticipant();
    }

    @Test
    public void addMan_NullInput()  {
        assertEquals(false, daoMan.addMan(null));
    }

    @Test
    public void updateMan_NullInput()  {
        assertEquals(false, daoMan.updateMan(null));
    }

    @Rollback
    @Test
    public void getFullManInfo() throws Exception  {
        Man man = LogicAdditionals.getCustomMan();
        if (!daoMan.addMan(man))
            throw new Exception();

        Man actualMan = daoMan.getFullManInfo(man.getManId());

        assertManEquals(man, actualMan);
    }
/*
    @Test
    public void getFullManInfo_NotExistingId() {
        Man expectedMan = null;
        long notCorrectId = -1;

        Man actualMan = daoMan.getFullManInfo(notCorrectId);

        assertManEquals(expectedMan, actualMan);
    }
*/
    @Rollback
    @Test
    public void addMan()  {
        Man man = LogicAdditionals.getCustomMan();
        boolean expectedResult = true;

        boolean actualResult = daoMan.addMan(man);

        assertEquals(actualResult, expectedResult);
    }

    @Rollback
    @Test
    public void addMan_NotCorrectValue()  {
        Man man = new Man();
        man.setName("someNameWithoutSurname");
        boolean expectedResult = false;

        boolean actualResult = daoMan.addMan(man);

        assertEquals(actualResult, expectedResult);
    }

    @Rollback
    @Test
    public void addMan_LimitField()  {
        Man man = new Man();
        man.setName("10letters_20letters_30letters_40letters_LIMIT!!!!!!");
        man.setSurname("Correct");
        boolean expectedResult = false;

        boolean actualResult = daoMan.addMan(man);

        assertEquals(actualResult, expectedResult);
    }

    @Rollback
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

        boolean actualResult = daoMan.updateMan(man);
        Man actualMan = daoMan.getFullManInfo(man.getManId());

        assertEquals(actualResult, expectedResult);
        assertManEquals(man, actualMan);
    }

    /*@Rollback
    @Test
    public void updateMan_NotCorrectId() throws Exception {
        Man man = LogicAdditionals.getCustomMan();
        if (!daoMan.addMan(man))
            throw new Exception();
        man.setManId(-2);
        boolean expectedResult = false;

        boolean actualResult = daoMan.updateMan(man);

        assertEquals(actualResult, expectedResult);
    }
*/
    @Rollback
    @Test
    public void updateMan_LimitOfField() throws Exception {
        Man man = LogicAdditionals.getCustomMan();
        if (!daoMan.addMan(man))
            throw new Exception();
        man.setName("10letters_20letters_30letters_40letters_LIMIT!!!!!!");
        boolean expectedResult = false;

        boolean actualResult = daoMan.updateMan(man);

        assertEquals(actualResult, expectedResult);
    }

    //TODO: look after method realization
    @Rollback
    @Test
    public void getAllManWithCrimeAmount_NoCrimes() throws Exception {
        Man man = LogicAdditionals.getCustomMan();
        if (!daoMan.addMan(man))
            throw new Exception();

        Map<Man,Long> mapping = daoMan.getAllManWithCrimeAmount();

        assertNotNull(mapping);
        assertNotEquals(0, mapping.size());
        long crimesAmount = mapping.get(man);
        assertEquals(0, crimesAmount);
    }

    @Rollback
    @Test
    public void getAllManWithCrimeAmount_CrimesExist() throws Exception {
        Man man = LogicAdditionals.getCustomMan();
        if (!daoMan.addMan(man))
            throw new Exception();
        Crime crime = LogicAdditionals.getCustomCrime();
        //TODO: убрать псоел исправления конфигураций для контроллера
        crime.setCrimeDate(LocalDate.of(2012,2,25));
        if (!daoCrime.addCrime(crime))
            throw new Exception();
        Participant participant = LogicAdditionals.getCustomParticipantWithDate();
        if (!daoParticipant.addParticipant(participant))
            throw new Exception();

        Map<Man,Long> mapping = daoMan.getAllManWithCrimeAmount();

        assertNotNull(mapping);
        assertNotEquals(0, mapping.size());
        long crimesAmount = mapping.get(man);
        assertEquals(1, crimesAmount);
    }

    @Rollback
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
