package com.services;

import com.services.interfaces.ITokenService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService implements ITokenService {
    protected final Map<String, String> store = new HashMap<>();

    @Override
    public String get(String token) {
        return store.get(token);
    }

    @Override
    public boolean contains(String token) {
        return store.containsKey(token);
    }

    @Override
    public void put(String token, String user) {
        store.put(token, user);
    }

    @Override
    public String remove(String token) {
        return store.remove(token);
    }
}
