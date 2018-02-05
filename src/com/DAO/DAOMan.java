package com.DAO;

import com.logic.LogicLog;
import com.logic.Man;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DAOMan extends DAO {
    protected void getManById(int id)
    {
        List<HashMap<String, Object>> list = currConnection.query("SELECT * FROM `Man` WHERE `man_id` = " + id);
        LogicLog.log(list.toString());
    }
}
