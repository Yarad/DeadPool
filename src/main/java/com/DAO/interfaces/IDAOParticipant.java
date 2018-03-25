package com.DAO.interfaces;

import java.util.List;

import com.logic.Man;
import com.logic.Participant;

public interface IDAOParticipant  {
    boolean addParticipant(Participant participantToAdd);
    Participant getParticipantById(long manId, long crimeId);
    boolean updateParticipant(Participant participantToUpdate);
    
    /*
     * Все люди, связанные с преступлением, с указанием их статуса
     * SELECT `man`.`man_id`, `man`.`name`, `man`.`surname`, `participant`.`participant_status`, `participant`.`alibi`, `participant`.`witness_report` FROM `man` JOIN `participant` WHERE `participant`.`crime_id` = 2 AND `participant`.`man_id` = `man`.`man_id`
     */
    List<Participant> getAllParticipantsByCrime(long crimeId);
    
    /*
     * Все преступления, в которых указан человек, с указанием номера и статуса дела (Generate report by participant; Search crimes by participant)
     * SELECT `criminal_case`.`criminal_case_id`, `criminal_case`.`criminal_case_number`, `criminal_case`.`closed`, `crime`.`crime_id`, `crime`.`description`, `crime`.`crime_date`, `participant`.`participant_status`, `participant`.`alibi`, `participant`.`witness_report` FROM `participant`, `crime`, `criminal_case`  WHERE `participant`.`man_id` = 3 AND `participant`.`crime_id` = `crime`.`crime_id` AND `crime`.`criminal_case_id` = `criminal_case`.`criminal_case_id`
     */
    List<Participant> getAllParticipantsByMan(long manId);
}
