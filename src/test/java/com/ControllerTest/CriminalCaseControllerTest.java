package com.ControllerTest;

import com.AuthorizeAdditionals.TokensForTests;
import com.DTO.*;
import com.DTO.parsers.CriminalCaseParser;
import com.controller.CriminalCaseController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logic.CriminalCase;
import com.logic.Detective;
import com.services.interfaces.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class CriminalCaseControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ICriminalCaseService crimeCasesService;

    @Mock
    private ICrimeService crimeService;

    @InjectMocks
    private CriminalCaseController controller;

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
    public void addCriminalCase() throws Exception {
        CriminalCaseInputDTO inputJson = new CriminalCaseInputDTO();
        inputJson.setDetective(new IdOnlyDTO());
        OperationResultDTO response = new OperationResultDTO(true);

        when(crimeCasesService.addCriminalCase(inputJson.getDetective().getId(), inputJson.getNumber(), inputJson.getCreateDate(), inputJson.getClosed(), inputJson.getCloseDate() )).thenReturn(true);

        mockMvc.perform(
                post("/criminal_cases/add")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited())
                        .content(objectMapper.writeValueAsString(inputJson)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }
/*
    @Test
    public void noAuthentication() throws Exception {
        CriminalCaseInputDTO inputJson = new CriminalCaseInputDTO();
        inputJson.setDetective(new IdOnlyDTO());

        mockMvc.perform(
                post("/criminal_cases/add")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(inputJson)))
                .andExpect(status().is4xxClientError());
    }
*/
    @Test
    public void updateCriminalCase() throws Exception {
        CriminalCaseInputWithIdDTO inputJson = new CriminalCaseInputWithIdDTO();
        inputJson.setDetective(new IdOnlyDTO());
        OperationResultDTO response = new OperationResultDTO(false);

        when(crimeCasesService.updateCriminalCase(inputJson.getId(), inputJson.getDetective().getId(), inputJson.getNumber(), inputJson.getCreateDate(), inputJson.getClosed(), inputJson.getCloseDate() )).thenReturn(false);

        mockMvc.perform(
                post("/criminal_cases/update")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited())
                        .content(objectMapper.writeValueAsString(inputJson)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void getAllCriminalCases() throws Exception {
        criminalCasesGetRequest("/criminal_cases");
    }

    @Test
    public void getAllCriminalCasesOpen() throws Exception {
        criminalCasesGetRequest("/criminal_cases/open");
    }

    @Test
    public void getAllCriminalCasesSolved() throws Exception {
        criminalCasesGetRequest("/criminal_cases/solved");
    }

    @Test
    public void getAllCriminalCasesUnsolved() throws Exception {
        List<CriminalCase> crCases = getCriminalCases();
        List<CriminalCaseObjectDTO> results = crCases.stream()
                .map(cc -> CriminalCaseParser.parseCriminalCase(cc))
                .collect(Collectors.toList());
        GenericDTO<ListCriminalCasesDTO> response = new GenericDTO<>(false, new ListCriminalCasesDTO(results));

        when(crimeCasesService.getAllCriminalCases()).thenReturn(crCases);

        mockMvc.perform(
                get("/criminal_cases/unsolved")
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(asJsonString(response)));
    }

    private void criminalCasesGetRequest(String path) throws Exception {
        List<CriminalCase> crCases = getCriminalCases();
        List<CriminalCaseObjectDTO> results = crCases.stream()
                .map(cc -> CriminalCaseParser.parseCriminalCase(cc))
                .collect(Collectors.toList());
        GenericDTO<ListCriminalCasesDTO> response = new GenericDTO<>(false, new ListCriminalCasesDTO(results));

        when(crimeCasesService.getAllCriminalCases()).thenReturn(crCases);

        mockMvc.perform(
                get(path)
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    private List<CriminalCase> getCriminalCases() {
        List<CriminalCase> cases = new ArrayList<>();
        CriminalCase crCase = new CriminalCase();
        crCase.setCriminalCaseId(1);
        crCase.setCloseDate(LocalDate.MAX);
        crCase.setCreateDate(LocalDate.now());
        crCase.setClosed(true);
        crCase.setDetectiveId(1);
        Detective det = new Detective();
        det.setManId(1);
        det.setLogin("test");
        det.setHashOfPassword("ferdsfgyujikjhgvbn");
        crCase.setParentDetective(det);
        crCase.setCriminalCaseNumber("API");
        cases.add(crCase);
        return cases;
    }
}
