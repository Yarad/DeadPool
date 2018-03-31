package com.ControllerTest;

import com.Additionals.LogicAdditionals;
import com.Additionals.TokensForTests;
import com.DTO.*;
import com.DTO.parsers.EvidenceOfCrimeParser;
import com.DTO.parsers.EvidenceParser;
import com.controller.EvidenceOfCrimeController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logic.Evidence;
import com.logic.EvidenceOfCrime;
import com.services.interfaces.IEvidenceOfCrimeService;
import com.services.interfaces.IEvidenceService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class EvidenceOfCrimeControllerTest {
    private MockMvc mockMvc;

    @Mock
    private IEvidenceOfCrimeService evidenceOfCrimeService;

    @Mock
    private IEvidenceService evidenceService;

    @InjectMocks
    private EvidenceOfCrimeController controller;

    private static ObjectMapper objectMapper;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @BeforeClass
    public static void getDAO() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void addEvidenceSingle() throws Exception {
        EvidenceInputDTO inputJson = new EvidenceInputDTO();
        OperationResultDTO response = new OperationResultDTO(true);

        when(evidenceService.addEvidence(inputJson.getName(), inputJson.getDescription())).thenReturn(true);

        mockMvc.perform(
                post("/evidences/add_single")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited())
                        .content(objectMapper.writeValueAsString(inputJson)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void updateEvidenceSingle() throws Exception {
        EvidenceInputWithIdDTO inputJson = new EvidenceInputWithIdDTO();
        OperationResultDTO response = new OperationResultDTO(true);

        when(evidenceService.updateEvidence(inputJson.getId(), inputJson.getName(), inputJson.getDescription())).thenReturn(true);

        mockMvc.perform(
                post("/evidences/update_single")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited())
                        .content(objectMapper.writeValueAsString(inputJson)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void addEvidenceForCrime() throws Exception {
        EvidenceOfCrimeInputDTO inputJson = new EvidenceOfCrimeInputDTO();
        inputJson.setCrime(new IdOnlyDTO());
        inputJson.setEvidence(new IdOnlyDTO());
        OperationResultDTO response = new OperationResultDTO(true);

        when(evidenceOfCrimeService.addEvidenceOfCrime(inputJson.getEvidence().getId(), inputJson.getCrime().getId(), inputJson.getType(), inputJson.getDateAdded(), inputJson.getTimeAdded(), inputJson.getDetails(), inputJson.getPhotoPath())).thenReturn(true);

        mockMvc.perform(
                post("/evidences/add_for_crime")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited())
                        .content(objectMapper.writeValueAsString(inputJson)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void updateEvidenceForCrime() throws Exception {
        EvidenceOfCrimeInputDTO inputJson = new EvidenceOfCrimeInputDTO();
        inputJson.setCrime(new IdOnlyDTO());
        inputJson.setEvidence(new IdOnlyDTO());
        OperationResultDTO response = new OperationResultDTO(true);

        when(evidenceOfCrimeService.updateEvidenceOfCrime(inputJson.getEvidence().getId(), inputJson.getCrime().getId(), inputJson.getType(), inputJson.getDateAdded(), inputJson.getTimeAdded(), inputJson.getDetails(), inputJson.getPhotoPath())).thenReturn(true);

        mockMvc.perform(
                post("/evidences/update_for_crime")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited())
                        .content(objectMapper.writeValueAsString(inputJson)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void getAllEvidenceOfCrime() throws Exception {
        List<EvidenceOfCrime> inputEvidencesOfCrime = LogicAdditionals.getEvidenceOfCrimeList();
        List<EvidenceOfCrimeShortedWithCrimeDTO> results = inputEvidencesOfCrime.stream()
                .map(curEvidence -> EvidenceOfCrimeParser.parseEvidenceOfCrimeShortedWithCrime(curEvidence))
                .collect(Collectors.toList());
        GenericDTO<ListEvidenceOfCrimeShortedWithCrimeList> response = new GenericDTO<>(false, new ListEvidenceOfCrimeShortedWithCrimeList(results));

        when(evidenceOfCrimeService.getAllEvidencesOfCrime()).thenReturn(inputEvidencesOfCrime);

        mockMvc.perform(
                get("/evidences")
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void getEvidenceOfCrimeByEvidenceAndCrimeCorrect() throws Exception {
        EvidenceOfCrime evidenceOfCrime = LogicAdditionals.getCustomEvidenceOfCrime();
        GenericDTO<EvidenceOfCrimeExtendedDTO> response = new GenericDTO<>(false, EvidenceOfCrimeParser.parseEvidenceOfCrimeExtended(evidenceOfCrime));

        when(evidenceOfCrimeService.getEvidenceOfCrimeByEvidenceAndCrime(anyLong(), anyLong())).thenReturn(evidenceOfCrime);

        mockMvc.perform(
                get("/evidences/{evidence_id}/{crime_id}", 1, 1)
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void getEvidenceOfCrimeByEvidenceAndCrimeNotCorrect() throws Exception {
        GenericDTO<EvidenceOfCrimeExtendedDTO> response = new GenericDTO<>(true, null);

        when(evidenceOfCrimeService.getEvidenceOfCrimeByEvidenceAndCrime(anyLong(), anyLong())).thenReturn(null);

        mockMvc.perform(
                get("/evidences/{evidence_id}/{crime_id}", 1, 1)
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void getEvidenceByIdCorrect() throws Exception {
        Evidence evidence = LogicAdditionals.getCustomEvidence();
        List<EvidenceOfCrime> evidencesOfCrime = LogicAdditionals.getEvidenceOfCrimeList();
        GenericDTO<EvidenceExtendedDTO> response = new GenericDTO<>(false, EvidenceParser.parseEvidenceExtended(evidence, evidencesOfCrime));

        when(evidenceService.getEvidenceById(anyLong())).thenReturn(evidence);
        when(evidenceOfCrimeService.getEvidencesOfCrimeByEvidenceId(anyLong())).thenReturn(evidencesOfCrime);

        mockMvc.perform(
                get("/evidences/{evidence_id}", 1)
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void getEvidenceByIdNotCorrect() throws Exception {
        GenericDTO<EvidenceExtendedDTO> response = new GenericDTO<>(true, null);

        when(evidenceService.getEvidenceById(anyLong())).thenReturn(null);

        mockMvc.perform(
                get("/evidences/{evidence_id}", 1)
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void getParticipantStatuses() throws Exception {
        ListEnumDTO list = EvidenceOfCrimeParser.getEvidenceTypes();

        mockMvc.perform(
                get("/evidences/types_list")
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(list)));
    }
}
