package com.DAOTest;

import com.DAO.DAODetective;
import com.DAO.DAOMan;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class DAOManTests {
    DAOMan daoMan = mock(DAOMan.class);

    @Test
    public void addMan()  {
        //when(daoDetective.addDetective(null)).thenReturn(false);
        assertEquals(false, daoMan.addMan(null));
    }
}
