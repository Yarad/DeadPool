package com.DAO;

import com.DAO.interfaces.IDAOCrime;
import com.logic.Crime;
import com.logic.ProjectFunctions;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

public class DAOCrime extends DAO implements IDAOCrime {
    @Override
    public boolean addCrime(Crime crimeToAdd) {
        if (crimeToAdd == null) return false;

        PreparedStatement preparedStatement = currConnection.prepareStatement("INSERT INTO `crime`(`crime_place`, `crime_date`, `crime_time`, `criminal_case_id`) VALUES (?,?,?,?)");
        try {
            preparedStatement.setString(1, crimeToAdd.getCrimePlace());
            preparedStatement.setDate(2, Date.valueOf(crimeToAdd.getCrimeDate()));

            if (crimeToAdd.getCrimeTime() != null)
                preparedStatement.setTime(3, Time.valueOf(crimeToAdd.getCrimeTime()));
            else
                preparedStatement.setNull(3, 0);

            preparedStatement.setInt(4, crimeToAdd.getCriminalCaseId());
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
    public Crime getCrimeById(int crimeId) {
        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM `crime` WHERE `crime_id` = ?");

        try {
            preparedStatement.setInt(1, crimeId);
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return null;
        }

        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

        if (retArray.isEmpty()) return null;

        Crime retCrime = new Crime();
        retCrime.setCrimeId(crimeId);

        ProjectFunctions.tryFillObjectByDbArray(retCrime, retArray.get(0));
/*
        if (ProjectFunctions.ifDbObjectContainsKey(retArray.get(0), "crime_place"))
            retCrime.setCrimePlace(retArray.get(0).get("crime_place").toString());
        if (ProjectFunctions.ifDbObjectContainsKey(retArray.get(0), "crime_date"))
            retCrime.setCrimeDate(((Date) retArray.get(0).get("crime_date")).toLocalDate());
        if (ProjectFunctions.ifDbObjectContainsKey(retArray.get(0), "crime_time"))
            retCrime.setCrimeTime(((Time) retArray.get(0).get("crime_time")).toLocalTime());
        if (ProjectFunctions.ifDbObjectContainsKey(retArray.get(0), "criminal_case_id"))
            retCrime.setCriminalCaseId(Integer.valueOf(retArray.get(0).get("criminal_case_id").toString()));
*/
        return retCrime;
    }

}