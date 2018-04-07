package com.controller;

import com.logic.Crime;
import com.security.annotations.IsDetective;
import com.services.interfaces.ICrimeService;
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

    @IsDetective
    @CrossOrigin
    @RequestMapping(path = "crimes_between_dates/{doc_type}/{date_start}/{date_end}", method = RequestMethod.GET,
            produces = {"text/csv", "application/pdf",
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "application/vnd.ms-excel"})
    public ResponseEntity<InputStreamResource> getReportCrimesBetweenDates(
            @PathVariable("doc_type") String documentFormat,
            @PathVariable("date_start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateStart,
            @PathVariable("date_end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEnd) {
        IReportView view;
        String contentType;
        String path;

        switch(documentFormat) {
            case "pdf":
                view = pdfView;
                contentType = "application/pdf";
                break;
            case "xlsx":
                view = xlsxView;
                contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
                break;
            case "csv":
                view = csvView;
                contentType = "text/csv";
                break;
            default:
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            List<Crime> crimes = crimeService.getCrimesBetweenDates(dateStart, dateEnd);
            path = view.generateReport(crimes);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return sendGeneratedFile(path, "crimes_between_dates_" + dateStart + "_and_" + dateEnd, documentFormat, contentType);
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
}
