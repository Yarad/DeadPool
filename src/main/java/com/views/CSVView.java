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


    //Drawers

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
        return inputObj != null ? inputObj.toString() : deaultNullStr;
    }
}