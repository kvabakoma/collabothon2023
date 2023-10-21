package com.tigerchamp.collabothon2023backend.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    private final AuthTokenFilter authTokenFilter;
    private final AuthEntryPointJwt unauthorizedHandler;

    @Autowired
    public SecurityConfiguration(AuthTokenFilter authTokenFilter, AuthEntryPointJwt unauthorizedHandler) {
        this.authTokenFilter = authTokenFilter;
        this.unauthorizedHandler = unauthorizedHandler;
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.
                cors().and().
                csrf().disable().
                exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
                authorizeHttpRequests().requestMatchers("/auth/login").permitAll().
                anyRequest().authenticated();

        http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
