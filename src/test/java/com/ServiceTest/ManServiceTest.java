package com.ServiceTest;

import com.Additionals.LogicAdditionals;
import com.DAO.interfaces.IDAOMan;
import com.logic.Man;
import com.services.ManService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class ManServiceTest {
    @Mock
    private IDAOMan daoMan;

    @InjectMocks
    private ManService service;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getFullManInfo() throws Exception {
        Man man = LogicAdditionals.getCustomMan();
        when(daoMan.getFullManInfo(anyLong())).thenReturn(man);

        Man actualMan = service.getFullManInfo(1);

        assertEquals(man, actualMan);
    }

    @Test
    public void getAllManWithCrimeAmount() throws Exception {
        Map<Man,Long> man = LogicAdditionals.getMapOfManAndTheirCrime();
        when(daoMan.getAllManWithCrimeAmount()).thenReturn(man);

        Map<Man,Long> actualMan = service.getAllManWithCrimeAmount();

        assertEquals(man, actualMan);
    }

    @Test
    public void addMan() throws Exception {
        Man man = LogicAdditionals.getCustomMan();
        boolean expectedResult = true;
        when(daoMan.addMan(any(Man.class))).thenReturn(expectedResult);

        boolean actualResult = service.addMan(man.getName(), man.getSurname(), man.getBirthDay(), man.getHomeAddress(), man.getPhotoPath());

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void updateMan() throws Exception {
        Man man = LogicAdditionals.getCustomMan();
        boolean expectedResult = true;
        when(daoMan.updateMan(any(Man.class))).thenReturn(expectedResult);

        boolean actualResult = service.updateMan(man.getManId(), man.getName(), man.getSurname(), man.getBirthDay(), man.getHomeAddress(), man.getPhotoPath());

        assertEquals(expectedResult, actualResult);
    }
}
