package com.views;

import com.logic.*;
import com.views.interfaces.IReportView;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

@Component
public class XLSXView implements IReportView {
    @Override
    public String generateReportByCrimes(List<Crime> crimes, LocalDate startDate, LocalDate endDate) throws Exception {
        return null;
    }

    @Override
    public String generateReportByCriminalCases(List<CriminalCase> criminalCases, String status) throws Exception {
        return null;
    }

    @Override
    public String generateReportByCrime(Crime crime, List<EvidenceOfCrime> evidencesOfCrime, List<Participant> participants) throws Exception {
        File tempFile = File.createTempFile("report", ".xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Datatypes in Java");
        Object[][] datatypes = {
                {"Datatype", "Type", "Size(in bytes)"},
                {"int", "Primitive", 2},
                {"float", "Primitive", 4},
                {"double", "Primitive", 8},
                {"char", "Primitive", 1},
                {"String", "Non-Primitive", "No fixed size"}
        };

        int rowNum = 0;
        System.out.println("Creating excel");

        for (Object[] datatype : datatypes) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object field : datatype) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }

        FileOutputStream fileOut = new FileOutputStream(tempFile);
        workbook.write(fileOut);
        fileOut.flush();
        fileOut.close();

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
