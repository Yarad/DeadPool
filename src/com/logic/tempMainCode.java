package com.logic;

import com.DAO.DAODetective;
import com.DAO.DAOParticipant;
import com.DAO.SQLConnection;

import java.time.LocalDate;

public class tempMainCode {
    public static void main(String[] args) {
        //создаём объект, благодаря которому будем доставать инфу о детективах
        DAODetective newDetectiveDao = new DAODetective();
        //устанавливаем способ соединения (это паттерн)
        newDetectiveDao.setConnectionToUse(new SQLConnection());

        Detective myDetective = newDetectiveDao.getDetectiveById(1);

        Detective newDetective = new Detective();
        newDetective.setName("Andrew");
        newDetective.setSurname("Zhlobich");
        newDetective.setLogin("login");
        newDetective.setPassword("password");
        newDetective.setBirthDay(LocalDate.now());
        newDetective.setHomeAddress("Osipovichi");

        newDetectiveDao.addDetective(newDetective);
    }
}
