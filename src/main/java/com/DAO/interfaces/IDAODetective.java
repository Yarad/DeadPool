package com.DAO.interfaces;

import com.logic.Detective;
import com.logic.Man;

import java.util.List;

public interface IDAODetective {
    Detective getDetectiveById(long id);
    boolean addDetective(Detective detectiveToAdd);
    boolean updateDetective(Detective detectiveToUpdate);
    Detective getDetectiveByLogin(String login);
    boolean existDetectiveWithLogin(String login);
    List<Detective> getAllDetectives();
}
