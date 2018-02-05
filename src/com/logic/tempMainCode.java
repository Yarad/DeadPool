package com.logic;

import com.DAO.DAODetective;
import com.DAO.SQLConnection;

public class tempMainCode {
    public static void main(String[] args) {
        //создаём объект, благодаря которому будем доставать инфу о детективах
        DAODetective newDetectiveDao = new DAODetective();
        //устанавливаем способ соединения (это паттерн)
        newDetectiveDao.setConnectionToUse(new SQLConnection());
        Detective myDetective = newDetectiveDao.getDetectiveById(1);

    }
}
