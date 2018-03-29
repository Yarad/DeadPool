package com.config;

import com.security.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CORSFilter corsFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .addFilterBefore(createCustomFilter(), AnonymousAuthenticationFilter.class)
                .csrf().disable();
        http.addFilterBefore(corsFilter, ChannelProcessingFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS,"/**");
    }

    protected AbstractAuthenticationProcessingFilter createCustomFilter() throws Exception {
        //Интерфейсы, для доступа к которым не нужна авторизация
        AuthFilter filter = new AuthFilter(new NegatedRequestMatcher(
                new AndRequestMatcher(
                        new AntPathRequestMatcher("/sign_in"),
                        new AntPathRequestMatcher("/sign_up")
                )
        ));
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }
}
