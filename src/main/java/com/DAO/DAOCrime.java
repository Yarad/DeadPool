package com.DAO;

import com.DAO.interfaces.IDAOCrime;
import com.logic.Crime;
import com.logic.ProjectFunctions;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class DAOCrime extends DAO implements IDAOCrime {

    @Override
    public boolean addCrime(Crime crimeToAdd) {
        if (crimeToAdd == null) return false;

        PreparedStatement preparedStatement = currConnection.prepareStatement("INSERT INTO `crime`(`criminal_case_id`, `description`, `crime_date`, `crime_time`, `crime_place`,`crime_type`) VALUES (?,?,?,?,?,?)");
        try {
            preparedStatement.setLong(1, crimeToAdd.getCriminalCaseId());
            preparedStatement.setString(2, crimeToAdd.getDescription());
            preparedStatement.setDate(3, Date.valueOf(crimeToAdd.getCrimeDate()));

            if (crimeToAdd.getCrimeTime() != null)
                preparedStatement.setTime(4, Time.valueOf(crimeToAdd.getCrimeTime()));
            else
                preparedStatement.setNull(4, 0);
            preparedStatement.setString(5, crimeToAdd.getCrimePlace());
            preparedStatement.setString(6, crimeToAdd.getCrimeType().toString());
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return false;
        }
        boolean b1 = currConnection.queryDataEdit(preparedStatement);
        if (b1)
            crimeToAdd.setCrimeId(currConnection.getLastAddedId(preparedStatement));

        return b1;
    }

    @Override
    public Crime getCrimeById(long crimeId) {
        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM `crime` WHERE `crime_id` = ?");

        try {
            preparedStatement.setLong(1, crimeId);
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return null;
        }

        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

        if (retArray.isEmpty()) return null;

        Crime retCrime = new Crime();
        retCrime.setCrimeId(crimeId);

        ProjectFunctions.tryFillObjectByDbArray(retCrime, retArray.get(0));
        return retCrime;
    }

    @Override
    public boolean updateCrime(Crime crimeToUpdate) {
        if (crimeToUpdate == null) return false;
        PreparedStatement preparedStatement = currConnection.prepareStatement("UPDATE `crime` SET `description`=?,`crime_date`=?,`crime_time`=?,`crime_place`=?,`crime_type`=? WHERE `crime_id` = ?");
        try {
            preparedStatement.setString(1, crimeToUpdate.getDescription());
            preparedStatement.setDate(2, Date.valueOf(crimeToUpdate.getCrimeDate()));
            preparedStatement.setTime(3, Time.valueOf(crimeToUpdate.getCrimeTime()));
            preparedStatement.setString(4, crimeToUpdate.getCrimePlace());
            preparedStatement.setString(5, crimeToUpdate.getCrimeType().toString());
            preparedStatement.setLong(6, crimeToUpdate.getCrimeId());
        } catch (Exception e) {
            DAOLog.log(e.toString());
        }
        return currConnection.queryDataEdit(preparedStatement);
    }

    @Override
    public List<Crime> getAllCrimes() {
        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM crime");
        List<Crime> crimes = new ArrayList<Crime>();
        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

        for (int i = 0; i < retArray.size(); i++) {
            Crime retCrimeRecord = new Crime();
            ProjectFunctions.tryFillObjectByDbArray(retCrimeRecord, retArray.get(i));
            crimes.add(retCrimeRecord);
        }
        return crimes;
    }

    @Override
    public List<Crime> getCrimesBetweenDates(LocalDate dateStart, LocalDate dateEnd) {
        List<Crime> crimes = new ArrayList<Crime>();
        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM crime WHERE `crime_date` BETWEEN ? AND ? ");

        try {
            preparedStatement.setDate(1, Date.valueOf(dateStart));
            preparedStatement.setDate(2, Date.valueOf(dateEnd));
        } catch (Exception e) {
            DAOLog.log(e.toString());
            return crimes;
        }

        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

        for (int i = 0; i < retArray.size(); i++) {
            Crime retCrimeRecord = new Crime();
            ProjectFunctions.tryFillObjectByDbArray(retCrimeRecord, retArray.get(i));
            crimes.add(retCrimeRecord);
        }
        return crimes;
    }

    @Override
    public List<Crime> getCrimesByCriminalCase(long caseId) {
        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM crime WHERE criminal_case_id = ?");
        List<Crime> crimes = new ArrayList<Crime>();

        try {
            preparedStatement.setLong(1, caseId);
        } catch (Exception e) {
            DAOLog.log(e.toString());
        }

        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

        for (int i = 0; i < retArray.size(); i++) {
            Crime retCrimeRecord = new Crime();
            ProjectFunctions.tryFillObjectByDbArray(retCrimeRecord, retArray.get(i));
            crimes.add(retCrimeRecord);
        }
        return crimes;
    }

    @Override
    public List<Crime> getCrimesWhereEvidenceExists(long evidenceId) {
        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT *"+
                "        FROM `crime` as `c`" +
                "        JOIN `evidence_of_crime` as `e`" +
                "        ON `c`.`crime_id` = `e`.`crime_id`" +
                "        WHERE `e`.`evidence_id` = ?");
        List<Crime> crimes = new ArrayList<Crime>();

        try {
            preparedStatement.setLong(1, evidenceId);
        } catch (Exception e) {
            DAOLog.log(e.toString());
        }

        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

        for (int i = 0; i < retArray.size(); i++) {
            Crime retCrimeRecord = new Crime();
            ProjectFunctions.tryFillObjectByDbArray(retCrimeRecord, retArray.get(i));
            crimes.add(retCrimeRecord);
        }
        return crimes;
    }
}