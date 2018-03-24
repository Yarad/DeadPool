package com.services.interfaces;

public interface ITokenService {
    String get(String token);
    boolean contains(String token);
    void put(String token, String user);
    String remove(String token);
}
