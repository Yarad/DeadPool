package com.DAO.interfaces;

import com.logic.Crime;

import java.time.LocalDate;
import java.util.List;

public interface IDAOCrime {
    boolean addCrime(Crime crimeToAdd);

    Crime getCrimeById(long crimeId);

    /*
     * По сути: обновить все остальные поля в БД у преступления с указанным id
     */
    boolean updateCrime(Crime crimeToUpdate);

    /*
     * Получить вообще все преступления. Для вывода на экран в соотв. разделе сайта
     */
    List<Crime> getAllCrimes();

    /* SELECT `criminal_case`.`criminal_case_id`, `criminal_case`.`criminal_case_number`, `criminal_case`.`closed`, `crime`.`crime_id`, `crime`.`crime_date`, `crime`.`crime_time`, `crime`.`description`, `crime`.`crime_place` FROM `crime`, `criminal_case` WHERE `crime`.`crime_date` BETWEEN '2012-02-01' AND '2018-02-07' AND `crime`.`criminal_case_id`  = `criminal_case`.`criminal_case_id`
     * */
    List<Crime> getCrimesBetweenDates(LocalDate dateStart, LocalDate dateEnd);

    /*
     * Общая информация о преступлении: дело, к которому относится (с указанием статуса дела), дата+время+место преступления, описание самого преступления
     * SELECT `criminal_case`.`criminal_case_id`, `criminal_case`.`criminal_case_number`, `criminal_case`.`closed`, `crime`.`description`, `crime`.`crime_date`, `crime`.`crime_time`, `crime`.`crime_place` FROM `crime`, `criminal_case` WHERE `crime`.`crime_id` = 2 AND `criminal_case`.`criminal_case_id` = `crime`.`criminal_case_id` 
     */
    Crime getCrimeWithCriminalCase(long crimeId);

    /*
     * Все преступления, входящие в состав дела, с указанием описания, дата+время+место преступления
     * SELECT `crime`.`crime_id`, `crime`.`description`, `crime`.`crime_date`, `crime`.`crime_time`, `crime`.`crime_place` FROM `crime` WHERE `crime`.`criminal_case_id` = 2
     */
    List<Crime> getCrimesByCriminalCase(long caseId);
}
