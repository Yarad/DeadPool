package com.DAO;

import com.logic.Detective;

public class DAODetective extends DAOMan {
    public Detective getDetectiveById(int id)
    {
        getManById(id);
        return new Detective();
    }
}
