package com.DAO;

import com.DAO.interfaces.IDAOCriminalCase;
import com.logic.CriminalCase;
import com.logic.ProjectFunctions;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
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

    @Override
    public boolean updateCriminalCase(CriminalCase criminalCaseToUpdate) {
        if (criminalCaseToUpdate == null) return false;
        PreparedStatement preparedStatement = currConnection.prepareStatement("UPDATE `criminal_case` SET " +
                "`criminal_case_number`=?," +
                "`create_date`=?," +
                "`close_date`=?," + //nullable
                "`closed`=?" +
                " WHERE `criminal_case_id` = ?");
        try {
            preparedStatement.setString(1, criminalCaseToUpdate.getCriminalCaseNumber());
            preparedStatement.setDate(2, Date.valueOf(criminalCaseToUpdate.getCreateDate()));

            if (criminalCaseToUpdate.getCloseDate() != null)
                preparedStatement.setDate(3, Date.valueOf(criminalCaseToUpdate.getCloseDate()));
            else
                preparedStatement.setNull(3, 0);

            preparedStatement.setBoolean(4, criminalCaseToUpdate.isClosed());
            preparedStatement.setLong(5, criminalCaseToUpdate.getCriminalCaseId());
        } catch (Exception e) {
            DAOLog.log(e.toString());
        }
        return currConnection.queryDataEdit(preparedStatement);
    }


    //что?? Какой with detective? обсудить!!!TODO
    @Override
    public CriminalCase getCriminalCaseWithDetective(long caseID) {
        return null;
    }

    @Override
    public List<CriminalCase> getAllCriminalCases() {
        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM `criminal_case`");
        List<CriminalCase> criminalCases = new ArrayList<>();

        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

        if (retArray.isEmpty()) return criminalCases;

        for (int i = 0; i < retArray.size(); i++) {
            CriminalCase retCriminalCase = new CriminalCase();
            ProjectFunctions.tryFillObjectByDbArray(retCriminalCase, retArray.get(i));
            criminalCases.add(retCriminalCase);
            //пардон за названия
        }
        return criminalCases;
    }

    @Override
    public List<CriminalCase> getAllClosedSolvedCrimes() {
        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM `criminal_case` WHERE `closed` = 1");
        List<CriminalCase> criminalCases = new ArrayList<>();

        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

        if (retArray.isEmpty()) return criminalCases;

        for (int i = 0; i < retArray.size(); i++) {
            CriminalCase retCriminalCase = new CriminalCase();
            ProjectFunctions.tryFillObjectByDbArray(retCriminalCase, retArray.get(i));
            criminalCases.add(retCriminalCase);
            //пардон за названия
        }
        return criminalCases;
    }

    @Override
    public List<CriminalCase> getAllClosedUnsolvedCrimes() {
        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM `criminal_case` WHERE `closed` = 0 AND `close_date` IS NOT NULL ");
        List<CriminalCase> criminalCases = new ArrayList<>();

        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

        if (retArray.isEmpty()) return criminalCases;

        for (int i = 0; i < retArray.size(); i++) {
            CriminalCase retCriminalCase = new CriminalCase();
            ProjectFunctions.tryFillObjectByDbArray(retCriminalCase, retArray.get(i));
            criminalCases.add(retCriminalCase);
            //пардон за названия
        }
        return criminalCases;
    }

    @Override
    public List<CriminalCase> getAllOpenCrimes() {
        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM `criminal_case` WHERE `closed` = 0");
        List<CriminalCase> criminalCases = new ArrayList<>();

        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

        if (retArray.isEmpty()) return criminalCases;

        for (int i = 0; i < retArray.size(); i++) {
            CriminalCase retCriminalCase = new CriminalCase();
            ProjectFunctions.tryFillObjectByDbArray(retCriminalCase, retArray.get(i));
            criminalCases.add(retCriminalCase);
            //пардон за названия
        }
        return criminalCases;
    }

    @Override
    public List<CriminalCase> getAllCrimesOfDetective(long detectiveID) {

        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM `criminal_case` WHERE `detective_id` = ?");
        List<CriminalCase> criminalCases = new ArrayList<>();

        try{
            preparedStatement.setLong(1,detectiveID);
        } catch (Exception e)
        {
            DAOLog.log(e.toString());
            return criminalCases;
        }

        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

        if (retArray.isEmpty()) return criminalCases;

        for (int i = 0; i < retArray.size(); i++) {
            CriminalCase retCriminalCase = new CriminalCase();
            ProjectFunctions.tryFillObjectByDbArray(retCriminalCase, retArray.get(i));
            criminalCases.add(retCriminalCase);
            //пардон за названия
        }
        return criminalCases;
    }
}
