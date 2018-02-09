package com.DAO;

import com.DAO.interfaces.IDAOCriminalCase;
import com.logic.CriminalCase;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

        return currConnection.queryDataEdit(preparedStatement);
    }
}
