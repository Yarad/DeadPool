package com.ServiceTest;

import com.Additionals.LogicAdditionals;
import com.DAO.interfaces.IDAOCriminalCase;
import com.DAO.interfaces.IDAOEvidenceOfCrime;
import com.logic.CriminalCase;
import com.logic.EvidenceOfCrime;
import com.services.CriminalCaseService;
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
}
