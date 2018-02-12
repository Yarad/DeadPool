package com.DAO;

import com.DAO.interfaces.IDAOEvidence;
import com.logic.Evidence;
import com.logic.EvidenceType;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class DAOEvidence extends DAO  implements IDAOEvidence {
    @Override
    public boolean addEvidence(Evidence evidenceToAdd) {
        int evidenceTypeId = getEvidenceTypeId(evidenceToAdd.getEvidenceType());

        PreparedStatement preparedStatement = currConnection.prepareStatement("INSERT INTO `evidence`(`name`, `description`, `evidence_type_id`) VALUES (?,?,?)");
        try {
            preparedStatement.setString(1, evidenceToAdd.getName());
            preparedStatement.setString(2,evidenceToAdd.getDescription());
            preparedStatement.setInt(3, evidenceTypeId);
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return false;
        }

        return currConnection.queryDataEdit(preparedStatement);
    }

    @Override
    public Evidence getEvidenceById(int id) {
        PreparedStatement preparedQuery = currConnection.prepareStatement("SELECT * FROM `evidence` WHERE `evidence_id` = ?");
        try{
            preparedQuery.setInt(1, id);
        }catch (SQLException e)
        {
            DAOLog.log(e.toString());
            return false;
        }


        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedQuery);

        if (retArray.isEmpty()) return false;

        //ProjectConstants.fillObjectFieldByArrayOfValues(objectToFill, retArray);
        if (retArray.get(0).containsKey("bithday") && (LocalDate) retArray.get(0).get("bithday") != null)
            objectToFill.setBirthDay((LocalDate) retArray.get(0).get("bithday"));

        if (retArray.get(0).containsKey("name") && retArray.get(0).get("name") != null)
            objectToFill.setName(retArray.get(0).get("name").toString());

        if (retArray.get(0).containsKey("surname") && retArray.get(0).get("surname") != null)
            objectToFill.setSurname(retArray.get(0).get("surname").toString());
        if (retArray.get(0).containsKey("home_address") && retArray.get(0).get("home_address") != null)
            objectToFill.setHomeAddress(retArray.get(0).get("home_address").toString());
    }

    private int getEvidenceTypeId(EvidenceType evidenceType) {
        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM `evidence` WHERE `name` = ?");
        try {
            preparedStatement.setString(1, evidenceType.toString());
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return -1;
        }

        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

        if (retArray.isEmpty()) return -1;

        if (retArray.get(0).containsKey("evidence_id"))
            return (int) retArray.get(0).get("evidence_id");

        return -1;
    }
}
