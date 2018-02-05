package com.DAO;

import com.DAO.interfaces.IDAOParticipant;
import com.logic.Detective;
import com.logic.Participant;
import com.logic.ParticipantStatus;

import java.util.HashMap;
import java.util.List;

public class DAOParticipant extends DAOMan implements IDAOParticipant{
    public Participant getParticipantById(int manId, int crimeId) {

        Participant retParticipantRecord = new Participant();

        fillInfoFromManTableById(manId, retParticipantRecord);
        fillInfoFromParticipantTableById(manId, crimeId, retParticipantRecord);

        return retParticipantRecord;
    }

    public boolean addParticipant(Participant participantToAdd) {
        boolean retValue = addMan(participantToAdd);
        participantToAdd.setManId(currConnection.getLastAddedId());
        retValue = currConnection.queryDataEdit("INSERT INTO `participant`(`" +
                "crime_id`, `man_id`, `alibi`, `witness_report`, `participant_status_id`) VALUES (" +
                +participantToAdd.getCrimeId() + "," +
                +participantToAdd.getManId() + "," +
                "'" + participantToAdd.getAlibi() + "'," +
                "'" + participantToAdd.getWitnessReport() + "'," +
                participantToAdd.participantStatus.ordinal() + ")") && retValue;
        return retValue;
    }

    private boolean fillInfoFromParticipantTableById(int manID, int crimeId, Participant objectToFill) {
        List<HashMap<String, Object>> retArray = currConnection.queryFind("SELECT * FROM `participant` WHERE `man_id` = " + manID + " AND `crime_id ` = " + crimeId);
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
            List<HashMap<String, Object>> retArray = currConnection.queryFind("SELECT `name` FROM `participant_status_id` WHERE `participant_status_id` = " + participantStatusId);
            if (retArray.isEmpty()) return false;
            participantObject.participantStatus = ParticipantStatus.valueOf(retArray.get(0).toString());
        } catch (Exception e) {
        }
        return true;
    }
}
