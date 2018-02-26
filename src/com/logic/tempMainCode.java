package com.logic;

import com.DAO.DAOCriminalCase;
import com.DAO.DAODetective;
import com.DAO.DAOParticipant;
import com.DAO.SQLConnection;

import java.util.List;

public class tempMainCode {
    public static void main(String[] args) {
        DAOParticipant daoParticipant = new DAOParticipant();
        daoParticipant.setConnectionToUse(new SQLConnection());

        DAODetective daoDetective = new DAODetective();
        daoDetective.setConnectionToUse(new SQLConnection());

        DAOCriminalCase daoCriminalCase = new DAOCriminalCase();
        daoCriminalCase.setConnectionToUse(new SQLConnection());

        List<CriminalCase> criminalCases = daoCriminalCase.getAllCrimesOfDetective(0);
        /*CriminalCase criminalCase = daoCriminalCase.getCriminalCaseById(3);
        criminalCase.setCloseDate(LocalDate.now());
        criminalCase.setCriminalCaseNumber("new criminal case number");
        daoCriminalCase.updateCriminalCase(criminalCase);
/*
        Detective detective = daoDetective.getDetectiveById(1);
        detective.setLogin("newlogin");
        detective.setHashOfPassword("newhash");
        detective.setName("Holms");
        boolean res = daoDetective.updateDetective(detective);

        /*
        Participant participant = daoParticipant.getParticipantById(1, 3);

        participant.setWitnessReport(participant.getWitnessReport() + "new");
        participant.setName(participant.getName() + "new");
        participant.setSurname(participant.getSurname() + "new");
        participant.setBirthDay(LocalDate.now());

        //List<Participant> participants = daoParticipant.getAllParticipantsByCrime(3);
/*
        boolean res = daoParticipant.updateParticipant(participant);

        /*
        /*Detective detective = daoDetective.getDetectiveById(1);

        List<Participant> participants = daoParticipant.getAllParticipantsByMan(1);

        /*
        //Participant participant = MainDAOMaster.getParticipantById(17,15);
        Participant participant = new Participant();
        participant.setManId(2);
        participant.setCrimeId(3);
        participant.setWitnessReport("SomeReportInfo");

        //com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry '15' for key 'PRIMARY
        //неправильно настроенная БД!!! Должно быть ВОЗМОЖНО добавить несколько участников к преступлению.
        //и НЕЛЬЗЯ добавить участника по несуществующему crime

        boolean b1 = MainDAOMaster.addParticipant(participant);

        /*Crime crime = new Crime();
        crime.setCriminalCaseId(2);
        crime.setCrimePlace("mesto");
        crime.setCrimeTime(null);
        boolean b1 = MainDAOMaster.addCrime(crime);

        Crime crime = MainDAOMaster.getCrimeById(1);


        Detective myDetective = MainDAOMaster.getDetectiveById(17);
        //добавление crimimalCase
        CriminalCase criminalCase = MainDAOMaster.getCriminalCaseById(1);

        //добавление crimimalCase
        //CriminalCase criminalCase = new CriminalCase();
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