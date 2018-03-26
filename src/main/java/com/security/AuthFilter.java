package com.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class AuthFilter extends AbstractAuthenticationProcessingFilter {
    public static final String TOKEN_HEADER = "deadpool-token";

    public AuthFilter(RequestMatcher requestMatcher) {
        super(requestMatcher);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        final String token = getTokenValue((HttpServletRequest) request);

        //Проверка наличия нужного заголовка
        if (StringUtils.isEmpty(token)) {
            chain.doFilter(request, response);
            return;
        }

        //В случае наличия заголовка
        this.setAuthenticationSuccessHandler((requestNext, responseNext, authentication) -> {
            chain.doFilter(requestNext, responseNext);
        });

        super.doFilter(request, response, chain);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        final String tokenValue = getTokenValue(request);

        if (StringUtils.isEmpty(tokenValue)) {
            //на всякий случай, т.к. doFilter это должен отловить. Но т.к. public...
            return null;
        }

        AuthenticationToken token = new AuthenticationToken(tokenValue);
        token.setDetails(authenticationDetailsSource.buildDetails(request));

        return this.getAuthenticationManager().authenticate(token);
    }

    private String getTokenValue(HttpServletRequest req) {
        //поиск нужного заголовка
        return Collections.list(req.getHeaderNames()).stream()
                .filter(header -> header.equalsIgnoreCase(TOKEN_HEADER))
                .map(header -> req.getHeader(header))
                .findFirst()
                .orElse(null);
    }
}
