package com.ControllerTest;

import com.Additionals.LogicAdditionals;
import com.Additionals.TokensForTests;
import com.DTO.*;
import com.DTO.parsers.CriminalCaseParser;
import com.config.CORSFilter;
import com.controller.CriminalCaseController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logic.Crime;
import com.logic.CriminalCase;
import com.services.interfaces.ICrimeService;
import com.services.interfaces.ICriminalCaseService;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
                .addFilters(new CORSFilter())
                .build();
    }

    @BeforeClass
    public static void getDAO() {
        objectMapper = LogicAdditionals.objectMapper();
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
        List<CriminalCase> crCases = LogicAdditionals.getCriminalCases();
        List<CriminalCaseObjectDTO> results = crCases.stream()
                .map(cc -> CriminalCaseParser.parseCriminalCase(cc))
                .collect(Collectors.toList());
        GenericDTO<ListCriminalCasesDTO> response = new GenericDTO<>(false, new ListCriminalCasesDTO(results));

        when(crimeCasesService.getAllCriminalCases()).thenReturn(crCases);

        mockMvc.perform(
                get("/criminal_cases")
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void getAllCriminalCasesOpen() throws Exception {
        List<CriminalCase> crCases = LogicAdditionals.getCriminalCases();
        List<CriminalCaseObjectDTO> results = crCases.stream()
                .map(cc -> CriminalCaseParser.parseCriminalCase(cc))
                .collect(Collectors.toList());
        GenericDTO<ListCriminalCasesDTO> response = new GenericDTO<>(false, new ListCriminalCasesDTO(results));

        when(crimeCasesService.getAllOpenCriminalCases()).thenReturn(crCases);

        mockMvc.perform(
                get("/criminal_cases/open")
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void getAllCriminalCasesSolved() throws Exception {
        List<CriminalCase> crCases = LogicAdditionals.getCriminalCases();
        List<CriminalCaseObjectDTO> results = crCases.stream()
                .map(cc -> CriminalCaseParser.parseCriminalCase(cc))
                .collect(Collectors.toList());
        GenericDTO<ListCriminalCasesDTO> response = new GenericDTO<>(false, new ListCriminalCasesDTO(results));

        when(crimeCasesService.getAllSolvedCriminalCases()).thenReturn(crCases);

        mockMvc.perform(
                get("/criminal_cases/solved")
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void getAllCriminalCasesUnsolved() throws Exception {
        List<CriminalCase> crCases = LogicAdditionals.getCriminalCases();
        List<CriminalCaseObjectDTO> results = crCases.stream()
                .map(cc -> CriminalCaseParser.parseCriminalCase(cc))
                .collect(Collectors.toList());
        GenericDTO<ListCriminalCasesDTO> response = new GenericDTO<>(false, new ListCriminalCasesDTO(results));

        when(crimeCasesService.getAllUnsolvedCriminalCases()).thenReturn(crCases);

        mockMvc.perform(
                get("/criminal_cases/unsolved")
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void getCriminalCaseByIdCorrect() throws Exception {
        CriminalCase crCase = LogicAdditionals.getCustomCriminalCase();
        List<Crime> crimes = LogicAdditionals.getCrimesList();
        GenericDTO<CriminalCaseExtendedDTO> response = new GenericDTO<>(false, CriminalCaseParser.parseExtendedCriminalCase(crCase, crimes));

        when(crimeCasesService.getCriminalCaseById(anyLong())).thenReturn(crCase);
        when(crimeService.getCrimesByCriminalCase(anyLong())).thenReturn(crimes);

        mockMvc.perform(
                get("/criminal_cases/{id}", 1)
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void getCriminalCaseByIdNotCorrect() throws Exception {
        GenericDTO<CriminalCaseExtendedDTO> response = new GenericDTO<>(true, null);

        when(crimeCasesService.getCriminalCaseById(anyLong())).thenReturn(null);

        mockMvc.perform(
                get("/criminal_cases/{id}", 1)
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }
}
