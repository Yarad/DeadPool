package com.DAO.interfaces;

import java.util.List;

import com.logic.Man;

public interface IDAOMan {
   boolean addMan(Man manToAdd);
   boolean updateMan(Man manToUpdate);
   
   /*
    * Получить личные данных людей, которые указаны в преступлениях
    * SELECT DISTINCT `man_id`, `name`, `surname`, `photo_path`
		FROM `man`
		JOIN `participant` USING (`man_id`)
    */
   List<Man> getAllManWhoTookParticipantInCrimes();
   
   /*
    * Личные данные человека по Id
    */
   Man getFullManInfo(long manId);
}
