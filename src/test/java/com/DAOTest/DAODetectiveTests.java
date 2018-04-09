package com.DAOTest;

import com.Additionals.ClassEqualsAsserts;
import com.Additionals.DAOAdditionals;
import com.Additionals.LogicAdditionals;
import com.DAO.DAODetective;
import com.DAO.DAOMan;
import com.DAO.interfaces.IDAODetective;
import com.DAO.interfaces.IDAOMan;
import com.logic.Detective;
import com.logic.Man;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class DAODetectiveTests {
    private static IDAODetective daoDetective;
    private static IDAOMan daoMan;
    private static DAOAdditionals daoAdditionals;

    @BeforeClass
    public static void getDAO() {
        daoDetective = new DAODetective();
        daoAdditionals = new DAOAdditionals();
        daoMan = new DAOMan();
    }

    @Test
    public void addDetective_NullInput() {
        assertEquals(false, daoDetective.addDetective(null));
    }

    @Test
    public void updateDetective_NullInput() {
        assertEquals(false, daoDetective.updateDetective(null));
    }

    @Test
    public void getDetectiveById() throws Exception  {
        Detective detective = LogicAdditionals.getDetectiveWithDates();
        if (!daoDetective.addDetective(detective))
            throw new Exception();

        try {
            Detective actualDetective = daoDetective.getDetectiveById(detective.getManId());
            assertNotNull(actualDetective);
            ClassEqualsAsserts.assertDetectiveEquals(detective, actualDetective);
        } finally {
            daoAdditionals.deleteDetective(detective);
        }
    }

    @Test
    public void getDetectiveById_NoDetective()  {
        Detective actualDetective = daoDetective.getDetectiveById(-1);
        assertNull(actualDetective);
    }

    @Test
    public void addDetective() throws Exception {
        Detective detective = LogicAdditionals.getDetectiveWithDates();

        try {
            boolean actualResult = daoDetective.addDetective(detective);

            assertTrue(actualResult);
        } finally {
            daoAdditionals.deleteDetective(detective);
        }
    }

    @Test
    public void addDetective_DetectiveExist() throws Exception {
        Detective someDetective = LogicAdditionals.getDetectiveWithDates();
        if (!daoDetective.addDetective(someDetective))
            throw new Exception();
        Detective detective = LogicAdditionals.getDetectiveWithDates();
        detective.setLogin(someDetective.getLogin());

        try {
            boolean actualResult = daoDetective.addDetective(detective);

            assertFalse(actualResult);
        } finally {
            daoAdditionals.deleteDetective(someDetective);
            daoAdditionals.deleteMan(detective);
        }
    }

    @Test
    public void addDetective_NotCorrectValue() {
        Detective detective = LogicAdditionals.getDetectiveWithDates();
        detective.setEmail(null);

        try {
            boolean actualResult = daoDetective.addDetective(detective);

            assertFalse(actualResult);
        } finally {
            daoAdditionals.deleteMan(detective);
        }
    }

    @Test
    public void updateDetective() throws Exception {
        Detective detective = LogicAdditionals.getDetectiveWithDates();
        if (!daoDetective.addDetective(detective))
            throw new Exception();
        detective.setHomeAddress("Виздзор Гарден, Лондон");
        detective.setName("Sherlock");
        detective.setPhotoPath("my best photo will be created soon");
        detective.setBirthDay(LocalDate.of(2015,12,25));
        detective.setSurname("Holms, sir!");
        detective.setLogin("NewLoginForCheaterTests");
        detective.setHashOfPassword("greatMD5Hash,godblessit");
        detective.setEmail("newyorker@email.com");

        try {
            boolean actualResult = daoDetective.updateDetective(detective);
            Detective actualDetective = daoDetective.getDetectiveById(detective.getManId());

            assertTrue(actualResult);
            ClassEqualsAsserts.assertDetectiveEquals(detective, actualDetective);
        } finally {
            daoAdditionals.deleteDetective(detective);
        }
    }

    @Test
    public void getDetectiveByLogin() throws Exception {
        Detective detective = LogicAdditionals.getDetectiveWithDates();
        if (!daoDetective.addDetective(detective))
            throw new Exception();

        try {
            Detective actualDetective = daoDetective.getDetectiveByLogin(detective.getLogin());
            assertNotNull(actualDetective);
            ClassEqualsAsserts.assertDetectiveEquals(detective, actualDetective);
        } finally {
            daoAdditionals.deleteDetective(detective);
        }
    }

    @Test
    public void getDetectiveByLogin_NoSuchDetective() {
        Detective actualDetective = daoDetective.getDetectiveByLogin(null);
        assertNull(actualDetective);
    }

    @Test
    public void existDetectiveWithLogin() throws Exception {
        Detective someDetective = LogicAdditionals.getDetectiveWithDates();
        if (!daoDetective.addDetective(someDetective))
            throw new Exception();

        try {
            boolean actualResult = daoDetective.existDetectiveWithLogin(someDetective.getLogin());

            assertTrue(actualResult);
        } finally {
            daoAdditionals.deleteDetective(someDetective);
        }
    }

    @Test
    public void existDetectiveWithLogin_NoSuchDetective() {
        boolean actualResult = daoDetective.existDetectiveWithLogin(null);

        assertFalse(actualResult);
    }

    @Test
    public void getAllDetectives() throws Exception {
        Detective someDetective = LogicAdditionals.getDetectiveWithDates();
        if (!daoDetective.addDetective(someDetective))
            throw new Exception();

        try {
            List<Man> men = daoDetective.getAllDetectives();

            assertNotNull(men);
            assertFalse(men.isEmpty());

            assertTrue(men.stream().anyMatch(o -> o.getManId() == someDetective.getManId()));
        } finally {
            daoAdditionals.deleteDetective(someDetective);
        }
    }
}
