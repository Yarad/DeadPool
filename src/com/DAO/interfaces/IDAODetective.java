package com.DAO.interfaces;

import com.logic.Detective;

public interface IDAODetective {
    Detective getDetectiveById(int id);
    boolean addDetective(Detective detectiveToAdd);
    boolean updateDetective(Detective detectiveToUpdate);
}
