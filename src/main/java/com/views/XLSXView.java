package com.views;

import com.DTO.TableBorder;
import com.logic.*;
import com.views.interfaces.IReportView;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Component;
import java.awt.Color;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.logic.ProjectConstants.*;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class XLSXView implements IReportView {
    private Font fontUsual = null;
    private Font fontBold = null;
    private Font fontLink = null;
    private Font fontBoldColored = null;
    private XSSFCellStyle styleHeader = null;
    private XSSFCellStyle styleUsual = null;
    private XSSFCellStyle styleLink = null;
    private XSSFCellStyle styleArea = null;
    private XSSFCreationHelper createHelper = null;

    private void initStyles(XSSFWorkbook workbook) {
        createHelper = workbook.getCreationHelper();

        fontUsual = workbook.createFont();
        fontUsual.setFontName("Times New Roman");

        fontLink = workbook.createFont();
        fontLink.setFontName("Times New Roman");
        fontLink.setUnderline(Font.U_SINGLE);
        fontLink.setColor(IndexedColors.BLUE.getIndex());

        fontBold = workbook.createFont();
        fontBold.setFontName("Times New Roman");
        fontBold.setColor(IndexedColors.ORANGE.getIndex());
        fontBold.setBold(true);

        fontBoldColored = workbook.createFont();
        fontBoldColored.setFontName("Times New Roman");
        fontBoldColored.setBold(true);
        fontBoldColored.setColor(IndexedColors.MAROON.getIndex());

        styleHeader = workbook.createCellStyle();
        styleHeader.setWrapText(true);
        styleHeader.setAlignment(HorizontalAlignment.CENTER);
        styleHeader.setVerticalAlignment(VerticalAlignment.CENTER);
        styleHeader.setBorderBottom(BorderStyle.MEDIUM);
        styleHeader.setBorderTop(BorderStyle.MEDIUM);
        styleHeader.setBorderLeft(BorderStyle.MEDIUM);
        styleHeader.setBorderRight(BorderStyle.MEDIUM);
        styleHeader.setFont(fontBold);

        styleArea = workbook.createCellStyle();
        styleArea.setWrapText(true);
        styleArea.setAlignment(HorizontalAlignment.CENTER);
        styleArea.setVerticalAlignment(VerticalAlignment.CENTER);
        styleArea.setBorderBottom(BorderStyle.MEDIUM);
        styleArea.setBorderTop(BorderStyle.MEDIUM);
        styleArea.setBorderLeft(BorderStyle.MEDIUM);
        styleArea.setBorderRight(BorderStyle.MEDIUM);
        styleArea.setFont(fontBoldColored);

        styleUsual = workbook.createCellStyle();
        styleUsual.setWrapText(true);
        styleUsual.setAlignment(HorizontalAlignment.LEFT);
        styleUsual.setVerticalAlignment(VerticalAlignment.TOP);
        styleUsual.setBorderBottom(BorderStyle.THIN);
        styleUsual.setBorderTop(BorderStyle.THIN);
        styleUsual.setBorderLeft(BorderStyle.THIN);
        styleUsual.setBorderRight(BorderStyle.THIN);
        styleUsual.setFont(fontUsual);

        styleLink = workbook.createCellStyle();
        styleLink.setWrapText(true);
        styleLink.setAlignment(HorizontalAlignment.LEFT);
        styleLink.setVerticalAlignment(VerticalAlignment.TOP);
        styleLink.setBorderBottom(BorderStyle.THIN);
        styleLink.setBorderTop(BorderStyle.THIN);
        styleLink.setBorderLeft(BorderStyle.THIN);
        styleLink.setBorderRight(BorderStyle.THIN);
        styleLink.setFont(fontLink);
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

    private void createCellHyperlinked(XSSFRow row, int colNum, String value, String linkURL) {
        XSSFCell cell = row.createCell(colNum);
        cell.setCellValue(value);
        Hyperlink link = createHelper.createHyperlink(HyperlinkType.URL);
        link.setAddress(linkURL);
        cell.setHyperlink(link);
        cell.setCellStyle(styleLink);
    }

    private void setParamsToFit(XSSFWorkbook workbook, XSSFSheet sheet, int columnCount) {
        sheet.setAutobreaks(true);
        PrintSetup ps = sheet.getPrintSetup();
        /*for(int i = 0; i < columnCount; i++) {
            sheet.autoSizeColumn(i);
        }
        sheet.setFitToPage(true);
        ps.setFitWidth((short)1);
        ps.setFitHeight((short)0);*/
        for(int i = 0; i < columnCount; i++) {
            sheet.setColumnWidth(i, 33200 / columnCount);
        }
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


    private int addCriminalCaseHeaders(XSSFRow row, int colNum, boolean withDetective, boolean withClosed) {
        createCell(row, colNum++, "Номер", styleHeader);
        if (withDetective) {
            createCell(row, colNum++, "Детектив", styleHeader);
        }
        createCell(row, colNum++, "Дата создания", styleHeader);
        createCell(row, colNum++, "Статус", styleHeader);
        if (withClosed) {
            createCell(row, colNum++, "Дата закрытия", styleHeader);
        }
        return colNum;
    }

    private int addDetectiveHeaders(XSSFRow row, int colNum) {
        colNum = addManHeaders(row, colNum);
        createCell(row, colNum++, "Логин", styleHeader);
        createCell(row, colNum++, "Адрес электронной почты", styleHeader);
        return colNum;
    }

    private int addParticipantHeaders(XSSFRow row, int colNum, boolean withParentMan, boolean withParentCrime) {
        if (withParentMan) {
            colNum = addManHeaders(row, colNum);
        }
        if (withParentCrime) {
            colNum = addCrimeHeaders(row, colNum, true);
        }
        createCell(row, colNum++, "Статус", styleHeader);
        createCell(row, colNum++, "Алиби", styleHeader);
        createCell(row, colNum++, "Отчёт (показания человека)", styleHeader);
        createCell(row, colNum++, "Дата добавления", styleHeader);
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

    private int addManHeaders(XSSFRow row, int colNum) {
        createCell(row, colNum++, "Имя", styleHeader);
        createCell(row, colNum++, "Фамилия", styleHeader);
        createCell(row, colNum++, "Дата рождения", styleHeader);
        createCell(row, colNum++, "Домашний адрес", styleHeader);
        createCell(row, colNum++, "Фотография", styleHeader);
        return colNum;
    }

    private int addParticipantContents(XSSFRow row, int colNum, boolean withParentMan,
                                       boolean withParentCrime, Participant participant) {
        if (withParentMan) {
            colNum = addManContents(row, colNum, participant);
        }
        if (withParentCrime) {
            colNum = addCrimeContents(row, colNum, true, participant.getParentCrime());
        }
        createCell(row, colNum++, participant.getParticipantStatus().getName(), styleUsual);
        createCell(row, colNum++, !isEmpty(participant.getAlibi()) ? participant.getAlibi() : "неизвестно", styleUsual);
        createCell(row, colNum++, !isEmpty(participant.getWitnessReport()) ? participant.getWitnessReport() : "неизвестно", styleUsual);
        createCell(row, colNum++, participant.getDateAdded().format(JSON_FORMATTER_DATETIME), styleUsual);
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

    private int addCriminalCaseContents(XSSFRow row, int colNum, boolean withDetective, boolean withClosed, CriminalCase criminalCase) {
        createCell(row, colNum++, criminalCase.getCriminalCaseNumber(), styleUsual);
        if (withDetective) {
            createCell(row, colNum++, ReportFunctions.getDetectiveData(criminalCase.getParentDetective()), styleUsual);
        }
        createCell(row, colNum++, criminalCase.getCreateDate().format(JSON_FORMATTER_DATE), styleUsual);
        createCell(row, colNum++, ReportFunctions.getCriminalCaseStatus(criminalCase), styleUsual);
        if (withClosed) {
            createCell(row, colNum++, criminalCase.getCloseDate() != null
                    ? criminalCase.getCloseDate().format(JSON_FORMATTER_DATE) : "не указана", styleUsual);
        }
        return colNum;
    }

    private int addEvidenceContents(XSSFRow row, int colNum, Evidence evidence) {
        createCell(row, colNum++, evidence.getName(), styleUsual);
        createCell(row, colNum++, evidence.getDescription(), styleUsual);
        return colNum;
    }

    private int addDetectiveContents(XSSFRow row, int colNum, Detective detective) {
        colNum = addManContents(row, colNum, detective);
        createCell(row, colNum++, detective.getLogin(), styleUsual);
        createCell(row, colNum++, detective.getEmail(), styleUsual);
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
        if (evidenceOfCrime.getPhotoPath() != null) {
            createCellHyperlinked(row, colNum++, evidenceOfCrime.getPhotoPath(), evidenceOfCrime.getPhotoPath());
        } else {
            createCell(row, colNum++, "отсутствует", styleUsual);
        }
        return colNum;
    }

    private int addManContents(XSSFRow row, int colNum, Man man) {
        createCell(row, colNum++, man.getName(), styleUsual);
        createCell(row, colNum++, man.getSurname(), styleUsual);
        createCell(row, colNum++, man.getBirthDay() != null ? man.getBirthDay().format(JSON_FORMATTER_DATE) : "неизвестно", styleUsual);
        createCell(row, colNum++, !isEmpty(man.getHomeAddress()) ? man.getHomeAddress() : "неизвестен", styleUsual);
        if (man.getPhotoPath() != null) {
            createCellHyperlinked(row, colNum++, man.getPhotoPath(), man.getPhotoPath());
        } else {
            createCell(row, colNum++, "отсутствует", styleUsual);
        }
        return colNum;
    }

    private void createMergedCell(XSSFSheet sheet, int headerRow, int resultColumns, String value) {
        XSSFRow row = sheet.createRow(headerRow);
        for (int i = 0; i < resultColumns; i++) {
            if (i == 0) {
                createCell(row,0,value, styleArea);
            } else {
                row.createCell(i).setCellStyle(styleArea);
            }
        }
        sheet.addMergedRegion(new CellRangeAddress(headerRow,headerRow,0,resultColumns-1));
    }


    private TableBorder addCrimesToSheet(XSSFSheet sheet, int rowNum, List<Crime> crimes,
                                                         boolean withCriminalCase, String title, int columnsCount) {
        int colNum = 0;
        int headerRow = rowNum++;

        XSSFRow row = sheet.createRow(rowNum++);
        int resultColumns = addCrimeHeaders(row, colNum, withCriminalCase);
        for(Crime crime : crimes) {
            row = sheet.createRow(rowNum++);
            colNum = 0;
            addCrimeContents(row, colNum, withCriminalCase, crime);
        }
        createMergedCell(sheet, headerRow, resultColumns, title);
        return new TableBorder(rowNum, columnsCount < resultColumns ? resultColumns : columnsCount);
    }

    private TableBorder addEvidenceOfCrimesToSheet(XSSFSheet sheet, int rowNum, List<EvidenceOfCrime> evidencesOfCrime,boolean withParentEvidence,
                                            boolean withParentCrime, String title, int columnsCount) {
        int colNum = 0;
        int headerRow = rowNum++;

        XSSFRow row = sheet.createRow(rowNum++);
        int resultColumns = addEvidenceOfCrimeHeaders(row, colNum, withParentEvidence, withParentCrime);
        for(EvidenceOfCrime evidenceOfCrime : evidencesOfCrime) {
            row = sheet.createRow(rowNum++);
            colNum = 0;
            addEvidenceOfCrimeContents(row, colNum, withParentEvidence, withParentCrime, evidenceOfCrime);
        }
        createMergedCell(sheet, headerRow, resultColumns, title);
        return new TableBorder(rowNum, columnsCount < resultColumns ? resultColumns : columnsCount);
    }

    private TableBorder addParticipantsToSheet(XSSFSheet sheet, int rowNum, List<Participant> participants, boolean withParentMan,
                                                   boolean withParentCrime, String title, int columnsCount) {
        int colNum = 0;
        int headerRow = rowNum++;

        XSSFRow row = sheet.createRow(rowNum++);
        int resultColumns = addParticipantHeaders(row, colNum, withParentMan, withParentCrime);
        for(Participant participant : participants) {
            row = sheet.createRow(rowNum++);
            colNum = 0;
            addParticipantContents(row, colNum, withParentMan, withParentCrime, participant);
        }
        createMergedCell(sheet, headerRow, resultColumns, title);
        return new TableBorder(rowNum, columnsCount < resultColumns ? resultColumns : columnsCount);
    }

    private TableBorder addCriminalCasesToSheet(XSSFSheet sheet, int rowNum, List<CriminalCase> criminalCases,
                                               boolean withDetective, boolean withClosed, String title, int columnsCount) {
        int colNum = 0;
        int headerRow = rowNum++;

        XSSFRow row = sheet.createRow(rowNum++);
        int resultColumns = addCriminalCaseHeaders(row, colNum, withDetective, withClosed);
        for(CriminalCase criminalCase : criminalCases) {
            row = sheet.createRow(rowNum++);
            colNum = 0;
            addCriminalCaseContents(row, colNum, withDetective, withClosed, criminalCase);
        }
        createMergedCell(sheet, headerRow, resultColumns, title);
        return new TableBorder(rowNum, columnsCount < resultColumns ? resultColumns : columnsCount);
    }

    private TableBorder addDetectiveToSheet(XSSFSheet sheet, int rowNum, List<Detective> detectives, String title, int columnsCount) {
        int colNum = 0;
        int headerRow = rowNum++;

        XSSFRow row = sheet.createRow(rowNum++);
        int resultColumns = addDetectiveHeaders(row, colNum);
        for(Detective detective : detectives) {
            row = sheet.createRow(rowNum++);
            colNum = 0;
            addDetectiveContents(row, colNum, detective);
        }
        createMergedCell(sheet, headerRow, resultColumns, title);
        return new TableBorder(rowNum, columnsCount < resultColumns ? resultColumns : columnsCount);
    }

    private TableBorder addMenToSheet(XSSFSheet sheet, int rowNum, List<Man> men, String title, int columnsCount) {
        int colNum = 0;
        int headerRow = rowNum++;

        XSSFRow row = sheet.createRow(rowNum++);
        int resultColumns = addManHeaders(row, colNum);
        for(Man man : men) {
            row = sheet.createRow(rowNum++);
            colNum = 0;
            addManContents(row, colNum, man);
        }
        createMergedCell(sheet, headerRow, resultColumns, title);
        return new TableBorder(rowNum, columnsCount < resultColumns ? resultColumns : columnsCount);
    }

    private TableBorder addEvidencesToSheet(XSSFSheet sheet, int rowNum, List<Evidence> evidences, String title, int columnsCount) {
        int colNum = 0;
        int headerRow = rowNum++;

        XSSFRow row = sheet.createRow(rowNum++);
        int resultColumns = addEvidenceHeaders(row, colNum);
        for(Evidence evidence : evidences) {
            row = sheet.createRow(rowNum++);
            colNum = 0;
            addEvidenceContents(row, colNum, evidence);
        }
        createMergedCell(sheet, headerRow, resultColumns, title);
        return new TableBorder(rowNum, columnsCount < resultColumns ? resultColumns : columnsCount);
    }

    @Override
    public String generateReportByCrime(Crime crime, List<EvidenceOfCrime> evidencesOfCrime, List<Participant> participants) throws Exception {
        File tempFile = File.createTempFile("report", ".xlsx");
        XSSFWorkbook workbook = getWorkbook();
        XSSFSheet sheet = getSheet(workbook,"Отчёт по преступлению");

        TableBorder borders = new TableBorder();

        borders = addCrimesToSheet(sheet, borders.getRow(), Arrays.asList(crime), true,
                "Преступление", borders.getColumn());
        borders.setRow(borders.getRow() + 2);
        borders = addEvidenceOfCrimesToSheet(sheet, borders.getRow(), evidencesOfCrime, true, false,
                "Прикреплённные улики", borders.getColumn());
        borders.setRow(borders.getRow() + 2);
        borders = addParticipantsToSheet(sheet, borders.getRow(), participants, true, false,
                "Участники преступления", borders.getColumn());
        setParamsToFit(workbook, sheet, borders.getColumn());

        FileOutputStream fileOut = new FileOutputStream(tempFile);
        workbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
        return tempFile.getAbsolutePath();
    }

    @Override
    public String generateReportByMan(Man man, List<Participant> participants) throws Exception {
        File tempFile = File.createTempFile("report", ".xlsx");
        XSSFWorkbook workbook = getWorkbook();
        XSSFSheet sheet = getSheet(workbook,"Отчёт по человеку");

        TableBorder borders = new TableBorder();
        borders = addMenToSheet(sheet, borders.getRow(), Arrays.asList(man), "Человек", borders.getColumn());
        borders.setRow(borders.getRow() + 2);
        borders = addParticipantsToSheet(sheet, borders.getRow(), participants, false, true,
                "Участие человека в преступлениях", borders.getColumn());
        setParamsToFit(workbook, sheet, borders.getColumn());

        FileOutputStream fileOut = new FileOutputStream(tempFile);
        workbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
        return tempFile.getAbsolutePath();
    }

    @Override
    public String generateReportByCriminalCase(CriminalCase criminalCase, List<Crime> crimes) throws Exception {
        File tempFile = File.createTempFile("report", ".xlsx");
        XSSFWorkbook workbook = getWorkbook();
        XSSFSheet sheet = getSheet(workbook,"Отчёт по уголовному делу");

        TableBorder borders = new TableBorder();
        borders = addCriminalCasesToSheet(sheet, borders.getRow(), Arrays.asList(criminalCase), true, true,
                "Уголовное дело", borders.getColumn());
        borders.setRow(borders.getRow() + 2);
        borders = addCrimesToSheet(sheet, borders.getRow(), crimes, false,
                "Улики в составе дела", borders.getColumn());
        setParamsToFit(workbook, sheet, borders.getColumn());

        FileOutputStream fileOut = new FileOutputStream(tempFile);
        workbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
        return tempFile.getAbsolutePath();
    }

    @Override
    public String generateReportByEvidence(Evidence evidence, List<EvidenceOfCrime> evidenceOfCrimes) throws Exception {
        File tempFile = File.createTempFile("report", ".xlsx");
        XSSFWorkbook workbook = getWorkbook();
        XSSFSheet sheet = getSheet(workbook,"Отчёт по улике");

        TableBorder borders = new TableBorder();
        borders = addEvidencesToSheet(sheet, borders.getRow(), Arrays.asList(evidence),
                "Улика", borders.getColumn());
        borders.setRow(borders.getRow() + 2);
        borders = addEvidenceOfCrimesToSheet(sheet, borders.getRow(), evidenceOfCrimes, false, true,
                "Участие улики в преступлениях", borders.getColumn());
        setParamsToFit(workbook, sheet, borders.getColumn());

        FileOutputStream fileOut = new FileOutputStream(tempFile);
        workbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
        return tempFile.getAbsolutePath();
    }

    @Override
    public String generateReportByDetective(Detective detective, List<CriminalCase> criminalCases) throws Exception {
        File tempFile = File.createTempFile("report", ".xlsx");
        XSSFWorkbook workbook = getWorkbook();
        XSSFSheet sheet = getSheet(workbook,"Отчёт по уголовному делу");

        TableBorder borders = new TableBorder();
        borders = addDetectiveToSheet(sheet, borders.getRow(), Arrays.asList(detective), "Детектив", borders.getColumn());
        borders.setRow(borders.getRow() + 2);
        borders = addCriminalCasesToSheet(sheet, borders.getRow(), criminalCases, false, true,
                "Расследуемые уголовные дела", borders.getColumn());
        setParamsToFit(workbook, sheet, borders.getColumn());

        FileOutputStream fileOut = new FileOutputStream(tempFile);
        workbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
        return tempFile.getAbsolutePath();
    }

    @Override
    public String generateReportByCrimes(List<Crime> crimes, LocalDate startDate, LocalDate endDate) throws Exception {
        File tempFile = File.createTempFile("report", ".xlsx");
        XSSFWorkbook workbook = getWorkbook();
        String title = "Отчёт по преступлениям за промежуток с " + startDate.format(JSON_FORMATTER_DATE)
                + " по " + endDate.format(JSON_FORMATTER_DATE);
        XSSFSheet sheet = getSheet(workbook,title);

        TableBorder borders = new TableBorder();
        borders = addCrimesToSheet(sheet, borders.getRow(), crimes, false, title, borders.getColumn());
        setParamsToFit(workbook, sheet, borders.getColumn());

        FileOutputStream fileOut = new FileOutputStream(tempFile);
        workbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
        return tempFile.getAbsolutePath();
    }

    @Override
    public String generateReportByCriminalCases(List<CriminalCase> criminalCases, String status) throws Exception {
        File tempFile = File.createTempFile("report", ".xlsx");
        XSSFWorkbook workbook = getWorkbook();
        XSSFSheet sheet = getSheet(workbook,ReportFunctions.getSheetTitleForCriminalCases(status));

        TableBorder borders = new TableBorder();
        borders = addCriminalCasesToSheet(sheet, borders.getRow(), criminalCases, true,
                status.equals("solved") || status.equals("all"),
                ReportFunctions.getTitleForCriminalCases(status), borders.getColumn());
        setParamsToFit(workbook, sheet, borders.getColumn());

        FileOutputStream fileOut = new FileOutputStream(tempFile);
        workbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
        return tempFile.getAbsolutePath();
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
