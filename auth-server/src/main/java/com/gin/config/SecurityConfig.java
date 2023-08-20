package com.gin.config;


import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
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
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.util.UUID;

/**
 * https://docs.spring.io/spring-authorization-server/docs/current/reference/html/getting-started.html
 */

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsConfig corsConfig;


    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        corsConfig.corsCustomizer(http);
       // http.csrf(x -> x.disable());

        /**
         *  http // necesara ca sa avem acces la http://localhost:8080/.well-known/openid-configuration din postman
         *             .getConfigurer(OAuth2AuthorizationServerConfigurer.class)
         *             .oidc(Customizer.withDefaults()); //OpenId connect
         * */
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());    // Enable OpenID Connect 1.0
        http
                // Redirect to the login page when not authenticated from the
                // authorization endpoint
                .exceptionHandling(exceptions -> exceptions
                        .defaultAuthenticationEntryPointFor(
                                new LoginUrlAuthenticationEntryPoint("/login"),
                                new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
                        )
                )
//                .sessionManagement((session) -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
                // Accept access tokens for User Info and/or Client Registration
                .oauth2ResourceServer(resourceServer -> resourceServer
                        .jwt(Customizer.withDefaults()));

            //TODO: to resove this shit -> LOL :-)
            /**
             * at the current moment I cant redirect the user from auth-server to angular app.
             * I cant make the auth-server stateless, so I made the session cookie last for 5 min.
             * In the current moment spring auth-server does not offer the possibility to rotate refresh-token
             *
             * */
//        http.oauth2Login(Customizer.withDefaults())
//                .logout(logout -> logout.logoutSuccessHandler(oidcLogoutSuccessHandler()));

        return http.build();
    }

//    @Bean -> need for logout
//    public ClientRegistrationRepository clientRegistrationRepository() {
//        ClientRegistration c1 = ClientRegistration.withRegistrationId("angular")
//                .clientId("client")
//                .clientSecret("secret")
//                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .tokenUri("http://localhost:8080/oauth2/token")
//                .redirectUri("http://localhost:8083/login")
//                .scope(OidcScopes.OPENID)
//                .build();
//
//        return new InMemoryClientRegistrationRepository(c1);
//    }
            // -> need for logout
//    private LogoutSuccessHandler oidcLogoutSuccessHandler() {
//        OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler =
//                new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository());
//
//        // Sets the location that the End-User's User Agent will be redirected to
//        // after the logout has been performed at the Provider
//        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("http://localhost:8083/login");
//
//        return oidcLogoutSuccessHandler;
//    }


    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        RegisteredClient r1 = RegisteredClient.withId("angular")
                .clientId("client")
                .clientSecret("secret")
                .scope(OidcScopes.OPENID) //avem nevoie de cel putin un scop
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN) //nu este obligatoriu
                .redirectUri("http://localhost:8083/authorized") //-> va face redirect la acest url la front client
                .tokenSettings(
                        // putem sa setam proprietatile tokenului
                        TokenSettings.builder()
                                .accessTokenTimeToLive(Duration.ofMinutes(15))
                                .refreshTokenTimeToLive(Duration.ofHours(8))
                                .build()

                )
                .clientSettings(ClientSettings.builder()
                        .requireAuthorizationConsent(true)
                        .build())
                .build();

        RegisteredClient r2 = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("apiClient")
                .clientSecret("apiSecret")
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofHours(10))
                        .build())
                .scope(OidcScopes.OPENID)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .build();

        return new InMemoryRegisteredClientRepository(r1, r2);
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

    @Bean //main settings of the server where we can override the endpoints
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().build();
    }


    @Bean // use Bcrypt in practice
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    //https://stackoverflow.com/questions/74730577/add-claims-to-the-token-in-spring-security-after-retrieving-a-user
    //https://docs.spring.io/spring-authorization-server/docs/current/reference/html/core-model-components.html#oauth2-token-customizer
    //https://docs.spring.io/spring-authorization-server/docs/current/reference/html/guides/how-to-userinfo.html#customize-id-token
    //https://github.com/spring-projects/spring-authorization-server/pull/1073/commits/eea7db3746323f0ffb10362ec254fa1d2cf36bfd#diff-7dda346b2952ea91e854d284361d20298d4dc323beeb0188ca00be3038910d9c
    @Bean //customize the JWT so I can add claims to it
    public OAuth2TokenCustomizer<JwtEncodingContext> oAuth2TokenCustomizer() {
        return context -> {
            var authorities = context.getPrincipal().getAuthorities();

            context.getClaims()
                    .claim("authorities", authorities.stream()
                            .map(GrantedAuthority::getAuthority)
                            .toList()
                    );
//           if(OidcParameterNames.ID_TOKEN.equals(context.getTokenType().getValue())){
//
//            }
        };
    }


}
