package com.DAO;

import com.DAO.interfaces.IDAOEvidenceOfCrime;
import com.logic.Evidence;
import com.logic.EvidenceOfCrime;
import com.logic.ProjectFunctions;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class DAOEvidenceOfCrime extends DAO implements IDAOEvidenceOfCrime {
    private DAOCrime parentDaoCrime;
    private DAOEvidence parentDaoEvidence;

    public DAOEvidenceOfCrime() {
        setConnectionToUse(new SQLConnection());

        parentDaoCrime = new DAOCrime();
        parentDaoEvidence = new DAOEvidence();

        parentDaoEvidence.setConnectionToUse(currConnection);
        parentDaoCrime.setConnectionToUse(currConnection);
    }


    //TODO: потестить
    @Override
    public EvidenceOfCrime getEvidenceOfCrime(long crimeId, long evidenceId) {
        EvidenceOfCrime evidenceOfCrime = new EvidenceOfCrime();

        evidenceOfCrime.parentCrime = parentDaoCrime.getCrimeById(crimeId);
        evidenceOfCrime.parentEvidence = parentDaoEvidence.getEvidenceById(evidenceId);

        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM evidence_of_crime WHERE crime_id = ? AND evidence_id  = ? ");
        //PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM `participant` WHERE `crime_id` = ? AND `man_id` = ?");

        try {
            preparedStatement.setLong(1, crimeId);
            preparedStatement.setLong(2, evidenceId);
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return null;
        }
        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

        if (retArray.isEmpty()) return null;

        ProjectFunctions.tryFillObjectByDbArray(evidenceOfCrime, retArray.get(0));
        return evidenceOfCrime;
    }

	@Override
	public List<EvidenceOfCrime> getAllEvidencesOfCrime() {
		// TODO Реализовать
		return new ArrayList<>();
	}

	@Override
	public List<EvidenceOfCrime> getAllEvidencesOfCrimeByCrimeId(long crimeId) {
		// TODO Реализовать
		return new ArrayList<>();
	}

	@Override
	public List<EvidenceOfCrime> getAllEvidencesOfCrimeByEvidenceId(long evidenceId) {
		// TODO Реализовать
		return new ArrayList<>();
	}

    @Override
    public boolean addEvidenceOfCrime(EvidenceOfCrime evidenceOfCrime) {
        return false;
    }

    @Override
    public boolean updateEvidenceOfCrime(EvidenceOfCrime evidenceOfCrime) {
        return false;
    }
}
