package com.gin.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class WebConfig {


    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(request ->
                        request
                                .requestMatchers("/error").permitAll()
                                // -> need to grand access to css file or OAUTH2 does not work properly(at least for login page I think)
                                .requestMatchers("/css/**").permitAll() // ->https://stackoverflow.com/questions/62531927/spring-security-redirect-to-static-resources-after-authentication
                                .requestMatchers("/fonts/**").permitAll()
                                .requestMatchers("/images/**").permitAll()
                                .requestMatchers("/svg/**").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(x ->
                        x.loginPage("/login").defaultSuccessUrl("/home").permitAll())
                .build();
    }

}
