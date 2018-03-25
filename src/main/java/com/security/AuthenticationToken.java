package com.security;

import com.logic.Detective;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class AuthenticationToken extends AbstractAuthenticationToken {
    private final String token;
    private final Detective user;

    public AuthenticationToken(String token) {
        super(null);

        this.token = token;
        this.user = null;
        setAuthenticated(false);
    }

    public AuthenticationToken(String token, Detective user) {
        super(user.roles);

        this.token = token;
        this.user = user;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return getToken();
    }

    @Override
    public Object getPrincipal() {
        return getUser();
    }

    public String getToken() {
        return token;
    }

    public Detective getUser() {
        return user;
    }
}
