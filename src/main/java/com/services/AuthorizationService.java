package com.services;

import com.DTO.TokenVerifyResult;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.logic.Detective;
import com.services.interfaces.IAuthorizationService;
import com.services.interfaces.IKeyProvider;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.sql.Date;

@Service
public class AuthorizationService implements IAuthorizationService {
    static Logger log = Logger.getLogger(AuthorizationService.class.getName());

    @Autowired
    private IKeyProvider keyProvider;

    @Override
    public String getToken(Detective user, long hoursExpire) {
        String token;
        try {
            Algorithm algorithm = Algorithm.HMAC256(keyProvider.getHS256Key());
            token = JWT.create()
                    .withIssuer("auth0")
                    .withSubject(user.getLogin())
                    .withIssuedAt(new Date(System.currentTimeMillis()))
                    .withNotBefore(hoursExpire > 0 ? new Date(System.currentTimeMillis() + hoursExpire*60*60*1000) : null)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException exception){
            token = null;
            log.error("Error with encoding of token", exception);
        } catch (JWTCreationException exception){
            token = null;
            log.error("Error creating JWT token", exception);
        }
        return token;
    }

    @Override
    public TokenVerifyResult checkToken(String token) {
        String userName = null;
        boolean result = false;
        try {
            Algorithm algorithm = Algorithm.HMAC256(keyProvider.getHS256Key());
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            userName = jwt.getSubject();
            result = true;
        } catch (UnsupportedEncodingException exception){
            log.error("Error with encoding of token", exception);
        } catch (JWTVerificationException exception){
            log.error("JWT verification error", exception);
        }
        return new TokenVerifyResult(result, userName);
    }
}
