package com.DAO;

import com.DAO.interfaces.IDAOMan;
import com.logic.Man;
import com.logic.ProjectFunctions;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Repository
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

    public boolean updateMan(Man manToUpdate) {
        if (manToUpdate == null) return false;
        PreparedStatement preparedStatement2 = currConnection.prepareStatement("UPDATE `man` SET " +
                "`name`=?," +
                "`surname`=?," +
                "`birthday`=?," + //nullable
                "`home_address`=? " + //nullable
                "WHERE man_id = ?");
        try {

            preparedStatement2.setString(1, manToUpdate.getName());
            preparedStatement2.setString(2, manToUpdate.getSurname());

            if (manToUpdate.getBirthDay() != null)
                preparedStatement2.setDate(3, Date.valueOf(manToUpdate.getBirthDay()));
            else
                preparedStatement2.setNull(3, 0);

            if (manToUpdate.getHomeAddress() != null)
                preparedStatement2.setString(4, manToUpdate.getHomeAddress());
            else
                preparedStatement2.setNull(4, 0);

            preparedStatement2.setLong(5, manToUpdate.getManId());
        } catch (Exception e) {
            DAOLog.log(e.toString());
        }

        return currConnection.queryDataEdit(preparedStatement2);
    }

    protected boolean fillInfoFromManTableById(long id, Man objectToFill) {
        //List<HashMap<String, Object>> retArray = currConnection.queryFind("SELECT * FROM `Man` WHERE `man_id` = " + id);
        PreparedStatement preparedQuery = currConnection.prepareStatement("SELECT * FROM `Man` WHERE `man_id` = ?");
        try {
            preparedQuery.setLong(1, id);
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return false;
        }

        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedQuery);

        if (retArray.isEmpty()) return false;

        ProjectFunctions.tryFillObjectByDbArray(objectToFill, retArray.get(0));
        //ProjectConstants.fillObjectFieldByArrayOfValues(objectToFill, retArray);
        //if (retArray.get(0).containsKey("bithday") && (LocalDate) retArray.get(0).get("bithday") != null)
        /*
        if (ProjectFunctions.ifDbObjectContainsKey(retArray.get(0), "birthday"))
            objectToFill.setBirthDay((LocalDate) retArray.get(0).get("bithday"));

        //if (retArray.get(0).containsKey("name") && retArray.get(0).get("name") != null)
        if (ProjectFunctions.ifDbObjectContainsKey(retArray.get(0), "name"))
            objectToFill.setName(retArray.get(0).get("name").toString());

        //if (retArray.get(0).containsKey("surname") && retArray.get(0).get("surname") != null)
        if (ProjectFunctions.ifDbObjectContainsKey(retArray.get(0), "surname"))
            objectToFill.setSurname(retArray.get(0).get("surname").toString());

        //if (retArray.get(0).containsKey("home_address") && retArray.get(0).get("home_address") != null)
        if (ProjectFunctions.ifDbObjectContainsKey(retArray.get(0), "home_address"))
            objectToFill.setHomeAddress(retArray.get(0).get("home_address").toString());
        //LogicLog.log(list.toString());
        */
        return true;
    }

    // TODO Надо реализовать
	@Override
	public List<Man> getAllManWhoTookParticipantInCrimes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Man getFullManInfo(long manId) {
		// TODO Auto-generated method stub
		return null;
	}
}
