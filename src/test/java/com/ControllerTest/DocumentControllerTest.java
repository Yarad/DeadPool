package com.ControllerTest;

import com.Additionals.LogicAdditionals;
import com.Additionals.TokensForTests;
import com.DTO.*;
import com.DTO.parsers.ManParser;
import com.config.CORSFilter;
import com.controller.DetectiveController;
import com.controller.DocumentController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logic.*;
import com.services.interfaces.*;
import com.views.CSVView;
import com.views.PDFView;
import com.views.XLSXView;
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

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class DocumentControllerTest {
    private MockMvc mockMvc;

    @Mock
    private PDFView pdfView;

    @Mock
    private XLSXView xlsxView;

    @Mock
    private CSVView csvView;

    @Mock
    private IManService manService;

    @Mock
    private IDetectiveService detectiveService;

    @Mock
    private ICrimeService crimeService;

    @Mock
    private IParticipantService participantService;

    @Mock
    private IEvidenceService evidenceService;

    @Mock
    private IEvidenceOfCrimeService evidenceOfCrimeService;

    @Mock
    private ICriminalCaseService criminalCaseService;

    @InjectMocks
    private DocumentController controller;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .addFilters(new CORSFilter())
                .build();
    }

    @Test
    public void getReportCrimesBetweenDates() throws Exception {
        List<Crime> crimes = LogicAdditionals.getCrimesList();
        File file = File.createTempFile("report", ".pdf");

        when(crimeService.getCrimesBetweenDates(LocalDate.of(2012,12,19),
                LocalDate.of(2015,11,22))).thenReturn(crimes);
        when(pdfView.generateReportByCrimes(crimes, LocalDate.of(2012,12,19),
                LocalDate.of(2015,11,22))).thenReturn(file.getAbsolutePath());

        try {
            mockMvc.perform(
                    get("/reports/crimes_between_dates/pdf/2012-12-19/2015-11-22")
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.parseMediaType("application/pdf")))
                    .andExpect(header().longValue("content-length", file.length()));
        } finally {
            if (file != null && file.exists()) {
                file.delete();
            }
        }
    }

    @Test
    public void getReportCrimesBetweenDates_UnsupportedExtension() throws Exception {
        mockMvc.perform(
                get("/reports/crimes_between_dates/txt/2012-12-19/2015-11-22")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void getReportCrimesBetweenDates_ExceptionOnFileGenerating() throws Exception {
        List<Crime> crimes = LogicAdditionals.getCrimesList();

        when(crimeService.getCrimesBetweenDates(LocalDate.of(2012,12,19),
                LocalDate.of(2015,11,22))).thenReturn(crimes);
        when(csvView.generateReportByCrimes(crimes, LocalDate.of(2012,12,19),
                LocalDate.of(2015,11,22))).thenThrow(new Exception());

        mockMvc.perform(
                get("/reports/crimes_between_dates/csv/2012-12-19/2015-11-22")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getReportCriminalCasesWithStatus_Open() throws Exception {
        List<CriminalCase> criminalCases = LogicAdditionals.getCriminalCases();
        File file = File.createTempFile("report", ".xlsx");

        when(criminalCaseService.getAllOpenCriminalCases()).thenReturn(criminalCases);
        when(xlsxView.generateReportByCriminalCases(criminalCases)).thenReturn(file.getAbsolutePath());

        try {
            mockMvc.perform(
                    get("/reports/criminal_cases_with_status/xlsx/open")
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")))
                    .andExpect(header().longValue("content-length", file.length()));
        } finally {
            if (file != null && file.exists()) {
                file.delete();
            }
        }
    }

    @Test
    public void getReportCrime() throws Exception {
        Crime crime = LogicAdditionals.getCrimeWithDates();
        List<EvidenceOfCrime> evidenceOfCrimes = LogicAdditionals.getEvidenceOfCrimeList();
        List<Participant> participants = LogicAdditionals.getParticipantList();
        File file = File.createTempFile("report", ".pdf");

        when(crimeService.getCrimeById(anyLong())).thenReturn(crime);
        when(evidenceOfCrimeService.getEvidencesOfCrimeByCrimeId(anyLong())).thenReturn(evidenceOfCrimes);
        when(participantService.getParticipantsByCrimeId(anyLong())).thenReturn(participants);
        when(pdfView.generateReportByCrime(crime, evidenceOfCrimes, participants)).thenReturn(file.getAbsolutePath());

        try {
            mockMvc.perform(
                    get("/reports/crime/pdf/2")
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.parseMediaType("application/pdf")))
                    .andExpect(header().longValue("content-length", file.length()));
        } finally {
            if (file != null && file.exists()) {
                file.delete();
            }
        }
    }

    @Test
    public void getReportMan() throws Exception {
        Man man = LogicAdditionals.getManWithDates();
        List<Participant> participants = LogicAdditionals.getParticipantList();
        File file = File.createTempFile("report", ".csv");

        when(manService.getFullManInfo(anyLong())).thenReturn(man);
        when(participantService.getParticipantsByManId(anyLong())).thenReturn(participants);
        when(csvView.generateReportByMan(man, participants)).thenReturn(file.getAbsolutePath());

        try {
            mockMvc.perform(
                    get("/reports/man/csv/2")
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.parseMediaType("text/csv")))
                    .andExpect(header().longValue("content-length", file.length()));
        } finally {
            if (file != null && file.exists()) {
                file.delete();
            }
        }
    }

    @Test
    public void getReportCriminalCase() throws Exception {
        CriminalCase criminalCase = LogicAdditionals.getCriminalCaseSolved();
        List<Crime> crimes = LogicAdditionals.getCrimesList();
        File file = File.createTempFile("report", ".csv");

        when(criminalCaseService.getCriminalCaseById(anyLong())).thenReturn(criminalCase);
        when(crimeService.getCrimesByCriminalCase(anyLong())).thenReturn(crimes);
        when(csvView.generateReportByCriminalCase(criminalCase, crimes)).thenReturn(file.getAbsolutePath());

        try {
            mockMvc.perform(
                    get("/reports/criminal_case/csv/2")
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.parseMediaType("text/csv")))
                    .andExpect(header().longValue("content-length", file.length()));
        } finally {
            if (file != null && file.exists()) {
                file.delete();
            }
        }
    }

    @Test
    public void getReportEvidence() throws Exception {
        Evidence evidence = LogicAdditionals.getCustomEvidence();
        List<EvidenceOfCrime> evidenceOfCrimes = LogicAdditionals.getEvidenceOfCrimeList();
        File file = File.createTempFile("report", ".xlsx");

        when(evidenceService.getEvidenceById(anyLong())).thenReturn(evidence);
        when(evidenceOfCrimeService.getEvidencesOfCrimeByEvidenceId(anyLong())).thenReturn(evidenceOfCrimes);
        when(xlsxView.generateReportByEvidence(evidence, evidenceOfCrimes)).thenReturn(file.getAbsolutePath());

        try {
            mockMvc.perform(
                    get("/reports/evidence/xlsx/3")
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")))
                    .andExpect(header().longValue("content-length", file.length()));
        } finally {
            if (file != null && file.exists()) {
                file.delete();
            }
        }
    }

    @Test
    public void getReportDetective() throws Exception {
        Detective detective = LogicAdditionals.getDetectiveWithDates();
        List<CriminalCase> criminalCases = LogicAdditionals.getCriminalCases();
        File file = File.createTempFile("report", ".pdf");

        when(detectiveService.getDetectiveById(anyLong())).thenReturn(detective);
        when(criminalCaseService.getCriminalCasesByDetectiveId(anyLong())).thenReturn(criminalCases);
        when(pdfView.generateReportByDetective(detective, criminalCases)).thenReturn(file.getAbsolutePath());

        try {
            mockMvc.perform(
                    get("/reports/detective/pdf/2")
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            .header("deadpool-token", TokensForTests.getCorrectTokenUnlimited()))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.parseMediaType("application/pdf")))
                    .andExpect(header().longValue("content-length", file.length()));
        } finally {
            if (file != null && file.exists()) {
                file.delete();
            }
        }
    }
}
