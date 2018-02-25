package com.DAO;

import com.DAO.interfaces.IDAODetective;
import com.logic.Detective;
import com.logic.ProjectFunctions;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class DAODetective extends DAOMan implements IDAODetective {

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

        return currConnection.queryDataEdit(preparedStatement);
    }

    //TODO
    @Override
    public boolean updateDetective(Detective detectiveToUpdate) {
        return false;
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

        ProjectFunctions.tryFillObjectByDbArray(objectToFill,retArray.get(0));
        /*
        //if (retArray.get(0).containsKey("login"))
        if (ProjectFunctions.ifDbObjectContainsKey(retArray.get(0),"login"))
            objectToFill.setLogin(retArray.get(0).get("login").toString());
        //if (retArray.get(0).containsKey("password_hash"))
        if (ProjectFunctions.ifDbObjectContainsKey(retArray.get(0),"password_hash"))
            objectToFill.setPassword(retArray.get(0).get("password_hash").toString());
*/
        return true;
    }
}
