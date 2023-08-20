package com.gin.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class WebConfig {

    private final CorsConfig corsConfig;

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        corsConfig.corsCustomizer(http);
        return http
                .authorizeHttpRequests(request ->
                        request
                                .requestMatchers("/home").hasAuthority("ADMIN")
                                .requestMatchers("/error").permitAll()
                                .requestMatchers("/user").permitAll()
                                .requestMatchers("css/**", "/fonts/**", "/images/**", "/svg/**").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(x ->
                        x.loginPage("/login").permitAll()
                                .defaultSuccessUrl("/home"))
                .build();
    }

    /**
     * https://stackoverflow.com/questions/62531927/spring-security-redirect-to-static-resources-after-authentication
     * https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/web/configuration/WebSecurityCustomizer.html
     * https://www.youtube.com/watch?v=3l2Z0_FGYls&t=42s
     * */
//    @Bean
//    public WebSecurityCustomizer ignoringCustomizer() {
//        return web -> web
//                .ignoring()
//                .requestMatchers("css/**",
//                                 "/fonts/**",
//                                 "/images/**",
//                                 "/svg/**"
//                );
//    }
}
