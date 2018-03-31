package com.ServiceTest;

import com.Additionals.LogicAdditionals;
import com.DAO.interfaces.IDAOEvidenceOfCrime;
import com.logic.EvidenceOfCrime;
import com.services.EvidenceOfCrimeService;
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

public class EvidenceOfCrimeServiceTest {
    @Mock
    private IDAOEvidenceOfCrime daoEvidenceOfCrime;

    @InjectMocks
    private EvidenceOfCrimeService service;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllEvidencesOfCrime() throws Exception {
        List<EvidenceOfCrime> evidenceOfCrimes = LogicAdditionals.getEvidenceOfCrimeList();
        when(daoEvidenceOfCrime.getAllEvidencesOfCrime()).thenReturn(evidenceOfCrimes);

        List<EvidenceOfCrime> actualEvidenceOfCrimes = service.getAllEvidencesOfCrime();

        assertEquals(evidenceOfCrimes, actualEvidenceOfCrimes);
    }

    @Test
    public void getEvidencesOfCrimeByCrimeId() throws Exception {
        List<EvidenceOfCrime> evidenceOfCrimes = LogicAdditionals.getEvidenceOfCrimeList();
        when(daoEvidenceOfCrime.getAllEvidencesOfCrimeByCrimeId(anyLong())).thenReturn(evidenceOfCrimes);

        List<EvidenceOfCrime> actualEvidenceOfCrimes = service.getEvidencesOfCrimeByCrimeId(1);

        assertEquals(evidenceOfCrimes, actualEvidenceOfCrimes);
    }

    @Test
    public void getEvidencesOfCrimeByEvidenceId() throws Exception {
        List<EvidenceOfCrime> evidenceOfCrimes = LogicAdditionals.getEvidenceOfCrimeList();
        when(daoEvidenceOfCrime.getAllEvidencesOfCrimeByEvidenceId(anyLong())).thenReturn(evidenceOfCrimes);

        List<EvidenceOfCrime> actualEvidenceOfCrimes = service.getEvidencesOfCrimeByEvidenceId(1);

        assertEquals(evidenceOfCrimes, actualEvidenceOfCrimes);
    }

    @Test
    public void getEvidenceOfCrimeByEvidenceAndCrime() throws Exception {
        EvidenceOfCrime evidenceOfCrime = LogicAdditionals.getCustomEvidenceOfCrime();
        when(daoEvidenceOfCrime.getEvidenceOfCrime(anyLong(), anyLong())).thenReturn(evidenceOfCrime);

        EvidenceOfCrime actualEvidenceOfCrime = service.getEvidenceOfCrimeByEvidenceAndCrime(1,1);

        assertEquals(evidenceOfCrime, actualEvidenceOfCrime);
    }

    @Test
    public void addEvidenceOfCrime() throws Exception {
        EvidenceOfCrime evidenceOfCrime = LogicAdditionals.getCustomEvidenceOfCrime();
        boolean expectedResult = true;
        when(daoEvidenceOfCrime.addEvidenceOfCrime(any(EvidenceOfCrime.class))).thenReturn(expectedResult);

        boolean actualResult = service.addEvidenceOfCrime(evidenceOfCrime.parentEvidence.getEvidenceId(), evidenceOfCrime.parentCrime.getCrimeId(), evidenceOfCrime.getEvidenceType().toString(), evidenceOfCrime.getDateAdded(), evidenceOfCrime.getDetails(), evidenceOfCrime.getPhotoPath());

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void updateEvidenceOfCrime() throws Exception {
        EvidenceOfCrime evidenceOfCrime = LogicAdditionals.getCustomEvidenceOfCrime();
        boolean expectedResult = true;
        when(daoEvidenceOfCrime.updateEvidenceOfCrime(any(EvidenceOfCrime.class))).thenReturn(expectedResult);

        boolean actualResult = service.updateEvidenceOfCrime(evidenceOfCrime.parentEvidence.getEvidenceId(), evidenceOfCrime.parentCrime.getCrimeId(), evidenceOfCrime.getEvidenceType().toString(), evidenceOfCrime.getDateAdded(), evidenceOfCrime.getDetails(), evidenceOfCrime.getPhotoPath());

        assertEquals(expectedResult, actualResult);
    }
}
