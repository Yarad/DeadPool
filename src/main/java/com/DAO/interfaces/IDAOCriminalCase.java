package com.DAO.interfaces;

import java.util.List;

import com.logic.CriminalCase;

public interface IDAOCriminalCase {
    boolean addCriminalCase(CriminalCase criminalCase);
    CriminalCase getCriminalCaseById(long id);
    
    /*
     * По сути: обновить все остальные поля в БД у дела с указанным id
     */
    boolean updateCriminalCase(CriminalCase criminalCaseToUpdate);

    /*
    * Получить вообще все дела. Для вывода на экран в соотв. разделе сайта
    */
    List<CriminalCase> getAllCriminalCases();

    /*
     * Получить все дела закрытые и раскрытые (Generate report by status of criminal cases)
     * SELECT `criminal_case_id`, `criminal_case_number`, `create_date`, `close_date` FROM `criminal_case` WHERE `closed` = 1 AND `close_date` IS NOT NULL
     */
    List<CriminalCase> getAllClosedSolvedCrimes();

    /*
     * Получить все дела закрытые и не раскрытые (Generate report by status of criminal cases)
     * SELECT `criminal_case_id`, `criminal_case_number`, `create_date` FROM `criminal_case` WHERE `closed` = 1 AND `close_date` IS NULL
     * */
    List<CriminalCase> getAllClosedUnsolvedCrimes();

    /*
     * Получить все дела открытые (Generate report by status of criminal cases)
     * SELECT `criminal_case_id`, `criminal_case_number`, `create_date` FROM `criminal_case` WHERE `closed` = 0
     * */
    List<CriminalCase> getAllOpenCrimes();

    /*
     * Детектив: все расследуемые дела с указанием их номера, статуса, датами создания и завершения (Generate report by detective)
     * SELECT `criminal_case_id`, `criminal_case_number`, `create_date`, `close_date`, `closed` FROM `criminal_case` WHERE `detective_id` = 1
     */
    List<CriminalCase> getAllCrimesOfDetective(long detectiveID);
}
