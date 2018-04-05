package com.DAO;

import com.DAO.interfaces.IDAOEvidenceOfCrime;
import com.logic.EvidenceOfCrime;
import com.logic.ProjectFunctions;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class DAOEvidenceOfCrime extends DAO implements IDAOEvidenceOfCrime {

    //TODO: потестить
    @Override
    public EvidenceOfCrime getEvidenceOfCrime(long crimeId, long evidenceId) {
        EvidenceOfCrime evidenceOfCrime = new EvidenceOfCrime();


        /*
        не нужно. при вызове evidenceOfCrime.getParentCrime() всё автоматом подгружается

        evidenceOfCrime.parentCrime = parentDaoCrime.getCrimeById(crimeId);
        evidenceOfCrime.parentEvidence = parentDaoEvidence.getEvidenceById(evidenceId);

        */

        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM evidence_of_crime WHERE crime_id = ? AND evidence_id  = ? ");

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

    //FIXED in prev commit надо заполнять parentEvidence & EvidenceType
    @Override
    public List<EvidenceOfCrime> getAllEvidencesOfCrime() {
        List<EvidenceOfCrime> retArr = new ArrayList<>();
        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM evidence_of_crime");

        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

        if (retArray.isEmpty()) return retArr;

        for (int i = 0; i < retArray.size(); i++) {
            EvidenceOfCrime tempObj = new EvidenceOfCrime();
            ProjectFunctions.tryFillObjectByDbArray(tempObj, retArray.get(i));

            retArr.add(tempObj);
        }
        return retArr;
    }


    public List<EvidenceOfCrime> getAllEvidencesOfCrimeByCrimeId(long crimeId) {
        List<EvidenceOfCrime> retArr = new ArrayList<>();
        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM evidence_of_crime WHERE crime_id = ?");

        try {
            preparedStatement.setLong(1, crimeId);
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return retArr;
        }
        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

        if (retArray.isEmpty()) return retArr;


        for (int i = 0; i < retArray.size(); i++) {
            EvidenceOfCrime tempObj = new EvidenceOfCrime();
            ProjectFunctions.tryFillObjectByDbArray(tempObj, retArray.get(i));
            retArr.add(tempObj);
        }
        return retArr;
    }

    @Override
    public List<EvidenceOfCrime> getAllEvidencesOfCrimeByEvidenceId(long evidenceId) {
        List<EvidenceOfCrime> retArr = new ArrayList<>();
        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM evidence_of_crime WHERE evidence_id = ?");

        try {
            preparedStatement.setLong(1, evidenceId);
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return retArr;
        }
        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

        if (retArray.isEmpty()) return retArr;


        for (int i = 0; i < retArray.size(); i++) {
            EvidenceOfCrime tempObj = new EvidenceOfCrime();
            ProjectFunctions.tryFillObjectByDbArray(tempObj, retArray.get(i));
            retArr.add(tempObj);
        }
        return retArr;
    }

    @Override
    public boolean addEvidenceOfCrime(EvidenceOfCrime evidenceOfCrime) {
        //в объекте должны быть установлены либо id crime&evidence, либо
        //в лоб прям ссылки на эти объекты (тогда id автоматом становятся такими же)

        if (evidenceOfCrime == null)
            return false;

        PreparedStatement preparedStatement = currConnection.prepareStatement("INSERT INTO `evidence_of_crime`(`evidence_id`, `crime_id`, `evidence_type`, `date_added`, `details`, `photo_path`) VALUES (?,?,?,?,?,?)");
        try {
            preparedStatement.setLong(1, evidenceOfCrime.getEvidenceId());
            preparedStatement.setLong(2, evidenceOfCrime.getCrimeId());
            preparedStatement.setString(3, evidenceOfCrime.getEvidenceType().toString());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(evidenceOfCrime.getDateAdded()));
            preparedStatement.setString(5, evidenceOfCrime.getDetails());
            preparedStatement.setString(6, evidenceOfCrime.getPhotoPath());
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return false;
        }

        return currConnection.queryDataEdit(preparedStatement);
    }

    @Override
    public boolean updateEvidenceOfCrime(EvidenceOfCrime evidenceOfCrime) {
        return false;
    }
}

