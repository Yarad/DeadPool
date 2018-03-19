package com.services.interfaces;

public interface IDetectiveService {
    boolean addDetective(long id, String login, String password, String email);
    boolean updateDetective(long id, String login, String password, String email);
}
