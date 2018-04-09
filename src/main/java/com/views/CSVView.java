package com.views;

import com.logic.*;
import com.views.interfaces.IReportView;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

@Component
public class CSVView implements IReportView {
    private static char delimiter = ';';
    private static char newLine = '\n';
    private static String deaultNullStr = "неизвестно";

    @Override //Done
    public String generateReportByCrimes(List<Crime> crimes, LocalDate startDate, LocalDate endDate) throws Exception {
        File file = File.createTempFile("report_" + startDate.toString() + "-" + endDate.toString(), ".csv");

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            writer.write('\uFEFF');
            //Crime report
            writer.append(getCrimeCsvHeader() + newLine);
            for (int i = 0; i < crimes.size(); i++)
                writer.append(getCsvStringOfCrimeObject(crimes.get(i)) + newLine);

            writer.flush();
        } catch (Exception e) {
            return null;
        }

        return file.getAbsolutePath();
    }

    @Override //Done
    public String generateReportByCriminalCases(List<CriminalCase> criminalCases, String status) throws Exception {
        File file = File.createTempFile("report_criminal_cases_" + status, ".csv");

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            writer.write('\uFEFF');
            //Crime report
            writer.append(getCriminalCaseCsvHeader() + newLine);
            for (int i = 0; i < criminalCases.size(); i++)
                writer.append(getCsvStringOfCriminalCaseObj(criminalCases.get(i)) + newLine);

            writer.flush();
        } catch (Exception e) {
            return null;
        }

        return file.getAbsolutePath();
    }

    @Override //Done
    public String generateReportByCrime(Crime crime, List<EvidenceOfCrime> evidencesOfCrime, List<Participant> participants) throws Exception {
        File file = File.createTempFile("report", ".csv");

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            writer.write('\uFEFF');
            //Crime report
            writer.append(getCrimeCsvHeader() + newLine);
            writer.append(getCsvStringOfCrimeObject(crime) + newLine);

            writer.append(newLine);

            //evidences of this crime report
            writer.append(getEvidencesOfCrimeCsvHeader() + newLine);

            for (int i = 0; i < evidencesOfCrime.size(); i++)
                writer.append(getCsvStringOfEvidenceOfCrimeObject(evidencesOfCrime.get(i)) + newLine);

            writer.append(newLine);

            //Participants report
            writer.append(getParticipantsCsvHeader() + newLine);
            for (int i = 0; i < participants.size(); i++)
                writer.append(getCsvStringOfParticipantObject(participants.get(i)));

            writer.flush();
        } catch (Exception e) {
            return null;
        }

        return file.getAbsolutePath();
    }

    @Override //Done
    public String generateReportByMan(Man man, List<Participant> participants) throws Exception {
        File file = File.createTempFile("report", ".csv");

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            writer.write('\uFEFF');

            //Man report
            writer.append(getManCsvHeader() + newLine);
            writer.append(getCsvStringOfManObject(man) + newLine);

            writer.append(newLine);

            //Participants report
            writer.append(getPartcipanceManInCrimeHeader() + newLine);
            for (int i = 0; i < participants.size(); i++)
                writer.append(getCsvStringOfPartcipanceManInCrime(participants.get(i)) + newLine);
            writer.flush();
        } catch (Exception e) {
            return null;
        }

        return file.getAbsolutePath();
    }

    @Override //Done
    public String generateReportByCriminalCase(CriminalCase criminalCase, List<Crime> crimes) throws Exception {
        File file = File.createTempFile("report", ".csv");

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            writer.write('\uFEFF');
            //Criminal case report
            writer.append(getCriminalCaseCsvHeader() + newLine);
            writer.append(getCsvStringOfCriminalCaseObj(criminalCase) + newLine);

            writer.write(newLine);

            //Crimes

            writer.append(getCrimeCsvHeader() + newLine);
            for (int i = 0; i < crimes.size(); i++)
                writer.write(getCsvStringOfCrimeObject(crimes.get(i)) + newLine);

            writer.flush();
        } catch (Exception e) {
            return null;
        }

        return file.getAbsolutePath();
    }

    @Override //Done
    public String generateReportByEvidence(Evidence evidence, List<EvidenceOfCrime> evidenceOfCrimes) throws Exception {
        File file = File.createTempFile("report", ".csv");

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            writer.write('\uFEFF');


            writer.append(getEvidenceHeader() + newLine);
            writer.append(getCsvStringOfEvidenceObject(evidence) + newLine);

            writer.append(newLine);

            writer.append(getEvidencesOfCrimeCsvHeader() + newLine);
            for (int i = 0; i < evidenceOfCrimes.size(); i++)
                writer.append(getCsvStringOfEvidenceOfCrimeObject(evidenceOfCrimes.get(i)) + newLine);
            writer.flush();
        } catch (Exception e) {
            return null;
        }

        return file.getAbsolutePath();

    }

    @Override //Done
    public String generateReportByDetective(Detective detective, List<CriminalCase> criminalCases) throws Exception {
        File file = File.createTempFile("report", ".csv");

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            writer.write('\uFEFF');

            //Man report
            writer.append(getDetectiveCsvHeader() + newLine);
            writer.append(getCsvStringOfDetectiveObject(detective) + newLine);

            writer.append(newLine);

            //criminal cases report
            writer.append(getCriminalCaseCsvHeader() + newLine);
            for (int i = 0; i < criminalCases.size(); i++)
                writer.append(getCsvStringOfCriminalCaseObj(criminalCases.get(i)) + newLine);
            writer.flush();
        } catch (Exception e) {
            return null;
        }

        return file.getAbsolutePath();
    }

    //Drawers
    private String getEvidenceHeader() {
        return "Название" + delimiter + "Описание";
    }

    private String getCsvStringOfEvidenceObject(Evidence evidence) {
        return escapeNullException(evidence.getName()) + delimiter +
                escapeNullException(evidence.getDescription());
    }

    private String getDetectiveCsvHeader() {
        return "Имя" + delimiter +
                "Фамилия" + delimiter +
                "Дата рождения" + delimiter +
                "Домашний адрес" + delimiter +
                "Фотография" + delimiter +
                "Логин" + delimiter +
                "Адрес электронной почты";
    }

    private String getCsvStringOfDetectiveObject(Detective detective) {
        return escapeNullException(detective.getName()) + delimiter +
                escapeNullException(detective.getSurname()) + delimiter +
                escapeNullException(detective.getBirthDay()) + delimiter +
                escapeNullException(detective.getHomeAddress()) + delimiter +
                escapeNullException(detective.getPhotoPath()) + delimiter +
                escapeNullException(detective.getLogin()) + delimiter +
                escapeNullException(detective.getEmail());
    }

    private String getCriminalCaseCsvHeader() {
        return "Номер" + delimiter +
                "Детектив" + delimiter +
                "Дата создания" + delimiter +
                "Статус" + delimiter +
                "Дата закрытия";
    }

    private String getCsvStringOfCriminalCaseObj(CriminalCase criminalCase) {
        return escapeNullException(criminalCase.getCriminalCaseNumber()) + delimiter +
                escapeNullException(criminalCase.getParentDetective().getSurname() + " " + criminalCase.getParentDetective().getName()) + delimiter +
                escapeNullException(criminalCase.getCreateDate()) + delimiter +
                escapeNullException(criminalCase.isClosed() ? "закрыто" : "не закрыто") + delimiter +
                escapeNullException(criminalCase.getCloseDate());
    }

    private String getParticipantsCsvHeader() {
        return "Имя" + delimiter +
                "Фамилия" + delimiter +
                "Дата рождения" + delimiter +
                "Домашний адрес" + delimiter +
                "Фотография" + delimiter +
                "Статус" + delimiter +
                "Алиби" + delimiter +
                "Отчёт (показания человека)" + delimiter +
                "Дата добавления";
    }

    private String getCsvStringOfParticipantObject(Participant participant) {
        return escapeNullException(participant.getName()) + delimiter +
                escapeNullException(participant.getSurname()) + delimiter +
                escapeNullException(participant.getBirthDay()) + delimiter +
                escapeNullException(participant.getHomeAddress()) + delimiter +
                escapeNullException(participant.getPhotoPath()) + delimiter +
                escapeNullException(participant.getParticipantStatus()) + delimiter +
                escapeNullException(participant.getAlibi()) + delimiter +
                escapeNullException(participant.getWitnessReport()) + delimiter +
                escapeNullException(participant.getDateAdded());
    }

    private String getPartcipanceManInCrimeHeader() {
        return "Номер уголовного дела" + delimiter +
                "Тип" + delimiter +
                "Дата совершения" + delimiter +
                "Время совершения" + delimiter +
                "Место совершения" + delimiter +
                "Описание" + delimiter +
                "Статус" + delimiter +
                "Алиби" + delimiter +
                "Отчёт (показания человека)" + delimiter +
                "Дата добавления";
    }

    private String getCsvStringOfPartcipanceManInCrime(Participant participant) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(escapeNullException(participant.getParentCrime().getParentCriminalCase().getCriminalCaseNumber()) + delimiter);
        stringBuilder.append(escapeNullException(participant.getParentCrime().getCrimeType()) + delimiter);
        stringBuilder.append(escapeNullException(participant.getParentCrime().getCrimeDate()) + delimiter);
        stringBuilder.append(escapeNullException(participant.getParentCrime().getCrimeTime()) + delimiter);
        stringBuilder.append(escapeNullException(participant.getParentCrime().getCrimePlace()) + delimiter);
        stringBuilder.append(escapeNullException(participant.getParentCrime().getDescription()) + delimiter);
        stringBuilder.append(escapeNullException(participant.getParticipantStatus()) + delimiter);
        stringBuilder.append(escapeNullException(participant.getAlibi()) + delimiter);
        stringBuilder.append(escapeNullException(participant.getWitnessReport()) + delimiter);
        stringBuilder.append(escapeNullException(participant.getDateAdded()) + delimiter);

        return stringBuilder.toString();
    }

    private String getManCsvHeader() {
        return "Имя" + delimiter +
                "Фамилия" + delimiter +
                "Дата рождения" + delimiter +
                "Домашний адрес" + delimiter +
                "Фотография";
    }

    private String getCsvStringOfManObject(Man man) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(escapeNullException(man.getName()) + delimiter);
        stringBuilder.append(escapeNullException(man.getSurname()) + delimiter);
        stringBuilder.append(escapeNullException(man.getBirthDay()) + delimiter);
        stringBuilder.append(escapeNullException(man.getHomeAddress()) + delimiter);
        stringBuilder.append(escapeNullException(man.getPhotoPath()) + delimiter);

        return stringBuilder.toString();
    }

    private String getCrimeCsvHeader() {
        return "Номер уголовного дела" + delimiter +
                "Тип" + delimiter +
                "Дата совершения" + delimiter +
                "Время совершения" + delimiter +
                "Место совершения" + delimiter +
                "Описание";
    }

    private String getEvidencesOfCrimeCsvHeader() {
        return "Название" + delimiter +
                "Описание" + delimiter +
                "Тип улики" + delimiter +
                "Детальная информация" + delimiter +
                "Дата добавления" + delimiter +
                "Фотография";
    }

    private String getCsvStringOfEvidenceOfCrimeObject(EvidenceOfCrime evidenceOfCrime) {
        Evidence evidence = evidenceOfCrime.getParentEvidence();
        return escapeNullException(evidence.getName()) + delimiter +
                escapeNullException(evidence.getDescription()) + delimiter +
                escapeNullException(evidenceOfCrime.getEvidenceType()) + delimiter +
                escapeNullException(evidenceOfCrime.getDetails()) + delimiter +
                escapeNullException(evidenceOfCrime.getDateAdded()) + delimiter +
                escapeNullException(evidenceOfCrime.getPhotoPath());
    }

    private String getCsvStringOfCrimeObject(Crime crime) {
        return escapeNullException(crime.getParentCriminalCase().getCriminalCaseNumber()) + delimiter +
                escapeNullException(crime.getCrimeType()) + delimiter +
                escapeNullException(crime.getCrimeDate()) + delimiter +
                escapeNullException(crime.getCrimeTime()) + delimiter +
                escapeNullException(crime.getCrimePlace()) + delimiter +
                escapeNullException(crime.getDescription()) + delimiter;
    }

    private String escapeNullException(Object inputObj) {
        return inputObj != null && !inputObj.toString().equals("") ? inputObj.toString() : deaultNullStr;
    }
}