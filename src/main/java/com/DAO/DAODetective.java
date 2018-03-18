package com.DAO;

import com.DAO.interfaces.IDAODetective;
import com.logic.Detective;
import com.logic.ProjectFunctions;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Repository
public class DAODetective extends DAOMan implements IDAODetective {
    public DAODetective() {
        setConnectionToUse(new SQLConnection());
    }

    public Detective getDetectiveById(long id) {

        Detective retDetectiveRecord = new Detective();
        retDetectiveRecord.setManId(id);

        boolean b1 = fillInfoFromManTableById(id, retDetectiveRecord);

        b1 = b1 && fillInfoFromDetectiveTableById(id, retDetectiveRecord);

        if (b1)
            return retDetectiveRecord;
        else
            return null;
    }

    public boolean addDetective(Detective detectiveToAdd) {
        if (!addMan(detectiveToAdd))
            return false;

        PreparedStatement preparedStatement = currConnection.prepareStatement("INSERT INTO `detective`(`login`, `password_hash`, `man_id`) VALUES (?,?,?)");
        try {
            preparedStatement.setString(1, detectiveToAdd.getLogin());
            preparedStatement.setString(2, detectiveToAdd.getPassword());
            preparedStatement.setLong(3, detectiveToAdd.getManId());
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return false;
        }
        //TODO
        return currConnection.queryDataEdit(preparedStatement);
    }

    @Override
    public boolean updateDetective(Detective detectiveToUpdate) {
        if (detectiveToUpdate == null) return false;
        PreparedStatement preparedStatement = currConnection.prepareStatement("UPDATE `detective` SET `login`=?,`hash_of_password`=? WHERE `detective_id` = ?");
        try {
            preparedStatement.setString(1, detectiveToUpdate.getLogin());
            preparedStatement.setString(2, detectiveToUpdate.getPassword());
            preparedStatement.setLong(3, detectiveToUpdate.getManId());
        } catch (Exception e) {
            DAOLog.log(e.toString());
        }
        boolean res1 = currConnection.queryDataEdit(preparedStatement);
        boolean res2 = updateMan(detectiveToUpdate);
        return res1 && res2;
    }

    private boolean fillInfoFromDetectiveTableById(long id, Detective objectToFill) {

        //TODO Join
        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM `detective` WHERE `detective_id` = ?");
        try {
            preparedStatement.setLong(1, id);
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return false;
        }

        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

        if (retArray.isEmpty()) return false;

        ProjectFunctions.tryFillObjectByDbArray(objectToFill, retArray.get(0));
        return true;
    }
}
