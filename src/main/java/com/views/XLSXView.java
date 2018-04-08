package com.views;

import com.logic.*;
import com.views.interfaces.IReportView;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.logic.ProjectConstants.JSON_FORMATTER_DATE;
import static com.logic.ProjectConstants.JSON_FORMATTER_DATETIME;
import static com.logic.ProjectConstants.JSON_FORMATTER_TIME;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class XLSXView implements IReportView {
    private XSSFCellStyle styleHeader = null;
    private XSSFCellStyle styleUsual = null;

    private void initStyles(XSSFWorkbook workbook) {
        styleHeader = workbook.createCellStyle();
        styleHeader.setWrapText(true);
        styleHeader.setAlignment(HorizontalAlignment.CENTER);
        styleHeader.setVerticalAlignment(VerticalAlignment.CENTER);
        styleHeader.setBorderBottom(BorderStyle.MEDIUM);
        styleHeader.setBorderTop(BorderStyle.MEDIUM);
        styleHeader.setBorderLeft(BorderStyle.MEDIUM);
        styleHeader.setBorderRight(BorderStyle.MEDIUM);

        styleUsual = workbook.createCellStyle();
        styleUsual.setWrapText(true);
        styleUsual.setAlignment(HorizontalAlignment.LEFT);
        styleUsual.setVerticalAlignment(VerticalAlignment.TOP);
        styleUsual.setBorderBottom(BorderStyle.THIN);
        styleUsual.setBorderTop(BorderStyle.THIN);
        styleUsual.setBorderLeft(BorderStyle.THIN);
        styleUsual.setBorderRight(BorderStyle.THIN);
    }

    private XSSFWorkbook getWorkbook() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        initStyles(workbook);
        return workbook;
    }

    private XSSFSheet getSheet(XSSFWorkbook workbook, String name) {
        XSSFSheet sheet = workbook.createSheet(name);
        sheet.getPrintSetup().setPaperSize(PrintSetup.A4_PAPERSIZE);
        sheet.getPrintSetup().setOrientation(PrintOrientation.LANDSCAPE);
        return sheet;
    }

    private void createCell(XSSFRow row, int colNum, String value, XSSFCellStyle style) {
        XSSFCell cell = row.createCell(colNum);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }

    private void setParamsToFit(XSSFSheet sheet) {
        sheet.setAutobreaks(true);
        PrintSetup ps = sheet.getPrintSetup();
        ps.setFitHeight((short)1);
        ps.setFitWidth((short)1);
        sheet.setFitToPage(false);
    }

    private int addCrimeHeaders(XSSFRow row, int colNum, boolean withCriminalCase) {
        if (withCriminalCase) {
            createCell(row, colNum++, "Номер уголовного дела", styleHeader);
        }
        createCell(row, colNum++, "Тип", styleHeader);
        createCell(row, colNum++, "Дата совершения", styleHeader);
        createCell(row, colNum++, "Время совершения", styleHeader);
        createCell(row, colNum++, "Место совершения", styleHeader);
        createCell(row, colNum++, "Описание", styleHeader);
        return colNum;
    }

    private int addEvidenceHeaders(XSSFRow row, int colNum) {
        createCell(row, colNum++, "Название", styleHeader);
        createCell(row, colNum++, "Описание", styleHeader);
        return colNum;
    }

    private int addEvidenceOfCrimeHeaders(XSSFRow row, int colNum, boolean withParentEvidence, boolean withParentCrime) {
        if (withParentEvidence) {
            colNum = addEvidenceHeaders(row, colNum);
        }
        if (withParentCrime) {
            colNum = addCrimeHeaders(row, colNum, true);
        }
        createCell(row, colNum++, "Тип улики", styleHeader);
        createCell(row, colNum++, "Детальная информация", styleHeader);
        createCell(row, colNum++, "Дата добавления", styleHeader);
        createCell(row, colNum++, "Фотография", styleHeader);
        return colNum;
    }

    private int addCrimeContents(XSSFRow row, int colNum, boolean withCriminalCase, Crime crime) {
        if (withCriminalCase) {
            createCell(row, colNum++, crime.getParentCriminalCase().getCriminalCaseNumber(), styleUsual);
        }
        createCell(row, colNum++, crime.getCrimeType().getName(), styleUsual);
        createCell(row, colNum++, crime.getCrimeDate().format(JSON_FORMATTER_DATE), styleUsual);
        createCell(row, colNum++, crime.getCrimeTime() != null ? crime.getCrimeTime().format(JSON_FORMATTER_TIME) : "не установлено", styleUsual);
        createCell(row, colNum++, !isEmpty(crime.getCrimePlace()) ? crime.getCrimePlace() : "не указано", styleUsual);
        createCell(row, colNum++, !isEmpty(crime.getDescription()) ? crime.getDescription() : "не указано", styleUsual);
        return colNum;
    }

    private int addEvidenceContents(XSSFRow row, int colNum, Evidence evidence) {
        createCell(row, colNum++, evidence.getName(), styleUsual);
        createCell(row, colNum++, evidence.getDescription(), styleUsual);
        return colNum;
    }

    private int addEvidenceOfCrimeContents(XSSFRow row, int colNum, boolean withParentEvidence,
                                            boolean withParentCrime, EvidenceOfCrime evidenceOfCrime) {
        if (withParentEvidence) {
            colNum = addEvidenceContents(row, colNum, evidenceOfCrime.getParentEvidence());
        }
        if (withParentCrime) {
            colNum = addCrimeContents(row, colNum, true, evidenceOfCrime.getParentCrime());
        }
        createCell(row, colNum++, evidenceOfCrime.getEvidenceType().getName(), styleUsual);
        createCell(row, colNum++, !isEmpty(evidenceOfCrime.getDetails()) ?
                evidenceOfCrime.getDetails() : "отсутствует", styleUsual);
        createCell(row, colNum++, evidenceOfCrime.getDateAdded().format(JSON_FORMATTER_DATETIME), styleUsual);
        createCell(row, colNum++, evidenceOfCrime.getPhotoPath() != null ? evidenceOfCrime.getPhotoPath(): "отсутствует", styleUsual);
        return colNum;
    }

    private void addCrimesWithCriminalCaseToSheet(XSSFWorkbook workbook, List<Crime> crimes, boolean withCriminalCase, String title) {
        int rowNum = 0;
        int colNum = 0;
        XSSFSheet sheet = getSheet(workbook, title);
        XSSFRow row = sheet.createRow(rowNum++);
        int resultColumns = addCrimeHeaders(row, colNum, withCriminalCase);
        for(Crime crime : crimes) {
            row = sheet.createRow(rowNum++);
            colNum = 0;
            addCrimeContents(row, colNum, withCriminalCase, crime);
        }
        /*for (int i = 0; i < resultColumns; i++) {
            sheet.autoSizeColumn(i);
        }*/
        setParamsToFit(sheet);
    }

    private void addEvidenceOfCrimesToSheet(XSSFWorkbook workbook, List<EvidenceOfCrime> evidencesOfCrime, boolean withParentEvidence,
                                            boolean withParentCrime, String title) {
        int rowNum = 0;
        int colNum = 0;
        XSSFSheet sheet = getSheet(workbook,title);
        XSSFRow row = sheet.createRow(rowNum++);
        int resultColumns = addEvidenceOfCrimeHeaders(row, colNum, withParentEvidence, withParentCrime);

        for(EvidenceOfCrime evidenceOfCrime : evidencesOfCrime) {
            row = sheet.createRow(rowNum++);
            colNum = 0;
            addEvidenceOfCrimeContents(row, colNum, withParentEvidence, withParentCrime, evidenceOfCrime);
        }
        /*for (int i = 0; i < resultColumns; i++) {
            sheet.autoSizeColumn(i);
        }*/
        setParamsToFit(sheet);
    }


    @Override
    public String generateReportByCrime(Crime crime, List<EvidenceOfCrime> evidencesOfCrime, List<Participant> participants) throws Exception {
        File tempFile = File.createTempFile("report", ".xlsx");
        XSSFWorkbook workbook = getWorkbook();

        addCrimesWithCriminalCaseToSheet(workbook, Arrays.asList(crime), true,"Преступление");
        addEvidenceOfCrimesToSheet(workbook, evidencesOfCrime, true, false,"Прикреплённные улики");

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

    @Override
    public String generateReportByCrimes(List<Crime> crimes, LocalDate startDate, LocalDate endDate) throws Exception {
        return null;
    }

    @Override
    public String generateReportByCriminalCases(List<CriminalCase> criminalCases, String status) throws Exception {
        return null;
    }
}
