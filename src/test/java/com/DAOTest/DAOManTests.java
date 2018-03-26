package com.DAOTest;

import com.DAO.DAODetective;
import com.DAO.DAOMan;
import com.DAO.interfaces.IDAOMan;
import com.logic.Man;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class DAOManTests {
    private static IDAOMan daoMan;
    private Man man;
    private Man actualMan;

    @BeforeClass
    public static void getDAO() {
        daoMan = new DAOMan();
    }

    @Test
    public void nullValues()  {
        assertEquals(false, daoMan.addMan(null));
        assertEquals(false, daoMan.updateMan(null));
    }

    @Test
    public void complexCorrectWork()  {
        Man man = new Man();
        man.setName("someName");
        man.setSurname("someSurname");
        assertEquals(true, daoMan.addMan(man));

        Man actualMan = daoMan.getFullManInfo(man.getManId());
        assertManEquals(man, actualMan);

        man.setName("10letters_20letters_30letters_40letters_");
        man.setSurname("Surname_10Surname_20Surname_30Surname_40");
        man.setPhotoPath("IDAOEvidence & IDAOEvidenceOfCrime имеют один и тот же метод getAllEvidencesByCrime. Надо оптимизировать. Что мне надо? Все EvidenceOfCrime, у которых заполнено Evidence & EvidenceType. Я использую только у себбя только метод IDAOEvidenceOfCrime. Над");
        man.setHomeAddress("someText");
        man.setBirthDay(LocalDate.of(2016, 12,31));
        assertEquals(true, daoMan.updateMan(man));

        actualMan = daoMan.getFullManInfo(man.getManId());
        assertManEquals(man, actualMan);

        //TODO: откомментировать после написания ДАО
        /*
        Map<Man,Long> mapping = daoMan.getAllManWithCrimeAmount();
        assertNotNull(mapping);
        assertEquals(0, mapping.get(actualMan).longValue());
        */
    }

    @Test
    public void limitAndExceptionWork()  {
        Man man = new Man();
        man.setName("10letters_20letters_30letters_40letters_L");
        man.setSurname("someSurname");
        assertEquals(false, daoMan.addMan(man));

        man.setName("no_limitation");
        man.setSurname("10letters_20letters_30letters_40letters_L");
        assertEquals(false, daoMan.addMan(man));

        man.setPhotoPath("IDAOEvidence & IDAOEvidenceOfCrime имеют один и тот же метод getAllEvidencesByCrime. Надо оптимизировать. Что мне надо? Все EvidenceOfCrime, у которых заполнено Evidence & EvidenceType. Я использую только у себбя только метод IDAOEvidenceOfCrime. Надо");
        man.setSurname("no_limitation");
        assertEquals(false, daoMan.addMan(man));

        man.setPhotoPath("no_limitation");
        man.setBirthDay(LocalDate.of(2012,5,31));
        assertEquals(true, daoMan.addMan(man));

        man.setName("10letters_20letters_30letters_40letters_L");
        assertEquals(false, daoMan.updateMan(man));

        man.setName("no_limitation");
        man.setSurname("10letters_20letters_30letters_40letters_L");
        assertEquals(false, daoMan.updateMan(man));

        man.setPhotoPath("IDAOEvidence & IDAOEvidenceOfCrime имеют один и тот же метод getAllEvidencesByCrime. Надо оптимизировать. Что мне надо? Все EvidenceOfCrime, у которых заполнено Evidence & EvidenceType. Я использую только у себбя только метод IDAOEvidenceOfCrime. Надо");
        man.setSurname("no_limitation");
        assertEquals(false, daoMan.updateMan(man));

        man.setPhotoPath("no_limitation");
        man.setBirthDay(null);
        assertEquals(true, daoMan.updateMan(man));
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
