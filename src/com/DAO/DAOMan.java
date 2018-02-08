package com.DAO;

import com.DAO.interfaces.IDAOMan;
import com.logic.Man;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class DAOMan extends DAO implements IDAOMan {
    public boolean addMan(Man manToAdd) {
        PreparedStatement preparedQuery = currConnection.prepareStatement("INSERT INTO `man`( `name`, `home_address`, `birthday`, `surname`) VALUES (?,?,?,?)");

        try {
            preparedQuery.setString(1, manToAdd.getName());
            preparedQuery.setString(2, manToAdd.getHomeAddress());
            preparedQuery.setDate(3, Date.valueOf(manToAdd.getBirthDay()));
            preparedQuery.setString(4, manToAdd.getSurname());
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return false;
        }

        boolean queryIsOk = currConnection.queryDataEdit(preparedQuery);
        if (queryIsOk) {
            manToAdd.setManId(currConnection.getLastAddedId(preparedQuery));
        }
        return queryIsOk;
    }

    protected boolean fillInfoFromManTableById(int id, Man objectToFill) {
        //List<HashMap<String, Object>> retArray = currConnection.queryFind("SELECT * FROM `Man` WHERE `man_id` = " + id);
        PreparedStatement preparedQuery = currConnection.prepareStatement("SELECT * FROM `Man` WHERE `man_id` = ?");
        try{
            preparedQuery.setInt(1,id);
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
        //LogicLog.log(list.toString());
        return true;
    }
}
