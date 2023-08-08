package com.gini.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .oauth2ResourceServer(
                auth -> auth.jwt(jwt -> jwt.jwkSetUri("http://localhost:8080/oauth2/jwks")
                )
        );

        http
           .authorizeHttpRequests(req -> req.anyRequest().authenticated());

        return http.build();
    }

}
