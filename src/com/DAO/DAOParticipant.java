package com.DAO;

import com.DAO.interfaces.IDAOParticipant;
import com.logic.Participant;
import com.logic.ParticipantStatus;
import com.logic.ProjectFunctions;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DAOParticipant extends DAOMan implements IDAOParticipant {
    public Participant getParticipantById(int manId, int crimeId) {

        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM `participant` WHERE `crime_id` = ? AND `man_id` = ?");

        try {
            preparedStatement.setInt(1, crimeId);
            preparedStatement.setInt(2, manId);
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return null;
        }
        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

        if (retArray.isEmpty()) return null;
        Participant retParticipantRecord = new Participant();
        retParticipantRecord.setManId(manId);
        retParticipantRecord.setCrimeId(crimeId);

        if (ProjectFunctions.ifDbObjectContainsKey(retArray.get(0), "alibi"))
            retParticipantRecord.setAlibi(retArray.get(0).get("alibi").toString());
        if (ProjectFunctions.ifDbObjectContainsKey(retArray.get(0), "witness_report"))
            retParticipantRecord.setAlibi(retArray.get(0).get("witness_report").toString());
        if (ProjectFunctions.ifDbObjectContainsKey(retArray.get(0), "participant_status"))
            retParticipantRecord.participantStatus = ParticipantStatus.valueOf(retArray.get(0).get("participant_status").toString());
        return retParticipantRecord;
    }

    public boolean addParticipant(Participant participantToAdd) {
        if (participantToAdd == null) return false;

        PreparedStatement preparedStatement = currConnection.prepareStatement("INSERT INTO `participant`(`crime_id`, `man_id`, `alibi`, `witness_report`, `participant_status`) VALUES (?,?,?,?,?)");
        try {
            preparedStatement.setInt(1, participantToAdd.getCrimeId());
            preparedStatement.setInt(2, participantToAdd.getManId());

            if (participantToAdd.getAlibi() != null)
                preparedStatement.setString(3, participantToAdd.getAlibi());
            else
                preparedStatement.setNull(3, 0);

            if (participantToAdd.getWitnessReport() != null)
                preparedStatement.setString(4, participantToAdd.getWitnessReport());
            else
                preparedStatement.setNull(4, 0);
            preparedStatement.setString(5, participantToAdd.participantStatus.toString());
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return false;
        }

        return currConnection.queryDataEdit(preparedStatement);
    }


    @Override
    //возвращает пустой массив или массив щаполненный данными, а не NULL
    public List<Participant> getParticipantInCrimesByManId(int participantId) {
        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT `criminal_case`.`criminal_case_number`, `criminal_case`.`closed`, `crime`.`crime_id`, `crime`.`description`, `crime`.`crime_date`, `participant`.`participant_status`, `participant`.`alibi`, `participant`.`witness_report` FROM `participant`, `crime`, `criminal_case` WHERE `participant`.`man_id` = ? AND `participant`.`crime_id` = `crime`.`crime_id` AND `crime`.`criminal_case_id` = `criminal_case`.`criminal_case_id`");
        List<Participant> retParticipantCrimesArray = new ArrayList<>();

        try {
            preparedStatement.setInt(1, participantId);
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return retParticipantCrimesArray;
        }

        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);
        if (retArray.isEmpty()) return retParticipantCrimesArray;

        //первая попытка использования *.tryFillObjectByDbArray
        for(int i=0;i< retArray.size();i++) {
            Participant participant = new Participant();
            ProjectFunctions.tryFillObjectByDbArray(participant, retArray.get(i));
            retParticipantCrimesArray.add(participant);
        }

        return null;
    }
}
