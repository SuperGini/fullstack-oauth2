package com.example.apiservice.confg;

import com.example.apiservice.client.CoreServiceClient;
import com.example.apiservice.error.decoder.WebClientErrorDecoder;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

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
    public WebClient reactiveCoreWebClient(HttpClient client) {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
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
