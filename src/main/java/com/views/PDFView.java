package com.views;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.logic.Crime;
import com.views.interfaces.IReportView;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Component
public class PDFView implements IReportView {
    public String generateReport(List<Crime> crimes) throws Exception {
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
}