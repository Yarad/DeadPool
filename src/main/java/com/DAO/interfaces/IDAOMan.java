package com.DAO.interfaces;

import java.util.List;
import java.util.Map;

import com.logic.Man;

public interface IDAOMan {
   boolean addMan(Man manToAdd);
   boolean updateMan(Man manToUpdate);

   /*
    * Личные данные человека по Id
    */
   Man getFullManInfo(long manId);

   /*
   * Получить всех людей Man в БД, указать для каждого кол-во записей в табл. Participant
    */
   Map<Man,Long> getAllManWithCrimeAmount();
}
