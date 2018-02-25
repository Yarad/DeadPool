package com.DAO;

import com.DAO.interfaces.IDAOCriminalCase;
import com.logic.CriminalCase;
import com.logic.ProjectFunctions;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class DAOCriminalCase extends DAO implements IDAOCriminalCase {
    @Override
    public boolean addCriminalCase(CriminalCase criminalCase) {
        if (criminalCase == null) return false;

        PreparedStatement preparedStatement = currConnection.prepareStatement("INSERT INTO `criminal_case`(`criminal_case_number`, `create_date`, `close_date`, `closed`, `detective_id`) VALUES (?,?,?,?,?)");
        try {
            preparedStatement.setString(1, criminalCase.getCriminalCaseNumber());
            preparedStatement.setDate(2, Date.valueOf(criminalCase.getCreateDate()));
            if (criminalCase.getCloseDate() == null)
                preparedStatement.setNull(3, 0);
            else
                preparedStatement.setDate(3, Date.valueOf(criminalCase.getCloseDate()));
            preparedStatement.setBoolean(4, criminalCase.isClosed());
            preparedStatement.setLong(5, criminalCase.getDetectiveId());
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return false;
        }
        criminalCase.setCriminalCaseId(currConnection.getLastAddedId(preparedStatement));
        return currConnection.queryDataEdit(preparedStatement);
    }

    @Override
    public CriminalCase getCriminalCaseById(long id) {
        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM `criminal_case` WHERE `criminal_case_id` = ?");

        try {
            preparedStatement.setLong(1, id);
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return null;
        }

        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

        if (retArray.isEmpty()) return null;

        CriminalCase retCriminalCase = new CriminalCase();
        retCriminalCase.setCriminalCaseId(id);

        ProjectFunctions.tryFillObjectByDbArray(retCriminalCase, retArray.get(0));
        return retCriminalCase;
    }
//TODO
    @Override
    public boolean updateCriminalCase(CriminalCase criminalCaseToUpdate) {
        return false;
    }

    @Override
    public CriminalCase getCriminalCaseWithDetective(long caseID) {
        return null;
    }

    @Override
    public List<CriminalCase> getAllCrimes() {
        return null;
    }

    @Override
    public List<CriminalCase> getAllClosedSolvedCrimes() {
        return null;
    }

    @Override
    public List<CriminalCase> getAllClosedUnsolvedCrimes() {
        return null;
    }

    @Override
    public List<CriminalCase> getAllOpenCrimes() {
        return null;
    }

    @Override
    public List<CriminalCase> getAllCrimesOfDetective(long detectiveID) {
        return null;
    }
}
