package com.Additionals;

import com.DAO.*;
import com.DAO.interfaces.*;
import com.logic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.LocalDate;

public class AllClassesList {
    private IDAOMan daoMan;
    private IDAODetective daoDetective;
    private IDAOParticipant daoParticipant;
    private IDAOCrime daoCrime;
    private IDAOCriminalCase daoCriminalCase;
    private IDAOEvidence daoEvidence;
    private IDAOEvidenceOfCrime daoEvidenceOfCrime;
    private DAOAdditionals daoAdditionals;

    private Crime crime;
    private CriminalCase criminalCase;
    private Man man;
    private Detective detective;
    private Participant participant;
    private Evidence evidence;
    private EvidenceOfCrime evidenceOfCrime;

    public AllClassesList() {
        daoMan = new DAOMan();
        daoCrime = new DAOCrime();
        daoParticipant = new DAOParticipant();
        daoDetective = new DAODetective();
        daoCriminalCase = new DAOCriminalCase();
        daoEvidence = new DAOEvidence();
        daoEvidenceOfCrime = new DAOEvidenceOfCrime();
        daoAdditionals = new DAOAdditionals();
    }

    public Crime getCrime() {
        return crime;
    }

    public CriminalCase getCriminalCase() {
        return criminalCase;
    }

    public Detective getDetective() {
        return detective;
    }

    public Man getMan() {
        return man;
    }

    public Evidence getEvidence() {
        return evidence;
    }

    public EvidenceOfCrime getEvidenceOfCrime() {
        return evidenceOfCrime;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void addCustomManToDatabase() throws Exception {
        man = LogicAdditionals.getManWithDates();
        if (!daoMan.addMan(man))
            throw new Exception();
    }

    public void addCustomDetectiveToDatabase(String detectiveLogin) throws Exception {
        detective = LogicAdditionals.getDetectiveWithDates();
        detective.setLogin(detectiveLogin);
        if (!daoDetective.addDetective(detective))
            throw new Exception();
    }

    public void addSolvedCriminalCaseToDatabase(String detectiveLogin) throws Exception {
        addCustomDetectiveToDatabase(detectiveLogin);
        criminalCase = LogicAdditionals.getCriminalCaseSolved();
        criminalCase.setDetectiveId(detective.getManId());
        if (!daoCriminalCase.addCriminalCase(criminalCase))
            throw new Exception();
    }

    public void addUnsolvedCriminalCaseToDatabase(String detectiveLogin) throws Exception {
        addCustomDetectiveToDatabase(detectiveLogin);
        criminalCase = LogicAdditionals.getCriminalCaseUnsolved();
        criminalCase.setDetectiveId(detective.getManId());
        if (!daoCriminalCase.addCriminalCase(criminalCase))
            throw new Exception();
    }

    public void addOpenCriminalCaseToDatabase(String detectiveLogin) throws Exception {
        addCustomDetectiveToDatabase(detectiveLogin);
        criminalCase = LogicAdditionals.getCriminalCaseOpen();
        criminalCase.setDetectiveId(detective.getManId());
        if (!daoCriminalCase.addCriminalCase(criminalCase))
            throw new Exception();
    }

    public void addCustomParticipantToDatabase(String detectiveLogin) throws Exception {
        addCustomManToDatabase();
        addSolvedCriminalCaseToDatabase(detectiveLogin);
        crime = LogicAdditionals.getCrimeWithDates();
        crime.setCriminalCaseId(criminalCase.getCriminalCaseId());
        if (!daoCrime.addCrime(crime))
            throw new Exception();
        participant = LogicAdditionals.getParticipantWithDates();
        participant.setCrimeId(crime.getCrimeId());
        participant.setManId(man.getManId());
        if (!daoParticipant.addParticipant(participant))
            throw new Exception();
    }

    public void deleteAllAddedEntities() throws Exception {
        if (participant != null) {
            if (!daoAdditionals.deleteParticipant(participant))
                throw new Exception();
        }
        if (evidenceOfCrime != null) {
            if (!daoAdditionals.deleteEvidenceOfCrime(evidenceOfCrime))
                throw new Exception();
        }
        if (evidence != null) {
            if (!daoAdditionals.deleteEvidence(evidence))
                throw new Exception();
        }
        if (crime != null) {
            if (!daoAdditionals.deleteCrime(crime))
                throw new Exception();
        }
        if (criminalCase != null) {
            if (!daoAdditionals.deleteCriminalCase(criminalCase))
                throw new Exception();
        }
        if (detective != null) {
            if (!daoAdditionals.deleteDetective(detective))
                throw new Exception();
        }
        if (man != null) {
            if (!daoAdditionals.deleteMan(man))
                throw new Exception();
        }
    }
}
