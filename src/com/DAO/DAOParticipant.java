package com.DAO;

import com.DAO.interfaces.IDAOParticipant;
import com.logic.Participant;
import com.logic.ParticipantStatus;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class DAOParticipant extends DAOMan implements IDAOParticipant {
    public Participant getParticipantById(int manId, int crimeId) {

        Participant retParticipantRecord = new Participant();

        fillInfoFromManTableById(manId, retParticipantRecord);
        fillInfoFromParticipantTableById(manId, crimeId, retParticipantRecord);

        return retParticipantRecord;
    }

    public boolean addParticipant(Participant participantToAdd) {
        /*boolean retValue = addMan(participantToAdd);
        if(!retValue) return false;

        //здеь не будет работать автоматическое добавление, ибо нужно просто в лоб задавать ключи crime_id и man_id! REDO!!!!
        PreparedStatement preparedQuery = currConnection.prepareStatement("INSERT INTO `participant`(`crime_id`, `man_id`, `alibi`, `witness_report`, `participant_status_id`) VALUES ([value-1],[value-2],[value-3],[value-4],[value-5])")
        currConnection.queryDataEdit("INSERT INTO `participant`(`" +
                "crime_id`, `man_id`, `alibi`, `witness_report`, `participant_status_id`) VALUES (" +
                +participantToAdd.getCrimeId() + "," +
                +participantToAdd.getManId() + "," +
                "'" + participantToAdd.getAlibi() + "'," +
                "'" + participantToAdd.getWitnessReport() + "'," +
                participantToAdd.participantStatus.ordinal() + ")");

        return retValue;
        */
        return false;
    }

    private boolean fillInfoFromParticipantTableById(int manID, int crimeId, Participant objectToFill) {
        // List<HashMap<String, Object>> retArray = currConnection.queryFind("SELECT * FROM `participant` WHERE `man_id` = ? AND `crime_id ` = ?");
        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM `participant` WHERE `man_id` = ? AND `crime_id ` = ?");

        try {
            preparedStatement.setInt(1, manID);
            preparedStatement.setInt(2, crimeId);
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return false;
        }

        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

        if (retArray.isEmpty()) return false;

        if (retArray.get(0).containsKey("alibi"))
            objectToFill.setAlibi(retArray.get(0).get("alibi").toString());
        if (retArray.get(0).containsKey("witness_report"))
            objectToFill.setWitnessReport(retArray.get(0).get("witness_report").toString());
        if (retArray.get(0).containsKey("participant_status_id")) {
            int participantStatusId = Integer.parseInt(retArray.get(0).get("participant_status_id").toString());
            fillParticipantStatusById(participantStatusId, objectToFill);
        }

        return true;
    }

    private boolean fillParticipantStatusById(int participantStatusId, Participant participantObject) {
        try {
            PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT `name` FROM `participant_status_id` WHERE `participant_status_id` = ?");
            try {
                preparedStatement.setInt(1, participantStatusId);
            } catch (SQLException e) {
                DAOLog.log(e.toString());
                return false;
            }
            List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

            if (retArray.isEmpty()) return false;
            participantObject.participantStatus = ParticipantStatus.valueOf(retArray.get(0).toString());
        } catch (Exception e) {
            DAOLog.log(e.toString());
            return false;
        }
        return true;
    }
}
