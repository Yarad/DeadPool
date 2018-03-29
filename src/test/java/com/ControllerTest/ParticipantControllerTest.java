package com.ControllerTest;

import com.Additionals.LogicAdditionals;
import com.Additionals.TokensForTests;
import com.DTO.*;
import com.DTO.parsers.ParticipantParser;
import com.controller.ParticipantController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logic.Participant;
import com.services.interfaces.IParticipantService;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ParticipantControllerTest {
    private MockMvc mockMvc;

    @Mock
    private IParticipantService participantService;

    @InjectMocks
    private ParticipantController controller;

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
    public void addParticipant() throws Exception {
        ParticipantInputDTO inputJson = new ParticipantInputDTO();
        inputJson.setMan(new IdOnlyDTO());
        inputJson.setCrime(new IdOnlyDTO());
        OperationResultDTO response = new OperationResultDTO(true);

        when(participantService.addParticipant(inputJson.getMan().getId(), inputJson.getCrime().getId(), inputJson.getStatus(), LocalDateTime.of(inputJson.getDateAdded(), inputJson.getTimeAdded()), inputJson.getAlibi(), inputJson.getWitnessReport())).thenReturn(true);

        mockMvc.perform(
                post("/participants/add")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited())
                        .content(objectMapper.writeValueAsString(inputJson)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void updateParticipant() throws Exception {
        ParticipantInputDTO inputJson = new ParticipantInputDTO();
        inputJson.setMan(new IdOnlyDTO());
        inputJson.setCrime(new IdOnlyDTO());
        OperationResultDTO response = new OperationResultDTO(true);

        when(participantService.updateParticipant(inputJson.getMan().getId(), inputJson.getCrime().getId(), inputJson.getStatus(), LocalDateTime.of(inputJson.getDateAdded(), inputJson.getTimeAdded()), inputJson.getAlibi(), inputJson.getWitnessReport())).thenReturn(true);

        mockMvc.perform(
                post("/participants/update")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited())
                        .content(objectMapper.writeValueAsString(inputJson)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void getParticipantByManAndCrimeCorrect() throws Exception {
        Participant participant = LogicAdditionals.getCustomParticipant();
        GenericDTO<ParticipantFullInfoDTO> response = new GenericDTO<>(false, ParticipantParser.parseParticipantFullInfo(participant));

        when(participantService.getParticipantByCrimeAndMan(anyLong(), anyLong())).thenReturn(participant);

        mockMvc.perform(
                get("/participants/{man_id}/{crime_id}", 1, 1)
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void getParticipantByManAndCrimeNotCorrect() throws Exception {
        GenericDTO<ParticipantFullInfoDTO> response = new GenericDTO<>(true, null);

        when(participantService.getParticipantByCrimeAndMan(anyLong(), anyLong())).thenReturn(null);

        mockMvc.perform(
                get("/participants/{man_id}/{crime_id}", 1, 1)
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void getParticipantStatuses() throws Exception {
        ListEnumDTO list = ParticipantParser.getParticipantStatuses();

        mockMvc.perform(
                get("/participants/status_list")
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(list)));
    }
}
