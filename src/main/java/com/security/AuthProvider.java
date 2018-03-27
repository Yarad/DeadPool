package com.security;

import com.DTO.TokenVerifyResult;
import com.logic.Detective;
import com.services.interfaces.IAuthorizationService;
import com.services.interfaces.IDetectiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

//Проверка токена
public class AuthProvider implements AuthenticationProvider {
    @Autowired
    private IAuthorizationService authorizationService;

    @Autowired
    private IDetectiveService detectiveService;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        final AuthenticationToken tokenContainer = (AuthenticationToken) auth;
        final String token = tokenContainer.getToken();

        TokenVerifyResult verifyResult = authorizationService.checkToken(token);
        if (!verifyResult.getIsCorrect()) {
            throw new BadCredentialsException("Invalid token - " + token);
        }

        if (!detectiveService.existDetectiveWithLogin(verifyResult.getUserName())) {
            //на всякий случай. Никогда не должн произойти
            throw new BadCredentialsException("No user found for token - " + token);
        }

        final Detective user = detectiveService.getDetectiveByLogin(verifyResult.getUserName());

        return new AuthenticationToken(token, user);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AuthenticationToken.class.isAssignableFrom(authentication);
    }
}
