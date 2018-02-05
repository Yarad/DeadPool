package com.DAO;

import com.DAO.interfaces.IDAOMan;
import com.logic.Man;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class DAOMan extends DAO implements IDAOMan{
    public boolean addMan(Man manToAdd) {
        return currConnection.queryDataEdit("INSERT INTO `man`( `name`, `home_address`, `birthday`, `surname`) VALUES (" +
                "'" + manToAdd.getName() + "'," +
                "'" + manToAdd.getHomeAddress() + "'," +
                "'" + Date.valueOf(manToAdd.getBirthDay()) + "'," +
                "'" + manToAdd.getSurname() + "')");
    }

    protected boolean fillInfoFromManTableById(int id, Man objectToFill) {
        List<HashMap<String, Object>> retArray = currConnection.queryFind("SELECT * FROM `Man` WHERE `man_id` = " + id);

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
        //LogicLog.log(list.toString());
        return true;
    }
}
