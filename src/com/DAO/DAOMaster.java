package com.DAO;

import com.DAO.interfaces.*;
import com.logic.CriminalCase;
import com.logic.Detective;
import com.logic.Man;
import com.logic.Participant;

public class DAOMaster implements IDAOMaster {

    //релизация singleton
    private static DAOMaster instance;

    private DAOMaster() {
    }

    public static DAOMaster getInstance() {
        if (instance == null) {
            instance = new DAOMaster();
        }
        return instance;
    }
    //конец реализации singleton

    private IConnection connectionToUse;
    private DAOMan masterDAOMan;
    private DAODetective masterDAODetective;
    private DAOParticipant masterDAOParticipant;
    private DAOCriminalCase masterDAOCriminalCase;

    private void provideDAOCriminalCaseExist() {
        if (masterDAOCriminalCase == null) {
            masterDAOCriminalCase = new DAOCriminalCase();
            masterDAOCriminalCase.setConnectionToUse(connectionToUse);
        }
    }

    private void provideDAOManExist() {
        if (masterDAOMan == null) {
            masterDAOMan = new DAOMan();
            masterDAOMan.setConnectionToUse(connectionToUse);
        }
    }

    private void provideDAODetectiveExist() {
        if (masterDAODetective == null) {
            masterDAODetective = new DAODetective();
            masterDAODetective.setConnectionToUse(connectionToUse);
        }
    }

    private void provideDAOParticipantExist() {
        if (masterDAOParticipant == null) {
            masterDAOParticipant = new DAOParticipant();
            masterDAOParticipant.setConnectionToUse(connectionToUse);
        }
    }

    public boolean setCommomConnectionToUse(IConnection newConnection) {
        if (newConnection == null) {
            return false;
        } else {
            if (connectionToUse != null)
                connectionToUse.disconnect();
            connectionToUse = newConnection;
            if (masterDAOParticipant != null) masterDAOParticipant.setConnectionToUse(connectionToUse);
            if (masterDAODetective != null) masterDAODetective.setConnectionToUse(connectionToUse);
            if (masterDAOMan != null) masterDAOMan.setConnectionToUse(connectionToUse);
            return connectionToUse.connect();
        }
    }

    public void finalize() {
        if (connectionToUse != null)
            connectionToUse.disconnect();
    }

    @Override
    public CriminalCase getCriminalCaseById(int id) {
        provideDAOCriminalCaseExist();
        return masterDAOCriminalCase.getCriminalCaseById(id);
    }

    @Override
    public boolean addMan(Man manToAdd) {
        provideDAOManExist();
        return masterDAOMan.addMan(manToAdd);
    }

    @Override
    public Detective getDetectiveById(int id) {
        provideDAODetectiveExist();
        return masterDAODetective.getDetectiveById(id);
    }

    @Override
    public boolean addDetective(Detective detectiveToAdd) {
        provideDAODetectiveExist();
        return masterDAODetective.addDetective(detectiveToAdd);
    }

    @Override
    public boolean addParticipant(Participant participantToAdd) {
        provideDAOParticipantExist();
        return masterDAOParticipant.addParticipant(participantToAdd);
    }

    @Override
    public Participant getParticipantById(int manId, int crimeId) {
        provideDAOParticipantExist();
        return masterDAOParticipant.getParticipantById(manId, crimeId);
    }

    @Override
    public boolean addCriminalCase(CriminalCase criminalCase) {
        provideDAOCriminalCaseExist();

        return masterDAOCriminalCase.addCriminalCase(criminalCase);
    }
}