package com.DAOTest;

import com.DAO.DAODetective;
import com.DAO.interfaces.IDAODetective;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class DAODetectiveTests {
    private static IDAODetective daoDetective;

    @BeforeClass
    public static void getDAO() {
        daoDetective = new DAODetective();
    }

    @Test
    public void addDetectiveNull()  {
        assertEquals(false, daoDetective.addDetective(null));
    }
}
