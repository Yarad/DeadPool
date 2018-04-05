package com.DAO;

import com.DAO.interfaces.IDAOEvidence;
import com.logic.Evidence;
import com.logic.ProjectFunctions;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Repository
public class DAOEvidence extends DAO implements IDAOEvidence {
    static Logger log = Logger.getLogger(DAOEvidence.class.getName());

    @Override
    public Evidence getEvidenceById(long evidenceId) {
        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM evidence WHERE evidence_id  = ? ");
        try {
            preparedStatement.setLong(1, evidenceId);
        } catch (SQLException e) {
            log.error(e.toString());
            return null;
        }
        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

        if (retArray.isEmpty()) return null;
        Evidence retEvidenceRecord = new Evidence();
        ProjectFunctions.tryFillObjectByDbArray(retEvidenceRecord, retArray.get(0));
        return retEvidenceRecord;
    }

    @Override
    public boolean addEvidence(Evidence evidence) {
        if (evidence == null)
            return false;
        PreparedStatement preparedStatement = currConnection.prepareStatement("INSERT INTO `evidence`(`name`, `description`) VALUES (?,?)");
        try {
            preparedStatement.setString(1, evidence.getName());
            preparedStatement.setString(2, evidence.getDescription());
        } catch (SQLException e) {
            log.error(e.toString());
            return false;
        }

        boolean queryIsOk = currConnection.queryDataEdit(preparedStatement);
        if (queryIsOk) {
            evidence.setEvidenceId(currConnection.getLastAddedId(preparedStatement));
            return true;
        } else
            return false;
    }

    @Override
    public boolean updateEvidence(Evidence evidence) {
        if (evidence == null)
            return false;
        PreparedStatement preparedStatement = currConnection.prepareStatement("UPDATE `evidence` SET `name`=?,`description`=? WHERE `evidence_id` = ?");
        try {
            preparedStatement.setString(1, evidence.getName());
            preparedStatement.setString(2, evidence.getDescription());
            preparedStatement.setLong(3, evidence.getEvidenceId());
        } catch (SQLException e) {
            log.error(e.toString());
            return false;
        }

        return currConnection.queryDataEdit(preparedStatement);
    }
}
