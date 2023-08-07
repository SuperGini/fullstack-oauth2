package com.example.apiservice.confg;

import com.example.apiservice.client.CoreServiceClient;
import com.example.apiservice.error.decoder.WebClientErrorDecoder;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class ClientConfig {

    @Bean
    public HttpClient client() {
        return HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 30000)
                .responseTimeout(Duration.ofMillis(30000))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(30000, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(30000, TimeUnit.MILLISECONDS)));
    }

    @Bean
    public WebClient reactiveCoreWebClient(HttpClient client, OAuth2AuthorizedClientManager authorizedClientManager) {

        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2Client =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
        /**
         * https://docs.spring.io/spring-security/reference/reactive/oauth2/client/authorized-clients.html
         * SET THIS MOTHERFUCKER REGISTRATIONid OR IT WILL NOT SET THE AUTHORIZATION TOKEN WHEN DOING A REQUEST
         * */
        oauth2Client.setDefaultClientRegistrationId("core-service");

        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .apply(oauth2Client.oauth2Configuration())
                .baseUrl("http://localhost:8081")
                .clientConnector(new ReactorClientHttpConnector(client))
                .defaultStatusHandler(HttpStatusCode::isError, WebClientErrorDecoder::handleException) // see error package for WebClientErrorDecoder
                .build();
    }

    @Bean
    public HttpServiceProxyFactory httpServiceProxyFactory(WebClient client) {
        return HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(client))
                .build();
    }

    @Bean
    public CoreServiceClient coreServiceClient(HttpServiceProxyFactory factory) {
        return factory.createClient(CoreServiceClient.class);
    }
}
