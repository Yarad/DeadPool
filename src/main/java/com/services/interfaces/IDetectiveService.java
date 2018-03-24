package com.services.interfaces;

import com.logic.Detective;

public interface IDetectiveService {
    boolean addDetective(long id, String login, String password, String email);
    boolean updateDetective(long id, String login, String password, String email);
    Detective getDetectiveByLogin(String login);
    boolean existDetectiveWithLogin(String login);
}
