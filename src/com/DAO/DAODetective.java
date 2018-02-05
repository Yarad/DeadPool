package com.DAO;

import com.DAO.interfaces.IDAODetective;
import com.logic.Detective;

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
        boolean retValue;
        detectiveToAdd.setManId(currConnection.getLastAddedId());
        retValue = currConnection.queryDataEdit("INSERT INTO `detective`(`login`, `password_hash`, `man_id`) VALUES (" +
                "'" + detectiveToAdd.getLogin() + "'," +
                "'" + detectiveToAdd.getPassword() + "'," +
                "'" + detectiveToAdd.getManId() + "')") && addMan(detectiveToAdd);
        return retValue;
    }

    private boolean fillInfoFromDetectiveTableById(int id, Detective objectToFill) {
        List<HashMap<String, Object>> retArray = currConnection.queryFind("SELECT * FROM `detective` WHERE `man_id` = " + id);
        if (retArray.isEmpty()) return false;

        if (retArray.get(0).containsKey("login"))
            objectToFill.setLogin(retArray.get(0).get("login").toString());
        if (retArray.get(0).containsKey("password_hash"))
            objectToFill.setPassword(retArray.get(0).get("password_hash").toString());

        return true;
    }
}
