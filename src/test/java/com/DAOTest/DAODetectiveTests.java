package com.DAOTest;

import com.DAO.DAODetective;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class DAODetectiveTests {
    DAODetective daoDetective = mock(DAODetective.class);

    @Test
    public void detectiveAdd()  {
        //when(daoDetective.addDetective(null)).thenReturn(false);
        assertEquals(false, daoDetective.addDetective(null));
    }
}
