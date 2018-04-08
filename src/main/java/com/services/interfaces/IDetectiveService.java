package com.services.interfaces;

import com.DTO.AddResult;
import com.logic.Detective;
import com.logic.Man;

import java.time.LocalDate;
import java.util.List;

public interface IDetectiveService {
    AddResult addDetective(String name, String surname, LocalDate birthday, String homeAddress, String photoPath,
                           String login, String password, String email);
    boolean updateDetective(long id, String name, String surname, LocalDate birthday, String homeAddress, String photoPath,
                            String login, String password, String email);
    Detective getDetectiveByLogin(String login);
    Detective getDetectiveById(long id);
    boolean existDetectiveWithLogin(String login);
    List<Man> getAllDetectives();
}
