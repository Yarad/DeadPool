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
        super(user != null ? user.roles : null);

        this.token = token;
        this.user = user;
        if (user != null && user.roles != null) {
            setAuthenticated(true);
        } else {
            setAuthenticated(false);
        }

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
