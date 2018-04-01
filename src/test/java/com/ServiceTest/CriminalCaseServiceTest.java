package com.ServiceTest;

import com.Additionals.LogicAdditionals;
import com.DAO.interfaces.IDAOCriminalCase;
import com.logic.CriminalCase;
import com.services.CriminalCaseService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CriminalCaseServiceTest {
    @Mock
    private IDAOCriminalCase daoCriminalCase;

    @InjectMocks
    private CriminalCaseService service;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllCriminalCases() throws Exception {
        List<CriminalCase> criminalCases = LogicAdditionals.getCriminalCases();
        when(daoCriminalCase.getAllCriminalCases()).thenReturn(criminalCases);

        List<CriminalCase> actualCriminalCases = service.getAllCriminalCases();

        assertEquals(criminalCases, actualCriminalCases);
    }

    @Test
    public void getAllSolvedCriminalCases() throws Exception {
        List<CriminalCase> criminalCases = LogicAdditionals.getCriminalCases();
        when(daoCriminalCase.getAllClosedSolvedCrimes()).thenReturn(criminalCases);

        List<CriminalCase> actualCriminalCases = service.getAllSolvedCriminalCases();

        assertEquals(criminalCases, actualCriminalCases);
    }

    @Test
    public void getAllUnsolvedCriminalCases() throws Exception {
        List<CriminalCase> criminalCases = LogicAdditionals.getCriminalCases();
        when(daoCriminalCase.getAllClosedUnsolvedCrimes()).thenReturn(criminalCases);

        List<CriminalCase> actualCriminalCases = service.getAllUnsolvedCriminalCases();

        assertEquals(criminalCases, actualCriminalCases);
    }

    @Test
    public void getAllOpenCriminalCases() throws Exception {
        List<CriminalCase> criminalCases = LogicAdditionals.getCriminalCases();
        when(daoCriminalCase.getAllOpenCrimes()).thenReturn(criminalCases);

        List<CriminalCase> actualCriminalCases = service.getAllOpenCriminalCases();

        assertEquals(criminalCases, actualCriminalCases);
    }

    @Test
    public void getCriminalCaseById() throws Exception {
        CriminalCase criminalCase = LogicAdditionals.getCustomCriminalCase();
        when(daoCriminalCase.getCriminalCaseById(anyLong())).thenReturn(criminalCase);

        CriminalCase actualCriminalCase = service.getCriminalCaseById(1);

        assertEquals(criminalCase, actualCriminalCase);
    }

    @Test
    public void addCriminalCase() throws Exception {
        CriminalCase criminalCase = LogicAdditionals.getCustomCriminalCase();
        boolean expectedResult = true;
        when(daoCriminalCase.addCriminalCase(any(CriminalCase.class))).thenReturn(expectedResult);

        boolean actualResult = service.addCriminalCase(criminalCase.getDetectiveId(), criminalCase.getCriminalCaseNumber(), criminalCase.getCreateDate(), criminalCase.isClosed(), criminalCase.getCloseDate());

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void updateCriminalCase() throws Exception {
        CriminalCase criminalCase = LogicAdditionals.getCustomCriminalCase();
        boolean expectedResult = true;
        when(daoCriminalCase.updateCriminalCase(any(CriminalCase.class))).thenReturn(expectedResult);

        boolean actualResult = service.updateCriminalCase(criminalCase.getCriminalCaseId(), criminalCase.getDetectiveId(), criminalCase.getCriminalCaseNumber(), criminalCase.getCreateDate(), criminalCase.isClosed(), criminalCase.getCloseDate());

        assertEquals(expectedResult, actualResult);
    }
}
