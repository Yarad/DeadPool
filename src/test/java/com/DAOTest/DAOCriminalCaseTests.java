package com.DAOTest;

import com.DAO.DAOCriminalCase;
import com.DAO.interfaces.IDAOCriminalCase;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DAOCriminalCaseTests {
    private static IDAOCriminalCase daoCriminalCase;

    @BeforeClass
    public static void getDAO() {
        daoCriminalCase = new DAOCriminalCase();
    }

    @Test
    public void addCriminalCaseNull()  {
        assertEquals(false, daoCriminalCase.addCriminalCase(null));
    }
}
