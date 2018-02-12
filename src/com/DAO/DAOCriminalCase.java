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
            preparedStatement.setInt(5, criminalCase.getDetectiveId());
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return false;
        }
        criminalCase.setCriminalCaseId(currConnection.getLastAddedId(preparedStatement));
        return currConnection.queryDataEdit(preparedStatement);
    }

    @Override
    public CriminalCase getCriminalCaseById(int id) {
        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM `criminal_case` WHERE `criminal_case_id` = ?");

        try {
            preparedStatement.setInt(1, id);
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return null;
        }

        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

        if (retArray.isEmpty()) return null;

        CriminalCase retCriminalCase = new CriminalCase();
        retCriminalCase.setCriminalCaseId(id);

        if (ProjectFunctions.ifDbObjectContainsKey(retArray.get(0), "detective_id"))
            retCriminalCase.setDetectiveId(Integer.parseInt(retArray.get(0).get("detective_id").toString()));

        if (ProjectFunctions.ifDbObjectContainsKey(retArray.get(0), "criminal_case_number"))
            retCriminalCase.setCriminalCaseNumber(retArray.get(0).get("criminal_case_number").toString());

        if (ProjectFunctions.ifDbObjectContainsKey(retArray.get(0), "create_date"))
            retCriminalCase.setCreateDate(((Date) retArray.get(0).get("create_date")).toLocalDate());

        if (ProjectFunctions.ifDbObjectContainsKey(retArray.get(0), "close_date"))
            retCriminalCase.setCreateDate(((Date) retArray.get(0).get("close_date")).toLocalDate());

        if (ProjectFunctions.ifDbObjectContainsKey(retArray.get(0), "closed"))
            retCriminalCase.setClosed(Boolean.valueOf(retArray.get(0).get("closed").toString()));
        return retCriminalCase;
    }
}
