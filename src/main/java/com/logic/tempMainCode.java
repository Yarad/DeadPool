package com.logic;

import com.DAO.DAOEvidenceOfCrime;

import java.time.LocalDateTime;

public class tempMainCode {
    public static void main(String[] args) {

        DAOEvidenceOfCrime daoEvidenceOfCrime = new DAOEvidenceOfCrime();

        EvidenceOfCrime evidenceOfCrime = new EvidenceOfCrime();

        evidenceOfCrime.setCrimeId(3);
        evidenceOfCrime.setEvidenceId(3);

        evidenceOfCrime.setDateAdded(LocalDateTime.now());
        evidenceOfCrime.setDetails("No details");

        boolean f = daoEvidenceOfCrime.addEvidenceOfCrime(evidenceOfCrime);
        /*
        DAOEvidenceOfCrime daoEvidenceOfCrime = new DAOEvidenceOfCrime();

        List<EvidenceOfCrime> t = daoEvidenceOfCrime.getAllEvidencesOfCrime();
        List<EvidenceOfCrime> t2 = daoEvidenceOfCrime.getAllEvidencesOfCrimeByCrimeId(3);
        List<EvidenceOfCrime> t3 = daoEvidenceOfCrime.getAllEvidencesOfCrimeByEvidenceId(1);


/*
        DAOMan daoMan = new DAOMan();
        Man man = daoMan.getFullManInfo(1);
        man.setManId(1);

        /*

        DAOParticipant daoParticipant = new DAOParticipant();
        daoParticipant.setConnectionToUse(new SQLConnection());

        DAOCrime daoCrime = new DAOCrime();
        daoCrime.setConnectionToUse(new SQLConnection());

        List<Crime> crimes = daoCrime.getCrimesByCriminalCase(2);
        List<Crime> crimes2 = daoCrime.getCrimesByCriminalCase(0);

        /*
        CriminalCase criminalCase = daoCriminalCase.getCriminalCaseById(3);
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
        DAOParticipant daoParticipant = new DAOParticipant();
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