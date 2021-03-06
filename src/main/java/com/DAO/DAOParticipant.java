package com.DAO;

import com.DAO.interfaces.IDAOParticipant;
import com.logic.Man;
import com.logic.Participant;
import com.logic.ProjectFunctions;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class DAOParticipant extends DAOMan implements IDAOParticipant {
    static Logger log = Logger.getLogger(DAOParticipant.class.getName());

    @Override
    public Participant getParticipantById(long manId, long crimeId) {

        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM participant JOIN man USING(man_id) WHERE crime_id = ? AND participant.man_id = ? ");
        //PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM `participant` WHERE `crime_id` = ? AND `man_id` = ?");

        try {
            preparedStatement.setLong(1, crimeId);
            preparedStatement.setLong(2, manId);
        } catch (SQLException e) {
            log.error(e.toString());
            return null;
        }
        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

        if (retArray.isEmpty()) return null;
        Participant retParticipantRecord = new Participant();
        retParticipantRecord.setManId(manId);
        retParticipantRecord.setCrimeId(crimeId);

        ProjectFunctions.tryFillObjectByDbArray(retParticipantRecord, retArray.get(0));
        return retParticipantRecord;
    }

    @Override
    public boolean updateParticipant(Participant participantToUpdate) {
        if (participantToUpdate == null) return false;

        PreparedStatement preparedStatement = currConnection.prepareStatement("UPDATE `participant` SET " +
                "`participant_status`=?," +
                "`alibi`=?," + //nullable
                "`witness_report`=?," + //nullable
                "`date_added`=? " +
                "WHERE man_id = ? AND crime_id = ?");
        try {
            preparedStatement.setString(1, participantToUpdate.getParticipantStatus().toString());
            preparedStatement.setString(2, participantToUpdate.getAlibi());
            preparedStatement.setString(3, participantToUpdate.getWitnessReport());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(participantToUpdate.getDateAdded()));
            preparedStatement.setLong(5, participantToUpdate.getManId());
            preparedStatement.setLong(6, participantToUpdate.getCrimeId());
        } catch (SQLException e) {
            log.error(e.toString());
            return false;
        }

        return currConnection.queryDataEdit(preparedStatement);
    }

    @Override
    public List<Participant> getAllParticipantsByMan(long manId) {
        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM participant JOIN man USING(man_id) WHERE participant.man_id = ?");
        List<Participant> participants = new ArrayList<Participant>();

        try {
            preparedStatement.setLong(1, manId);
        } catch (SQLException e) {
            log.error(e.toString());
            return null;
        }
        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

        for (int i = 0; i < retArray.size(); i++) {
            Participant retParticipantRecord = new Participant();
            ProjectFunctions.tryFillObjectByDbArray(retParticipantRecord, retArray.get(i));
            participants.add(retParticipantRecord);
        }
        return participants;
    }

    @Override
    public boolean addParticipant(Participant participantToAdd) {
        if (participantToAdd == null) return false;

        PreparedStatement preparedStatement = currConnection.prepareStatement("INSERT INTO `participant`(`crime_id`, `man_id`, `alibi`, `witness_report`, `participant_status`, `date_added`) VALUES (?,?,?,?,?,?)");
        try {
            preparedStatement.setLong(1, participantToAdd.getCrimeId());
            preparedStatement.setLong(2, participantToAdd.getManId());
            preparedStatement.setString(3, participantToAdd.getAlibi());
            preparedStatement.setString(4, participantToAdd.getWitnessReport());
            preparedStatement.setString(5, participantToAdd.getParticipantStatus().toString());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(participantToAdd.getDateAdded()));
        } catch (SQLException e) {
            log.error(e.toString());
            return false;
        }

        return currConnection.queryDataEdit(preparedStatement);
    }

    //возвращает пустой массив или массив щаполненный данными, а не NULL
    @Override
    public List<Participant> getAllParticipantsByCrime(long crimeId) {
        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM participant JOIN man USING(man_id) WHERE participant.crime_id = ?");
        //PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT `criminal_case`.`criminal_case_number`, `criminal_case`.`closed`, `crime`.`crime_id`, `crime`.`description`, `crime`.`crime_date`, `participant`.`participant_status`, `participant`.`alibi`, `participant`.`witness_report` FROM `participant`, `crime`, `criminal_case` WHERE `participant`.`man_id` = ? AND `participant`.`crime_id` = `crime`.`crime_id` AND `crime`.`criminal_case_id` = `criminal_case`.`criminal_case_id`");
        List<Participant> retParticipantCrimesArray = new ArrayList<Participant>();

        try {
            preparedStatement.setLong(1, crimeId);
        } catch (SQLException e) {
            log.error(e.toString());
            return retParticipantCrimesArray;
        }

        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);
        if (retArray.isEmpty()) return retParticipantCrimesArray;

        //первая попытка использования *.tryFillObjectByDbArray
        for (int i = 0; i < retArray.size(); i++) {
            Participant participant = new Participant();
            ProjectFunctions.tryFillObjectByDbArray(participant, retArray.get(i));
            retParticipantCrimesArray.add(participant);
        }

        return retParticipantCrimesArray;
    }
}