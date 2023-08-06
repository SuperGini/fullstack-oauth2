package com.example.apiservice.confg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(x -> x.disable());
        http.authorizeHttpRequests(request -> {
            request.anyRequest().permitAll();
        });

        return http
                .build();
    }

}
