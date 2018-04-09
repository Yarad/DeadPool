package com.security;

import com.DTO.TokenVerifyResult;
import com.logic.Detective;
import com.services.interfaces.IAuthorizationService;
import com.services.interfaces.IDetectiveService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

//Проверка токена
public class AuthProvider implements AuthenticationProvider {
    static Logger log = Logger.getLogger(AuthProvider.class.getName());

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
            log.error("Authorization token isn't correct.");
            throw new BadCredentialsException("Invalid token - " + token);
        }

        if (!detectiveService.existDetectiveWithLogin(verifyResult.getUserName())) {
            //на всякий случай. Никогда не должн произойти
            log.error("No user found for authorization token in request header.");
            throw new BadCredentialsException("No user found for token - " + token);
        }

        final Detective user = detectiveService.getDetectiveByLogin(verifyResult.getUserName());
        log.trace("Setting authentication user for correct token.");

        return new AuthenticationToken(token, user);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AuthenticationToken.class.isAssignableFrom(authentication);
    }
}
