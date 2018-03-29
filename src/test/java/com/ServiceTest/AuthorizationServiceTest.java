package com.ServiceTest;

import com.DTO.TokenVerifyResult;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.logic.Detective;
import com.services.AuthorizationService;
import com.services.KeyProvider;
import com.services.interfaces.IKeyProvider;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.Console;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

public class AuthorizationServiceTest {
    @Mock
    private IKeyProvider keyProvider;

    @InjectMocks
    private AuthorizationService service;

    private static IKeyProvider realKeyProvider;

    @BeforeClass
    public static void getDAO() {
        realKeyProvider = new KeyProvider();
    }

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getToken() throws Exception {
        long hoursExpire = 24*7;
        Detective user = new Detective();
        user.setLogin("some_login");

        when(keyProvider.getHS256Key()).thenReturn(realKeyProvider.getHS256Key());

        String actualResult = service.getToken(user, hoursExpire);

        assertNotEquals(null, actualResult);

        String userName = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(realKeyProvider.getHS256Key());
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();
            DecodedJWT jwt = verifier.verify(actualResult);
            userName = jwt.getSubject();
        } catch (UnsupportedEncodingException exception){
            String t = Console.class.toString();
        } catch (JWTVerificationException exception){
            String t = Console.class.toString();
        }

        assertEquals(user.getLogin(), userName);
    }

    @Test
    public void checkTokenCorrect() throws Exception {
        long hoursExpire = 24*7;
        Detective user = new Detective();
        user.setLogin("some_login");
        when(keyProvider.getHS256Key()).thenReturn(realKeyProvider.getHS256Key());
        String token = service.getToken(user, hoursExpire);

        TokenVerifyResult result = service.checkToken(token);
        assertEquals(true, result.getIsCorrect());
        assertEquals(user.getLogin(), result.getUserName());
    }

    @Test
    public void checkTokenNotCorrect() throws Exception {
        when(keyProvider.getHS256Key()).thenReturn(realKeyProvider.getHS256Key());

        TokenVerifyResult result = service.checkToken("fjycgvbu.oiykcfjchghbkjniuulgjh.kjnluvkcfhg");
        assertEquals(false, result.getIsCorrect());
        assertEquals(null, result.getUserName());
    }
}
