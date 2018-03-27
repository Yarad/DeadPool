package com.services;

import com.services.interfaces.ITokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService implements ITokenService {
    protected final Map<String, String> store = new HashMap<>();

    @Transactional
    @Override
    public String get(String token) {
        return store.get(token);
    }

    @Transactional
    @Override
    public boolean contains(String token) {
        return store.containsKey(token);
    }

    @Transactional
    @Override
    public void put(String token, String user) {
        store.put(token, user);
    }

    @Transactional
    @Override
    public String remove(String token) {
        return store.remove(token);
    }
}
