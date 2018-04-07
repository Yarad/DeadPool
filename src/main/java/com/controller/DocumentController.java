package com.controller;

import com.logic.*;
import com.security.annotations.IsDetective;
import com.services.interfaces.*;
import com.views.CSVView;
import com.views.PDFView;
import com.views.XLSXView;
import com.views.interfaces.IReportView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.logic.ProjectConstants.fileDateTimeFormatter;

@Controller
@RequestMapping(value = "/reports")
public class DocumentController {
    @Autowired
    private PDFView pdfView;

    @Autowired
    private XLSXView xlsxView;

    @Autowired
    private CSVView csvView;

    @Autowired
    private ICrimeService crimeService;

    @Autowired
    private ICriminalCaseService criminalCaseService;

    @Autowired
    private IEvidenceService evidenceService;

    @Autowired
    private IEvidenceOfCrimeService evidenceOfCrimeService;

    @Autowired
    private IDetectiveService detectiveService;

    @Autowired
    private IParticipantService participantService;

    @Autowired
    private IManService manService;

    @IsDetective
    @CrossOrigin
    @RequestMapping(path = "crimes_between_dates/{doc_type}/{date_start}/{date_end}", method = RequestMethod.GET,
            produces = {"text/csv", "application/pdf",
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "application/vnd.ms-excel"})
    public ResponseEntity<InputStreamResource> getReportCrimesBetweenDates(
            @PathVariable("doc_type") String documentFormat,
            @PathVariable("date_start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateStart,
            @PathVariable("date_end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEnd) {
        IReportView view = getViewForFileFormat(documentFormat);
        String contentType = getContentType(documentFormat);
        String path;

        if (view == null || contentType == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<Crime> crimes = crimeService.getCrimesBetweenDates(dateStart, dateEnd);
        try {
            path = view.generateReportByCrimes(crimes);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return sendGeneratedFile(path, "crimes_between_dates_" + dateStart + "_and_" + dateEnd, documentFormat, contentType);
    }

    @IsDetective
    @CrossOrigin
    @RequestMapping(path = "criminal_cases_with_status/{doc_type}/{status}", method = RequestMethod.GET,
            produces = {"text/csv", "application/pdf",
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "application/vnd.ms-excel"})
    public ResponseEntity<InputStreamResource> getReportCriminalCasesWithStatus(
            @PathVariable("doc_type") String documentFormat,
            @PathVariable("status") String status) {
        IReportView view = getViewForFileFormat(documentFormat);
        String contentType = getContentType(documentFormat);
        String path;

        if (view == null || contentType == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<CriminalCase> criminalCases;
        switch(status) {
            case "open":
                criminalCases = criminalCaseService.getAllOpenCriminalCases();
                break;
            case "solved":
                criminalCases = criminalCaseService.getAllSolvedCriminalCases();
                break;
            case "unsolved":
                criminalCases = criminalCaseService.getAllUnsolvedCriminalCases();
                break;
            default:
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            path = view.generateReportByCriminalCases(criminalCases);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return sendGeneratedFile(path, "criminal_cases_with_status_'" + status + "'", documentFormat, contentType);
    }

    @IsDetective
    @CrossOrigin
    @RequestMapping(path = "crime/{doc_type}/{id}", method = RequestMethod.GET,
            produces = {"text/csv", "application/pdf",
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "application/vnd.ms-excel"})
    public ResponseEntity<InputStreamResource> getReportCrime(
            @PathVariable("doc_type") String documentFormat,
            @PathVariable("id") long crimeId) {
        IReportView view = getViewForFileFormat(documentFormat);
        String contentType = getContentType(documentFormat);
        String path;

        if (view == null || contentType == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Crime crime = crimeService.getCrimeById(crimeId);
        List<EvidenceOfCrime> evidenceOfCrimes = evidenceOfCrimeService.getEvidencesOfCrimeByCrimeId(crimeId);
        List<Participant> participants = participantService.getParticipantsByCrimeId(crimeId);
        try {
            path = view.generateReportByCrime(crime, evidenceOfCrimes, participants);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return sendGeneratedFile(path, "crime_id_'" + crimeId + "'", documentFormat, contentType);
    }

    @IsDetective
    @CrossOrigin
    @RequestMapping(path = "man/{doc_type}/{id}", method = RequestMethod.GET,
            produces = {"text/csv", "application/pdf",
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "application/vnd.ms-excel"})
    public ResponseEntity<InputStreamResource> getReportMan(
            @PathVariable("doc_type") String documentFormat,
            @PathVariable("id") long manId) {
        IReportView view = getViewForFileFormat(documentFormat);
        String contentType = getContentType(documentFormat);
        String path;

        if (view == null || contentType == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Man man = manService.getFullManInfo(manId);
        List<Participant> participants = participantService.getParticipantsByManId(manId);
        try {
            path = view.generateReportByMan(man, participants);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return sendGeneratedFile(path, "man_id_'" + manId + "'", documentFormat, contentType);
    }

    @IsDetective
    @CrossOrigin
    @RequestMapping(path = "criminal_case/{doc_type}/{id}", method = RequestMethod.GET,
            produces = {"text/csv", "application/pdf",
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "application/vnd.ms-excel"})
    public ResponseEntity<InputStreamResource> getReportCriminalCase(
            @PathVariable("doc_type") String documentFormat,
            @PathVariable("id") long criminalCaseId) {
        IReportView view = getViewForFileFormat(documentFormat);
        String contentType = getContentType(documentFormat);
        String path;

        if (view == null || contentType == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        CriminalCase criminalCase = criminalCaseService.getCriminalCaseById(criminalCaseId);
        List<Crime> crimes = crimeService.getCrimesByCriminalCase(criminalCaseId);
        try {
            path = view.generateReportByCriminalCase(criminalCase, crimes);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return sendGeneratedFile(path, "criminal_case_id_'" + criminalCaseId + "'", documentFormat, contentType);
    }

    @IsDetective
    @CrossOrigin
    @RequestMapping(path = "evidence/{doc_type}/{id}", method = RequestMethod.GET,
            produces = {"text/csv", "application/pdf",
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "application/vnd.ms-excel"})
    public ResponseEntity<InputStreamResource> getReportEvidence(
            @PathVariable("doc_type") String documentFormat,
            @PathVariable("id") long evidenceId) {
        IReportView view = getViewForFileFormat(documentFormat);
        String contentType = getContentType(documentFormat);
        String path;

        if (view == null || contentType == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Evidence evidence = evidenceService.getEvidenceById(evidenceId);
        List<EvidenceOfCrime> evidenceOfCrimes = evidenceOfCrimeService.getEvidencesOfCrimeByEvidenceId(evidenceId);
        try {
            path = view.generateReportByEvidence(evidence, evidenceOfCrimes);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return sendGeneratedFile(path, "evidence_id_'" + evidenceId + "'", documentFormat, contentType);
    }

    @IsDetective
    @CrossOrigin
    @RequestMapping(path = "detective/{doc_type}/{id}", method = RequestMethod.GET,
            produces = {"text/csv", "application/pdf",
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "application/vnd.ms-excel"})
    public ResponseEntity<InputStreamResource> getReportDetective(
            @PathVariable("doc_type") String documentFormat,
            @PathVariable("id") long detectiveId) {
        IReportView view = getViewForFileFormat(documentFormat);
        String contentType = getContentType(documentFormat);
        String path;

        if (view == null || contentType == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Detective detective = detectiveService.getDetectiveById(detectiveId);
        List<CriminalCase> criminalCases = criminalCaseService.getCriminalCasesByDetectiveId(detectiveId);
        try {
            path = view.generateReportByDetective(detective, criminalCases);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return sendGeneratedFile(path, "detective_id_'" + detectiveId + "'", documentFormat, contentType);
    }

    private ResponseEntity<InputStreamResource> sendGeneratedFile(String path, String reportName, String docType, String contentType) {
        File file = null;
        try {
            file = new File(path);

            InputStream inputstream = new FileInputStream(file);
            InputStreamResource inputStreamResource = new InputStreamResource(inputstream);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentLength(file.length());
            headers.setContentType(MediaType.parseMediaType(contentType));
            headers.add("Content-Disposition", "attachment; filename=\"report_" +
                    reportName + "_(generated_" + LocalDateTime.now().format(fileDateTimeFormatter) + ")." + docType +  "\"");
            headers.setCacheControl("no-cache, no-store, must-revalidate");
            headers.setPragma("no-cache");
            headers.setExpires(0);
            return new ResponseEntity<>(inputStreamResource, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } finally {
            if (file != null && file.exists()) {
                file.delete();
            }
        }
    }

    private String getContentType(String extension) {
        switch(extension) {
            case "pdf":
                return "application/pdf";
            case "xlsx":
                return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            case "csv":
                return "text/csv";
            default:
                return null;
        }
    }

    private IReportView getViewForFileFormat(String extension) {
        switch(extension) {
            case "pdf":
                return pdfView;
            case "xlsx":
                return xlsxView;
            case "csv":
                return csvView;
            default:
                return null;
        }
    }
}
