package com.ServiceTest;

import com.Additionals.LogicAdditionals;
import com.DAO.interfaces.IDAOEvidence;
import com.logic.Evidence;
import com.services.EvidenceService;
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

public class EvidenceServiceTest {
    @Mock
    private IDAOEvidence daoEvidence;

    @InjectMocks
    private EvidenceService service;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getEvidenceById() throws Exception {
        Evidence evidence = LogicAdditionals.getCustomEvidence();
        when(daoEvidence.getEvidenceById(anyLong())).thenReturn(evidence);

        Evidence actualEvidence = service.getEvidenceById(1);

        assertEquals(evidence, actualEvidence);
    }

    @Test
    public void addEvidence() throws Exception {
        Evidence evidence = LogicAdditionals.getCustomEvidence();
        boolean expectedResult = true;
        when(daoEvidence.addEvidence(any(Evidence.class))).thenReturn(expectedResult);

        boolean actualResult = service.addEvidence(evidence.getName(), evidence.getDescription());

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void updateEvidence() throws Exception {
        Evidence evidence = LogicAdditionals.getCustomEvidence();
        boolean expectedResult = true;
        when(daoEvidence.updateEvidence(any(Evidence.class))).thenReturn(expectedResult);

        boolean actualResult = service.updateEvidence(evidence.getEvidenceId(), evidence.getName(), evidence.getDescription());

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getAllEvidences() throws Exception {
        List<Evidence> evidences = LogicAdditionals.getEvidenceList();
        when(daoEvidence.getAllEvidences()).thenReturn(evidences);

        List<Evidence> actualEvidences = service.getAllEvidences();

        assertEquals(evidences, actualEvidences);
    }
}
