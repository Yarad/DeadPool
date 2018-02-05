package com.DAO;

import com.logic.Detective;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class DAODetective extends DAOMan {

    public Detective getDetectiveById(int id) {

        Detective retDetectiveRecord = new Detective();

        fillInfoFromManTableById(id, retDetectiveRecord);
        fillInfoFromDetectiveTableById(id, retDetectiveRecord);

        return retDetectiveRecord;
    }

    public boolean addDetective(Detective detectiveToAdd) {
        boolean retValue = addMan(detectiveToAdd);
        detectiveToAdd.setManId(currConnection.getLastAddedId());
        retValue = currConnection.queryDataEdit("INSERT INTO `detective`(`login`, `password_hash`, `man_id`) VALUES (" +
                "'" + detectiveToAdd.getLogin() + "'," +
                "'" + detectiveToAdd.getPassword() + "'," +
                "'" + detectiveToAdd.getManId() + "')") && retValue;
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
