package com.logic;

import com.DAO.DAODetective;
import com.DAO.DAOMaster;
import com.DAO.DAOParticipant;
import com.DAO.SQLConnection;

import java.time.LocalDate;

public class tempMainCode {
    public static void main(String[] args) {
        //создаём объект, благодаря которому будем доставать инфу о детективах

        DAOMaster MainDAOMaster = DAOMaster.getInstance();
        MainDAOMaster.setCommomConnectionToUse(new SQLConnection());

        Detective myDetective = MainDAOMaster.getDetectiveById(17);

        //добавление crimimalCase
        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setDetectiveId(32);
        criminalCase.setCriminalCaseNumber("какой-то номер дела");
        MainDAOMaster.addCriminalCase(criminalCase);

        /*
        //дообавление детектива
        Detective newDetective = new Detective();
        newDetective.setName("Andr");
        newDetective.setSurname("Zhlobich");
        newDetective.setLogin("log897");
        newDetective.setPassword("password");
        newDetective.setBirthDay(LocalDate.now());
        newDetective.setHomeAddress("Osipovichi");

        boolean is_added = MainDAOMaster.addDetective(newDetective);


        //добавление участника(crime остётся чисто по номеру (решено общим голосованием))
        DAOParticipant newParticipantDao = new DAOParticipant();
        newParticipantDao.setConnectionToUse(new SQLConnection());

        Participant participantToAdd = new Participant();
        participantToAdd.setName("Buloichikl");
        participantToAdd.setSurname("Anna");
        participantToAdd.setAlibi("Somthing about alibi");
        participantToAdd.setCrimeId(1);
        participantToAdd.setBirthDay(LocalDate.now());
        participantToAdd.setWitnessReport("WitnessReportasd klsak lskdl sakds lksal");

        newParticipantDao.addParticipant(participantToAdd);*/
    }
}
