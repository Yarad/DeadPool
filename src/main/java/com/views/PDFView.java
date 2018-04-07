package com.views;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.logic.*;
import com.views.interfaces.IReportView;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import static com.logic.ProjectConstants.JSON_FORMATTER_DATE;
import static com.logic.ProjectConstants.JSON_FORMATTER_TIME;
import static com.views.ReportFunctions.getStatusWithData;

@Component
public class PDFView implements IReportView {
    private static final Font standardFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, 0, BaseColor.BLACK);
    private static final Font standardBoldFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK);
    private static final Font header1Font = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLDITALIC, BaseColor.MAGENTA);

    private Paragraph getStandardParagraph() {
        Paragraph p = new Paragraph();
        p.setAlignment(Element.ALIGN_JUSTIFIED);
        p.setFont(standardFont);
        p.setLeading(10);
        p.setSpacingAfter(4F);
        return p;
    }

    private Paragraph getStandardParagraphBold() {
        Paragraph p = getStandardParagraph();
        p.setFont(standardBoldFont);
        return p;
    }

    private Paragraph createStandardParagraph(String boldText, String text) {
        Paragraph p = getStandardParagraphBold();
        p.add(boldText + ": ");
        p.setFont(standardFont);
        p.add(text);
        return p;
    }

    private Paragraph createHeader1Paragraph(String text) {
        Paragraph p = new Paragraph();
        p.setFont(header1Font);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setSpacingAfter(10);
        p.add(text);
        return p;
    }

    @Override
    public String generateReportByCrimes(List<Crime> crimes) throws Exception {
         Document document = new Document();
         File tempFile = File.createTempFile("report", ".pdf");
         PdfWriter.getInstance(document,
                 new FileOutputStream(tempFile));

         document.open();
         Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

         PdfPTable table = new PdfPTable(3);
         table.setWidths(new int[]{10, 10, 120});

         table.addCell("ID");
         table.addCell("Criminal Case ID");
         table.addCell("Description");

         for (Crime crime : crimes){
             table.addCell(String.valueOf(crime.getCrimeId()));
             table.addCell(String.valueOf(crime.getCriminalCaseId()));
             table.addCell(crime.getDescription());
         }

         document.add(table);
         document.close();
         return tempFile.getAbsolutePath();
    }

    @Override
    public String generateReportByCriminalCases(List<CriminalCase> criminalCases) throws Exception {
        return null;
    }

    @Override
    public String generateReportByCrime(Crime crime, List<EvidenceOfCrime> evidencesOfCrime, List<Participant> participants) throws Exception {
        Document document = new Document();
        File tempFile = File.createTempFile("report", ".pdf");
        PdfWriter.getInstance(document, new FileOutputStream(tempFile));
        document.addTitle("Report by crime #" + crime.getCrimeId());
        document.open();

        document.add(createHeader1Paragraph("Report by crime #" + crime.getCrimeId()));

        document.add(createStandardParagraph("Criminal case", crime.getParentCriminalCase().getCriminalCaseNumber()
                + " (" + getStatusWithData(crime.getParentCriminalCase()) + ")"));
        document.add(createStandardParagraph("Type", crime.getCrimeType().toString()));
        document.add(createStandardParagraph("Date", crime.getCrimeDate().format(JSON_FORMATTER_DATE)));
        if (crime.getCrimeTime() != null) {
            document.add(createStandardParagraph("Time", crime.getCrimeTime().format(JSON_FORMATTER_TIME)));
        }

        document.close();
        return tempFile.getAbsolutePath();
    }

    @Override
    public String generateReportByMan(Man man, List<Participant> participants) throws Exception {
        return null;
    }

    @Override
    public String generateReportByCriminalCase(CriminalCase criminalCase, List<Crime> crimes) throws Exception {
        return null;
    }

    @Override
    public String generateReportByEvidence(Evidence evidence, List<EvidenceOfCrime> evidenceOfCrimes) throws Exception {
        return null;
    }

    @Override
    public String generateReportByDetective(Detective detective, List<CriminalCase> criminalCases) throws Exception {
        return null;
    }
}