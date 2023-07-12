package com.gin.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.web.SecurityFilterChain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.util.UUID;

/**
 * https://docs.spring.io/spring-authorization-server/docs/current/reference/html/getting-started.html
 * */

@Configuration
public class SecurityConfig {


    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http); // for the moment we need this. In the future we might not

        http
                .getConfigurer(OAuth2AuthorizationServerConfigurer.class) // -> to get access to http://localhost:8080/.well-known/openid-configuration from postman
                .oidc(Customizer.withDefaults()); // -> OpenId connect

        http.formLogin(Customizer.withDefaults());

        return http
                .build();
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
             //   .csrf(AbstractHttpConfigurer::disable) // dont forget to enable // -> Thymeleaf takes care of CSRF :)
                .authorizeHttpRequests(request -> request.anyRequest().permitAll())
                .formLogin(Customizer.withDefaults()) // -> add my login page
                .build();
    }

    //FOR THE MOMENT
    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        RegisteredClient r1 = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("client")
                .clientSecret("secret")
                .scope(OidcScopes.OPENID) //avem nevoie de cel putin un scop
                /**
                 * -> numerge cu asta -> de vazut de ce?? ->
                 * trebuie in postman sa pun la Basic Auth username: client si password: secret la al doilea pas ca sa mearga
                 */
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN) //nu este obligatoriu
                .redirectUri("http://localhost:3000/authorized") //-> vaf ace redirect la acest url la front client
                .tokenSettings(
                        //putem sa setam proprietatile tokenului
                        TokenSettings.builder()
                                .accessTokenTimeToLive(Duration.ofHours(10))
                                .refreshTokenTimeToLive(Duration.ofHours(10))
                                .build()
                )
                .clientSettings(ClientSettings.builder()
                        .requireAuthorizationConsent(true)
                        .build())
                .build();

        return new InMemoryRegisteredClientRepository(r1);
    }

    @Bean //daca avem JWT trebuie sa avem 2 chei -> astea ar trebui luate dintr-un vault
    public JWKSource<SecurityContext> jwkSource() throws NoSuchAlgorithmException {
        KeyPairGenerator kg = KeyPairGenerator.getInstance("RSA");
        kg.initialize(2048); //initializam cheia sa aiba 2048 biti
        KeyPair kp = kg.generateKeyPair(); //generam cheile


        RSAPublicKey publicKey = (RSAPublicKey) kp.getPublic(); //extragem cheia publica
        RSAPrivateKey privateKey = (RSAPrivateKey) kp.getPrivate(); //extragem cheia privata

        RSAKey key = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();

        JWKSet set = new JWKSet(key);

        return new ImmutableJWKSet<>(set);
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }



}
