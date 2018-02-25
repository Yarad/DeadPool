package com.DAO;

import com.DAO.interfaces.IDAOParticipant;
import com.logic.Participant;
import com.logic.ProjectFunctions;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DAOParticipant extends DAOMan implements IDAOParticipant {
    public Participant getParticipantById(long manId, long crimeId) {

        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM participant JOIN man USING(man_id) WHERE crime_id = ? AND participant.man_id = ? ");
        //PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM `participant` WHERE `crime_id` = ? AND `man_id` = ?");

        try {
            preparedStatement.setLong(1, crimeId);
            preparedStatement.setLong(2, manId);
        } catch (SQLException e) {
            DAOLog.log(e.toString());
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
                "`witness_report`=? " + //nullable
                "WHERE man_id = ?");
        PreparedStatement preparedStatement2 = currConnection.prepareStatement("UPDATE `man` SET " +
                "`name`=?," +
                "`surname`=?," +
                "`birthday`=?," + //nullable
                "`home_address`=? " + //nullable
                "WHERE man_id = ?");
        try {
            preparedStatement.setString(1, participantToUpdate.participantStatus.toString());

            if (participantToUpdate.getAlibi() != null)
                preparedStatement.setString(2, participantToUpdate.getAlibi());
            else
                preparedStatement.setNull(2, 0);

            if (participantToUpdate.getWitnessReport() != null)
                preparedStatement.setString(3, participantToUpdate.getWitnessReport());
            else
                preparedStatement.setNull(3, 0);
            preparedStatement.setLong(4, participantToUpdate.getManId());
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return false;
        }

        try {

            preparedStatement2.setString(1, participantToUpdate.getName());
            preparedStatement2.setString(2, participantToUpdate.getSurname());

            if (participantToUpdate.getBirthDay() != null)
                preparedStatement2.setDate(3, Date.valueOf(participantToUpdate.getBirthDay()));
            else
                preparedStatement2.setNull(3, 0);

            if (participantToUpdate.getHomeAddress() != null)
                preparedStatement2.setString(4, participantToUpdate.getHomeAddress());
            else
                preparedStatement2.setNull(4, 0);

            preparedStatement2.setLong(5, participantToUpdate.getManId());
        } catch (Exception e) {
            DAOLog.log(e.toString());
        }

        boolean res1 = currConnection.queryDataEdit(preparedStatement);
        boolean res2 = currConnection.queryDataEdit(preparedStatement2);
        return res1 && res2;
    }

    @Override
    public List<Participant> getAllParticipantsByMan(long manId) {
        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM participant JOIN man USING(man_id) WHERE participant.man_id = ?");
        List<Participant> participants = new ArrayList<Participant>();

        try {
            preparedStatement.setLong(1, manId);
        } catch (SQLException e) {
            DAOLog.log(e.toString());
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

    public boolean addParticipant(Participant participantToAdd) {
        if (participantToAdd == null) return false;

        PreparedStatement preparedStatement = currConnection.prepareStatement("INSERT INTO `participant`(`crime_id`, `man_id`, `alibi`, `witness_report`, `participant_status`) VALUES (?,?,?,?,?)");
        try {
            preparedStatement.setLong(1, participantToAdd.getCrimeId());
            preparedStatement.setLong(2, participantToAdd.getManId());

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

    //возвращает пустой массив или массив щаполненный данными, а не NULL
    @Override
    public List<Participant> getAllParticipantsByCrime(long participantId) {
        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT `criminal_case`.`criminal_case_number`, `criminal_case`.`closed`, `crime`.`crime_id`, `crime`.`description`, `crime`.`crime_date`, `participant`.`participant_status`, `participant`.`alibi`, `participant`.`witness_report` FROM `participant`, `crime`, `criminal_case` WHERE `participant`.`man_id` = ? AND `participant`.`crime_id` = `crime`.`crime_id` AND `crime`.`criminal_case_id` = `criminal_case`.`criminal_case_id`");
        List<Participant> retParticipantCrimesArray = new ArrayList<>();

        try {
            preparedStatement.setLong(1, participantId);
        } catch (SQLException e) {
            DAOLog.log(e.toString());
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

        return null;
    }
}