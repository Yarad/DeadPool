package com.ControllerTest;

import com.Additionals.LogicAdditionals;
import com.Additionals.TokensForTests;
import com.DTO.*;
import com.DTO.parsers.ManParser;
import com.config.CORSFilter;
import com.controller.ManController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logic.Man;
import com.logic.Participant;
import com.services.interfaces.IManService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ManControllerTest {
    private MockMvc mockMvc;

    @Mock
    private IManService manService;

    @Mock
    private IParticipantService participantService;

    @InjectMocks
    private ManController controller;

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
    public void addMan() throws Exception {
        ManInfoWithoutIdDTO inputJson = new ManInfoWithoutIdDTO();
        OperationResultDTO response = new OperationResultDTO(true);

        when(manService.addMan(inputJson.getName(), inputJson.getSurname(), inputJson.getBirthday(), inputJson.getHomeAddress(), inputJson.getPhotoPath() )).thenReturn(true);

        mockMvc.perform(
                post("/man/add")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited())
                        .content(objectMapper.writeValueAsString(inputJson)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void updateMan() throws Exception {
        ManInfoDTO inputJson = new ManInfoDTO();
        OperationResultDTO response = new OperationResultDTO(true);

        when(manService.updateMan(inputJson.getId(), inputJson.getName(), inputJson.getSurname(), inputJson.getBirthday(), inputJson.getHomeAddress(), inputJson.getPhotoPath() )).thenReturn(true);

        mockMvc.perform(
                post("/man/update")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited())
                        .content(objectMapper.writeValueAsString(inputJson)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void getManFullInfoCorrect() throws Exception {
        Man man  = LogicAdditionals.getCustomMan();
        List<Participant> participants = LogicAdditionals.getParticipantList();
        GenericDTO<ManExtendedDTO> response = new GenericDTO<>(false, ManParser.parseManExtended(man, participants));

        when(manService.getFullManInfo(anyLong())).thenReturn(man);
        when(participantService.getParticipantsByManId(anyLong())).thenReturn(participants);

        mockMvc.perform(
                get("/man/{id}", 1)
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void getManFullInfoNotCorrect() throws Exception {
        GenericDTO<ManExtendedDTO> response = new GenericDTO<>(true, null);

        when(manService.getFullManInfo(anyLong())).thenReturn(null);

        mockMvc.perform(
                get("/man/{id}", 1)
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void getAllManWithCrimeAmount() throws Exception {
        Map<Man,Long> men = LogicAdditionals.getMapOfManAndTheirCrime();
        List<ManForListWithCrimesAmountDTO> results = new ArrayList<>();
        men.forEach((key,value) -> {
            results.add(ManParser.parseManForList(key, value));
        });
        GenericDTO<ListMenDTO> response = new GenericDTO<>(false, new ListMenDTO(results));

        when(manService.getAllManWithCrimeAmount()).thenReturn(men);

        mockMvc.perform(
                get("/man")
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }
}
