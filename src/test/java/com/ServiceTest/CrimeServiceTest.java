package com.ServiceTest;

import com.Additionals.LogicAdditionals;
import com.DAO.interfaces.IDAOCrime;
import com.logic.Crime;
import com.services.CrimeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CrimeServiceTest {
    @Mock
    private IDAOCrime daoCrime;

    @InjectMocks
    private CrimeService service;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllCrimes() throws Exception {
        List<Crime> crimes = LogicAdditionals.getCrimesList();
        when(daoCrime.getAllCrimes()).thenReturn(crimes);

        List<Crime> actualCrimes = service.getAllCrimes();

        assertEquals(crimes, actualCrimes);
    }

    @Test
    public void getCrimesByCriminalCase() throws Exception {
        List<Crime> crimes = LogicAdditionals.getCrimesList();
        when(daoCrime.getCrimesByCriminalCase(anyLong())).thenReturn(crimes);

        List<Crime> actualCrimes = service.getCrimesByCriminalCase(1);

        assertEquals(crimes, actualCrimes);
    }

    @Test
    public void getCrimesBetweenDates() throws Exception {
        List<Crime> crimes = LogicAdditionals.getCrimesList();
        when(daoCrime.getCrimesBetweenDates(LocalDate.MIN, LocalDate.MAX)).thenReturn(crimes);

        List<Crime> actualCrimes = service.getCrimesBetweenDates(LocalDate.MIN, LocalDate.MAX);

        assertEquals(crimes, actualCrimes);
    }

    @Test
    public void getCrimeById() throws Exception {
        Crime crime = LogicAdditionals.getCustomCrime();
        when(daoCrime.getCrimeById(anyLong())).thenReturn(crime);

        Crime actualCrime = service.getCrimeById(1);

        assertEquals(crime, actualCrime);
    }

    @Test
    public void addCrime() throws Exception {
        Crime crime = LogicAdditionals.getCustomCrime();
        boolean expectedResult = true;
        when(daoCrime.addCrime(any(Crime.class))).thenReturn(expectedResult);

        boolean actualResult = service.addCrime(crime.getCriminalCaseId(), crime.getCrimeType().toString(), crime.getDescription(), crime.getCrimeDate(),crime.getCrimeTime(), crime.getCrimePlace());

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void addCrime_NotCorrectStatus() throws Exception {
        Crime crime = LogicAdditionals.getCustomCrime();
        boolean expectedResult = false;
        when(daoCrime.addCrime(any(Crime.class))).thenReturn(expectedResult);

        boolean actualResult = service.addCrime(crime.getCriminalCaseId(), crime.getCrimeType().getName(), crime.getDescription(), crime.getCrimeDate(),crime.getCrimeTime(), crime.getCrimePlace());

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void updateCrime() throws Exception {
        Crime crime = LogicAdditionals.getCustomCrime();
        boolean expectedResult = true;
        when(daoCrime.updateCrime(any(Crime.class))).thenReturn(expectedResult);

        boolean actualResult = service.updateCrime(crime.getCrimeId(), crime.getCriminalCaseId(), crime.getCrimeType().toString(), crime.getDescription(), crime.getCrimeDate(),crime.getCrimeTime(), crime.getCrimePlace());

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void updateCrime_NotCorrectStatus() throws Exception {
        Crime crime = LogicAdditionals.getCustomCrime();
        boolean expectedResult = false;
        when(daoCrime.updateCrime(any(Crime.class))).thenReturn(expectedResult);

        boolean actualResult = service.updateCrime(crime.getCrimeId(), crime.getCriminalCaseId(), crime.getCrimeType().getName(), crime.getDescription(), crime.getCrimeDate(),crime.getCrimeTime(), crime.getCrimePlace());

        assertEquals(expectedResult, actualResult);
    }
}
