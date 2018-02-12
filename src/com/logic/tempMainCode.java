package com.logic;

import com.DAO.DAOMaster;
import com.DAO.SQLConnection;

public class tempMainCode {
    public static void main(String[] args) {
        //создаём объект, благодаря которому будем доставать инфу о детективах
        DAOMaster MainDAOMaster = DAOMaster.getInstance();
        MainDAOMaster.setCommomConnectionToUse(new SQLConnection());

        Participant participant = MainDAOMaster.getParticipantById(17,15);
/*        Participant participant = new Participant();
        participant.setManId(17);
        participant.setCrimeId(15);
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
