package com.ControllerTest;

import com.Additionals.LogicAdditionals;
import com.Additionals.TokensForTests;
import com.DTO.*;
import com.DTO.parsers.CrimeParser;
import com.config.CORSFilter;
import com.config.MVCConfig;
import com.controller.CrimeController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logic.Crime;
import com.logic.EvidenceOfCrime;
import com.logic.Participant;
import com.services.interfaces.ICrimeService;
import com.services.interfaces.IEvidenceOfCrimeService;
import com.services.interfaces.IParticipantService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class CrimeControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ICrimeService crimeService;

    @Mock
    private IParticipantService participantService;

    @Mock
    private IEvidenceOfCrimeService evidenceOfCrimeService;

    @InjectMocks
    private CrimeController controller;

    private static ObjectMapper objectMapper;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .addFilters(new CORSFilter())
                .build();
    }

    @BeforeClass
    public static void getDAO() {
        objectMapper = LogicAdditionals.objectMapper();
    }

    @Test
    public void addCrime() throws Exception {
        CrimeInputDTO inputJson = new CrimeInputDTO();
        inputJson.setCriminalCase(new IdOnlyDTO());
        OperationResultDTO response = new OperationResultDTO(true);

        when(crimeService.addCrime(inputJson.getCriminalCase().getId(), inputJson.getType(), inputJson.getDescription(), inputJson.getDate(), inputJson.getTime(), inputJson.getPlace())).thenReturn(true);

        mockMvc.perform(
                post("/crimes/add")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited())
                        .content(objectMapper.writeValueAsString(inputJson)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void updateCrime() throws Exception {
        CrimeInputWithIdDTO inputJson = new CrimeInputWithIdDTO();
        inputJson.setCriminalCase(new IdOnlyDTO());
        OperationResultDTO response = new OperationResultDTO(true);

        when(crimeService.updateCrime(inputJson.getId(), inputJson.getCriminalCase().getId(), inputJson.getType(), inputJson.getDescription(), inputJson.getDate(), inputJson.getTime(), inputJson.getPlace())).thenReturn(true);

        mockMvc.perform(
                post("/crimes/update")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited())
                        .content(objectMapper.writeValueAsString(inputJson)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void getAllCrimes() throws Exception {
        List<Crime> crimes = LogicAdditionals.getCrimesList();
        List<CrimeObjectDTO> results = crimes.stream()
                .map(curCrime -> CrimeParser.parseCrime(curCrime))
                .collect(Collectors.toList());
        GenericDTO<ListCrimesDTO> response = new GenericDTO<>(false, new ListCrimesDTO(results));

        when(crimeService.getAllCrimes()).thenReturn(crimes);

        mockMvc.perform(
                get("/crimes")
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void getCrimeByIdCorrect() throws Exception {
        Crime crime = LogicAdditionals.getCustomCrime();
        List<EvidenceOfCrime> evidencesOfCrime = LogicAdditionals.getEvidenceOfCrimeList();
        List<Participant> participants = LogicAdditionals.getParticipantList();
        GenericDTO<CrimeExtendedDTO> response = new GenericDTO<>(false, CrimeParser.parseCrimeFullInformation(crime, participants, evidencesOfCrime));

        when(crimeService.getCrimeById(anyLong())).thenReturn(crime);
        when(participantService.getParticipantsByCrimeId(anyLong())).thenReturn(participants);
        when(evidenceOfCrimeService.getEvidencesOfCrimeByCrimeId(anyLong())).thenReturn(evidencesOfCrime);

        mockMvc.perform(
                get("/crimes/{id}", 1)
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void getCrimeByIdNotCorrect() throws Exception {
        GenericDTO<CrimeExtendedDTO> response = new GenericDTO<>(true, null);

        when(crimeService.getCrimeById(anyLong())).thenReturn(null);

        mockMvc.perform(
                get("/crimes/{id}", 1)
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void getParticipantStatuses() throws Exception {
        ListEnumDTO list = CrimeParser.getEvidenceTypes();

        mockMvc.perform(
                get("/crimes/types_list")
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(list)));
    }
}
