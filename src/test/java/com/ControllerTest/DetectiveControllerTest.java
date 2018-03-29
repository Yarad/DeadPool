package com.ControllerTest;

import com.Additionals.LogicAdditionals;
import com.Additionals.TokensForTests;
import com.DTO.*;
import com.DTO.parsers.ManParser;
import com.controller.DetectiveController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logic.Man;
import com.services.interfaces.IDetectiveService;
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

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class DetectiveControllerTest {
    private MockMvc mockMvc;

    @Mock
    private IDetectiveService detectiveService;

    @InjectMocks
    private DetectiveController controller;

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
    public void addDetective() throws Exception {
        DetectiveWithoutManIdDTO inputJson = new DetectiveWithoutManIdDTO();
        inputJson.setMan(new ManInfoWithoutIdDTO());
        OperationResultDTO response = new OperationResultDTO(true);

        when(detectiveService.addDetective(inputJson.getMan().getName(), inputJson.getMan().getSurname(), inputJson.getMan().getBirthday(), inputJson.getMan().getHomeAddress(), inputJson.getMan().getPhotoPath(), inputJson.getLogin(), inputJson.getPassword(), inputJson.getEmail() )).thenReturn(true);

        mockMvc.perform(
                post("/detectives/add")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited())
                        .content(objectMapper.writeValueAsString(inputJson)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void updateDetective() throws Exception {
        DetectiveDTO inputJson = new DetectiveDTO();
        inputJson.setMan(new ManInfoDTO());
        OperationResultDTO response = new OperationResultDTO(true);

        when(detectiveService.updateDetective(inputJson.getMan().getId(), inputJson.getMan().getName(), inputJson.getMan().getSurname(), inputJson.getMan().getBirthday(), inputJson.getMan().getHomeAddress(), inputJson.getMan().getPhotoPath(), inputJson.getLogin(), inputJson.getPassword(), inputJson.getEmail() )).thenReturn(true);

        mockMvc.perform(
                post("/detectives/update")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited())
                        .content(objectMapper.writeValueAsString(inputJson)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void getAllDetectives() throws Exception {
        List<Man> men = LogicAdditionals.getManList();
        List<ManShortedDTO> results = men.stream()
                .map(man -> ManParser.parseManShorted(man))
                .collect(Collectors.toList());
        GenericDTO<ListMenShortedDTO> response = new GenericDTO<ListMenShortedDTO>(false, new ListMenShortedDTO(results));

        when(detectiveService.getAllDetectives()).thenReturn(men);

        mockMvc.perform(
                get("/detectives")
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }
}
