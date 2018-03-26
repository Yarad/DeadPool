package com.security;

import com.logic.Detective;
import com.services.interfaces.IDetectiveService;
import com.services.interfaces.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

//Проверка токена
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    ITokenService tokenStore;

    @Autowired
    IDetectiveService detectiveService;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        final AuthenticationToken tokenContainer = (AuthenticationToken) auth;
        final String token = tokenContainer.getToken();

        if (!tokenStore.contains(token)) {
            throw new BadCredentialsException("Invalid token - " + token);
        }

        final String username = tokenStore.get(token);
        if (!detectiveService.existDetectiveWithLogin(username)) {
            //на всякий случай. Никогда не должн произойти
            throw new BadCredentialsException("No user found for token - " + token);
        }

        final Detective user = detectiveService.getDetectiveByLogin(username);

        return new AuthenticationToken(token, user);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AuthenticationToken.class.isAssignableFrom(authentication);
    }
}
