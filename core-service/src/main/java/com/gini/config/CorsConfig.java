package com.gini.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

//no need for this since i don't call this service from front-end
//@Component
public class CorsConfig {
    public void corsCustomizer(HttpSecurity http) throws Exception {
        http.cors(c -> {
            CorsConfigurationSource source = s -> {
                CorsConfiguration cc = new CorsConfiguration();
                cc.setAllowCredentials(true);
                cc.setAllowedOrigins(List.of("http://localhost:8083"));
                cc.setAllowedHeaders(List.of("*")); // -> all types of headers
                cc.setAllowedMethods(List.of("*")); // -> all types of methods
                return cc;
            };

            c.configurationSource(source);
        });
    }
}
