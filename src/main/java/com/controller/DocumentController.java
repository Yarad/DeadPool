package com.controller;

import com.logic.Crime;
import com.security.annotations.IsDetective;
import com.services.interfaces.ICrimeService;
import com.views.PDFView;
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
import java.util.List;

@Controller
@RequestMapping(value = "/reports")
public class DocumentController {
    @Autowired
    private PDFView pdfView;

    @Autowired
    private ICrimeService crimeService;

    @IsDetective
    @CrossOrigin
    @RequestMapping(path = "crimes_between_dates/{doc_type}/{date_start}/{date_end}", method = RequestMethod.GET,
            /*produces = MediaType.APPLICATION_PDF_VALUE*/
            produces = {"text/csv", "application/pdf",
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "application/vnd.ms-excel"})
    public ResponseEntity<InputStreamResource> getReportCrimesBetweenDates(
            @PathVariable("doc_type") String documentFormat,
            @PathVariable("date_start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateStart,
            @PathVariable("date_end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEnd) {
        List<Crime> crimes = crimeService.getCrimesBetweenDates(dateStart, dateEnd);
        String path;
        File file = null;

        try {
            path = pdfView.generateReport(crimes);

            file = new File(path);

            InputStream inputstream = new FileInputStream(file);
            InputStreamResource inputStreamResource = new InputStreamResource(inputstream);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentLength(file.length());
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            //headers.setContentDispositionFormData("inline", file.getName());
            headers.add("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
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
