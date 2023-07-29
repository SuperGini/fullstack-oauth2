package com.gin.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class WebConfig {


    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // http.csrf(AbstractHttpConfigurer::disable);
        return http
                .authorizeHttpRequests(request ->
                        request
                                .requestMatchers("/error").permitAll()
                                .requestMatchers("/userx").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(x ->
                        x.loginPage("/login").defaultSuccessUrl("/home").permitAll())
                .build();
    }

    /**
     * https://stackoverflow.com/questions/62531927/spring-security-redirect-to-static-resources-after-authentication
     * https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/web/configuration/WebSecurityCustomizer.html
     * https://www.youtube.com/watch?v=3l2Z0_FGYls&t=42s
     * */
    @Bean
    public WebSecurityCustomizer ignoringCustomizer() {
        return web -> web
                .ignoring()
                .requestMatchers("css/**",
                                 "/fonts/**",
                                 "/images/**",
                                 "/svg/**"
                );
    }
}
