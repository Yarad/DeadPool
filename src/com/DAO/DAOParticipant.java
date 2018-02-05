package com.DAO;

import com.logic.Detective;
import com.logic.Participant;
import com.logic.ParticipantStatus;

import java.util.HashMap;
import java.util.List;

public class DAOParticipant extends DAOMan {
    public Participant getParticipantById(int manId, int crimeId) {

        Participant retParticipantRecord = new Participant();

        fillInfoFromManTableById(manId, retParticipantRecord);
        fillInfoFromParticipantTableById(manId, crimeId, retParticipantRecord);

        return retParticipantRecord;
    }

    //public Participant

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
        List<HashMap<String, Object>> retArray = currConnection.queryFind("SELECT `name` FROM `participant_status_id` WHERE `participant_status_id` = " + participantStatusId);
        if (retArray.isEmpty()) return false;
        participantObject.participantStatus = ParticipantStatus.valueOf(retArray.get(0).toString());

        return true;
    }
}
