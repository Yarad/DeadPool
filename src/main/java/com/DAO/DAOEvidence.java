package com.DAO;

import com.DAO.interfaces.IDAOEvidence;
import com.logic.Evidence;
import com.logic.ProjectFunctions;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class DAOEvidence extends DAO implements IDAOEvidence {

    @Override
    public Evidence getEvidenceById(long evidenceId) {
        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM evidence WHERE evidence_id  = ? ");
        try {
            preparedStatement.setLong(1, evidenceId);
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return null;
        }
        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

        if (retArray.isEmpty()) return null;
        Evidence retEvidenceRecord = new Evidence();
        ProjectFunctions.tryFillObjectByDbArray(retEvidenceRecord, retArray.get(0));
        return retEvidenceRecord;
    }

    //потестить
    @Override
    public List<Evidence> getAllEvidencesByCrime(long crimeId) {
        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM evidence_of_crime JOIN evidence USING(evidence_id ) WHERE crime_id = ?");
        List<Evidence> evidences = new ArrayList<Evidence>();
        try {
            preparedStatement.setLong(1, crimeId);
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return null;
        }
        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

        if (retArray.isEmpty()) return evidences;
        for (int i = 0; i < retArray.size(); i++) {
            Evidence retEvidenceRecord = new Evidence();
            ProjectFunctions.tryFillObjectByDbArray(retEvidenceRecord, retArray.get(0));
            evidences.add(retEvidenceRecord);
        }
        return evidences;
    }
}
