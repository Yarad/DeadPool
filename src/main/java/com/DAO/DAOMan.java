package com.DAO;

import com.DAO.interfaces.IDAOMan;
import com.logic.Detective;
import com.logic.Man;
import com.logic.ProjectFunctions;
import com.mysql.jdbc.MysqlDataTruncation;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DAOMan extends DAO implements IDAOMan {
    public DAOMan() {
        setConnectionToUse(new SQLConnection());
    }

    public boolean addMan(Man manToAdd) {
        if (manToAdd == null)
            return false;

        PreparedStatement preparedQuery = currConnection.prepareStatement("INSERT INTO `man`( `name`, `home_address`, `birthday`, `surname`, `photo_path`) VALUES (?,?,?,?,?)");

        try {
            preparedQuery.setString(1, manToAdd.getName());
            preparedQuery.setString(2, manToAdd.getHomeAddress());
            if (manToAdd.getBirthDay() != null)
                preparedQuery.setDate(3, Date.valueOf(manToAdd.getBirthDay()));
            else
                preparedQuery.setNull(3, 0);
            preparedQuery.setString(4, manToAdd.getSurname());
            preparedQuery.setString(5, manToAdd.getPhotoPath());

        } catch (SQLException  e) {
            DAOLog.log(e.toString());
            return false;
        }

        boolean queryIsOk = currConnection.queryDataEdit(preparedQuery);
        if (queryIsOk) {
            manToAdd.setManId(currConnection.getLastAddedId(preparedQuery));
        }
        return queryIsOk;
    }

    public boolean updateMan(Man manToUpdate) {
        if (manToUpdate == null) return false;
        PreparedStatement preparedStatement2 = currConnection.prepareStatement("UPDATE `man` SET " +
                "`name`=?," +
                "`surname`=?," +
                "`birthday`=?," + //nullable
                "`home_address`=?, " + //nullable //TODO: быть внимательнее. Была пропущена запятая => неправильный синтаксис. Лекс
                "`photo_path`=? " + //nullable
                "WHERE `man_id` = ?");
        try {
            preparedStatement2.setString(1, manToUpdate.getName());
            preparedStatement2.setString(2, manToUpdate.getSurname());
            if (manToUpdate.getBirthDay() != null)
                preparedStatement2.setDate(3, Date.valueOf(manToUpdate.getBirthDay()));
            else
                preparedStatement2.setNull(3, 0);
            preparedStatement2.setString(4, manToUpdate.getHomeAddress());
            preparedStatement2.setString(5, manToUpdate.getPhotoPath());
            preparedStatement2.setLong(6, manToUpdate.getManId());
        } catch (SQLException  e) {
            DAOLog.log(e.toString());
            //TODO: обсудить возврат false. Мне кажется, так правильнее. Лекс
            return false;
        }

        return currConnection.queryDataEdit(preparedStatement2);
    }

    @Override
    public Man getFullManInfo(long manId) {
        Man man = new Man();
        if (fillInfoFromManTableById(manId, man))
            return man;
        else
            return null;
    }

    protected boolean fillInfoFromManTableById(long id, Man objectToFill) {
        //List<HashMap<String, Object>> retArray = currConnection.queryFind("SELECT * FROM `Man` WHERE `man_id` = " + id);
        PreparedStatement preparedQuery = currConnection.prepareStatement("SELECT * FROM `Man` WHERE `man_id` = ?");
        try {
            preparedQuery.setLong(1, id);
        } catch (SQLException  e) {
            DAOLog.log(e.toString());
            return false;
        }

        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedQuery);

        if (retArray.isEmpty()) return false;

        ProjectFunctions.tryFillObjectByDbArray(objectToFill, retArray.get(0));
        return true;
    }

    //TODO: реализовать!!!
    @Override
    public Map<Man, Long> getAllManWithCrimeAmount() {
        return new HashMap<>();
    }
}
