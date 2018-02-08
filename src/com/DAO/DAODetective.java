package com.DAO;

import com.DAO.interfaces.IDAODetective;
import com.logic.Detective;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class DAODetective extends DAOMan implements IDAODetective {

    public Detective getDetectiveById(int id) {

        Detective retDetectiveRecord = new Detective();

        boolean b1 = fillInfoFromManTableById(id, retDetectiveRecord);

        if (b1)
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
            preparedStatement.setInt(3, detectiveToAdd.getManId());
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return false;
        }

        return currConnection.queryDataEdit(preparedStatement);
    }

    private boolean fillInfoFromDetectiveTableById(int id, Detective objectToFill) {

        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM `detective` WHERE `man_id` = ?");
        try {
            preparedStatement.setInt(1, id);
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return false;
        }

        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

        if (retArray.isEmpty()) return false;

        if (retArray.get(0).containsKey("login"))
            objectToFill.setLogin(retArray.get(0).get("login").toString());
        if (retArray.get(0).containsKey("password_hash"))
            objectToFill.setPassword(retArray.get(0).get("password_hash").toString());

        return true;
    }
}
