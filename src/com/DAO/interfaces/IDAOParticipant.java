package com.DAO.interfaces;

import java.util.List;

import com.logic.Participant;

public interface IDAOParticipant  {
    boolean addParticipant(Participant participantToAdd);
<<<<<<< HEAD
    Participant getParticipantById(int manId, int crimeId);
=======
    Participant getParticipantById(long manId, long crimeId);
>>>>>>> 8e75430233230b0e9ad7c6b17f7fc7b48600b908
    boolean updateParticipant(Participant participantToUpdate);
    
    /*
     * Все люди, связанные с преступлением, с указанием их статуса
     * SELECT `man`.`man_id`, `man`.`name`, `man`.`surname`, `participant`.`participant_status`, `participant`.`alibi`, `participant`.`witness_report` FROM `man` JOIN `participant` WHERE `participant`.`crime_id` = 2 AND `participant`.`man_id` = `man`.`man_id`
     */
<<<<<<< HEAD
    List<Participant> getAllParticipantsByCrime(int crimeId);
=======
    List<Participant> getAllParticipantsByCrime(long crimeId);
>>>>>>> 8e75430233230b0e9ad7c6b17f7fc7b48600b908
    
    /*
     * Все преступления, в которых указан человек, с указанием номера и статуса дела (Generate report by participant; Search crimes by participant)
     * SELECT `criminal_case`.`criminal_case_id`, `criminal_case`.`criminal_case_number`, `criminal_case`.`closed`, `crime`.`crime_id`, `crime`.`description`, `crime`.`crime_date`, `participant`.`participant_status`, `participant`.`alibi`, `participant`.`witness_report` FROM `participant`, `crime`, `criminal_case`  WHERE `participant`.`man_id` = 3 AND `participant`.`crime_id` = `crime`.`crime_id` AND `crime`.`criminal_case_id` = `criminal_case`.`criminal_case_id`
     */
<<<<<<< HEAD
    List<Participant> getAllParticipantsByMan(int manId);
=======
    List<Participant> getAllParticipantsByMan(long manId);
>>>>>>> 8e75430233230b0e9ad7c6b17f7fc7b48600b908
}
