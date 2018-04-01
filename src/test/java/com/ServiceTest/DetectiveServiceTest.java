package com.ServiceTest;

import com.Additionals.LogicAdditionals;
import com.DAO.interfaces.IDAODetective;
import com.logic.Detective;
import com.logic.Man;
import com.services.DetectiveService;
import com.services.HashService;
import com.services.interfaces.IHashService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class DetectiveServiceTest {
    @Mock
    private IDAODetective daoDetective;

    @Mock
    private IHashService hashService;

    @InjectMocks
    private DetectiveService service;

    private static IHashService realHashService;

    @BeforeClass
    public static void getDAO() {
        realHashService = new HashService();
    }

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllDetectives() throws Exception {
        List<Man> men = LogicAdditionals.getManList();
        when(daoDetective.getAllDetectives()).thenReturn(men);

        List<Man> actualMen = service.getAllDetectives();

        assertEquals(men, actualMen);
    }

    @Test
    public void getDetectiveByLogin() throws Exception {
        Detective detective = LogicAdditionals.getCustomDetective();
        when(daoDetective.getDetectiveByLogin(detective.getLogin())).thenReturn(detective);

        Detective actualDetective = service.getDetectiveByLogin(detective.getLogin());

        assertEquals(detective, actualDetective);
    }

    @Test
    public void existDetectiveWithLogin() throws Exception {
        boolean expectedResult = true;
        when(daoDetective.existDetectiveWithLogin(anyString())).thenReturn(expectedResult);

        boolean actualResult = service.existDetectiveWithLogin("someLogin");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void addDetective() throws Exception {
        Detective detective = LogicAdditionals.getCustomDetective();
        boolean expectedResult = true;
        when(daoDetective.addDetective(any(Detective.class))).thenReturn(expectedResult);
        when(hashService.getMD5Hash(anyString())).thenReturn(realHashService.getMD5Hash("anyMd5Hash"));

        boolean actualResult = service.addDetective(detective.getName(), detective.getSurname(), detective.getBirthDay(), detective.getHomeAddress(), detective.getPhotoPath(), detective.getLogin(), detective.getHashOfPassword(), detective.getEmail());

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void updateDetective() throws Exception {
        Detective detective = LogicAdditionals.getCustomDetective();
        boolean expectedResult = true;
        when(daoDetective.updateDetective(any(Detective.class))).thenReturn(expectedResult);
        when(hashService.getMD5Hash(anyString())).thenReturn(realHashService.getMD5Hash("anyMd5Hash"));

        boolean actualResult = service.updateDetective(detective.getManId(), detective.getName(), detective.getSurname(), detective.getBirthDay(), detective.getHomeAddress(), detective.getPhotoPath(), detective.getLogin(), detective.getHashOfPassword(), detective.getEmail());

        assertEquals(expectedResult, actualResult);
    }
}
