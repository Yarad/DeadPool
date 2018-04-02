package com.Additionals;

import com.DAO.DAOLog;
import com.DAO.SQLConnection;
import com.DAO.interfaces.IConnection;
import com.logic.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOAdditionals {
    private IConnection currConnection;

    public DAOAdditionals() {
        currConnection = new SQLConnection();
        currConnection.connect();
    }

    public boolean deleteMan(Man man) {
        if (man == null) return false;

        PreparedStatement preparedQuery = currConnection.prepareStatement("DELETE FROM `man` WHERE `man_id` = ?");
        try {
            preparedQuery.setLong(1, man.getManId());
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return false;
        }
        return currConnection.queryDataEdit(preparedQuery);
    }

    public boolean deleteDetective(Detective detective) {
        if (detective == null) return false;

        PreparedStatement preparedQuery = currConnection.prepareStatement("DELETE FROM `detective` WHERE `detective_id` = ?");
        try {
            preparedQuery.setLong(1, detective.getManId());
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return false;
        }
        if (currConnection.queryDataEdit(preparedQuery)) {
            return deleteMan(detective);
        } else
            return false;
    }

    public boolean deleteCrime(Crime crime) {
        if (crime == null) return false;

        PreparedStatement preparedQuery = currConnection.prepareStatement("DELETE FROM `crime` WHERE `crime_id` = ?");
        try {
            preparedQuery.setLong(1, crime.getCrimeId());
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return false;
        }
        return currConnection.queryDataEdit(preparedQuery);
    }

    public boolean deleteCriminalCase(CriminalCase criminalCase) {
        if (criminalCase == null) return false;

        PreparedStatement preparedQuery = currConnection.prepareStatement("DELETE FROM `criminal_case` WHERE `criminal_case_id` = ?");
        try {
            preparedQuery.setLong(1, criminalCase.getCriminalCaseId());
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return false;
        }
        return currConnection.queryDataEdit(preparedQuery);
    }

    public boolean deleteParticipant(Participant participant) {
        if (participant == null) return false;

        PreparedStatement preparedQuery = currConnection.prepareStatement("DELETE FROM `participant` WHERE `man_id` = ? AND `crime_id` = ?");
        try {
            preparedQuery.setLong(1, participant.getManId());
            preparedQuery.setLong(2, participant.getCrimeId());
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return false;
        }
        return currConnection.queryDataEdit(preparedQuery);
    }

    public boolean deleteEvidenceOfCrime(EvidenceOfCrime evidenceOfCrime) {
        if (evidenceOfCrime == null) return false;

        PreparedStatement preparedQuery = currConnection.prepareStatement("DELETE FROM `evidence_of_crime` WHERE `evidence_id` = ? AND `crime_id` = ?");
        try {
            preparedQuery.setLong(1, evidenceOfCrime.getParentEvidence().getEvidenceId());
            preparedQuery.setLong(2, evidenceOfCrime.getParentCrime().getCrimeId());
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return false;
        }
        return currConnection.queryDataEdit(preparedQuery);
    }

    public boolean deleteEvidence(Evidence evidence) {
        if (evidence == null) return false;

        PreparedStatement preparedQuery = currConnection.prepareStatement("DELETE FROM `evidence` WHERE `evidence_id` = ?");
        try {
            preparedQuery.setLong(1, evidence.getEvidenceId());
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return false;
        }
        return currConnection.queryDataEdit(preparedQuery);
    }
}
