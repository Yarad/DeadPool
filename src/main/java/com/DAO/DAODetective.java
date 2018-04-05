package com.DAO;

import com.DAO.interfaces.IDAODetective;
import com.logic.Detective;
import com.logic.Man;
import com.logic.Participant;
import com.logic.ProjectFunctions;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class DAODetective extends DAOMan implements IDAODetective {

    public Detective getDetectiveById(long id) {
        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM detective JOIN man ON detective_id = man_id WHERE detective_id = ?");

        try {
            preparedStatement.setLong(1, id);
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return null;
        }

        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

        if (retArray.isEmpty())
            return null;

        Detective detective = new Detective();
        ProjectFunctions.tryFillObjectByDbArray(detective, retArray.get(0));
        return detective;
    }

    public boolean addDetective(Detective detectiveToAdd) {
        if (!addMan(detectiveToAdd))
            return false;

        PreparedStatement preparedStatement = currConnection.prepareStatement("INSERT INTO `detective`(`login`, `hash_of_password`, `detective_id`, `email`) VALUES (?,?,?,?)");
        try {
            preparedStatement.setString(1, detectiveToAdd.getLogin());
            preparedStatement.setString(2, detectiveToAdd.getHashOfPassword());
            preparedStatement.setLong(3, detectiveToAdd.getManId());
            preparedStatement.setString(4, detectiveToAdd.getEmail());
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return false;
        }

        return currConnection.queryDataEdit(preparedStatement);
    }

    @Override
    public boolean updateDetective(Detective detectiveToUpdate) {
        if (detectiveToUpdate == null)
            return false;
        PreparedStatement preparedStatement = currConnection.prepareStatement("UPDATE `detective` SET `login`=?,`hash_of_password`=?,`email`=? WHERE `detective_id` = ?");
        try {
            preparedStatement.setString(1, detectiveToUpdate.getLogin());
            preparedStatement.setString(2, detectiveToUpdate.getHashOfPassword());
            preparedStatement.setString(3, detectiveToUpdate.getEmail());
            preparedStatement.setLong(4, detectiveToUpdate.getManId());
        } catch (Exception e) {
            DAOLog.log(e.toString());
        }
        boolean res1 = currConnection.queryDataEdit(preparedStatement);
        boolean res2 = updateMan(detectiveToUpdate);
        return res1 && res2;
    }

    @Override
    public Detective getDetectiveByLogin(String login) {
        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM detective JOIN man ON detective_id = man_id WHERE `login` = ?");
        try {
            preparedStatement.setString(1, login);
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return null;
        }

        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

        if (retArray.isEmpty()) return null;

        Detective detective = new Detective();
        ProjectFunctions.tryFillObjectByDbArray(detective, retArray.get(0));
        return detective;
    }

    @Override
    public boolean existDetectiveWithLogin(String login) {
        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM `detective` WHERE `login` = ?");
        try {
            preparedStatement.setString(1, login);
        } catch (SQLException e) {
            DAOLog.log(e.toString());
            return false;
        }

        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

        if (!retArray.isEmpty() && retArray.size() == 1)
            return true;

        return false;
    }

    @Override
    public List<Man> getAllDetectives() {
        //Все люди, которым есть эквивалент в таблице `Detective`
        PreparedStatement preparedStatement = currConnection.prepareStatement("SELECT * FROM detective JOIN man ON detective_id = man_id");

        List<HashMap<String, Object>> retArray = currConnection.queryFind(preparedStatement);

        List<Man> manList = new ArrayList<>();

        if (retArray.isEmpty()) return manList;

        for (int i = 0; i < retArray.size(); i++) {
            Detective tempDetective = new Detective();
            ProjectFunctions.tryFillObjectByDbArray(tempDetective, retArray.get(i));
            manList.add(tempDetective);
        }

        return manList;
    }
}