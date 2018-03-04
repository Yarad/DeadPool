package com.DAO.interfaces;

import com.logic.Detective;

public interface IDAODetective {
    Detective getDetectiveById(long id);
    boolean addDetective(Detective detectiveToAdd);
    boolean updateDetective(Detective detectiveToUpdate);
}