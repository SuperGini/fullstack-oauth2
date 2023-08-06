package com.example.apiservice.error.decoder;

import com.example.apiservice.error.exception.ClientException;
import com.example.apiservice.error.exception.ServerException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WebClientErrorDecoder {

    public static Mono<? extends Throwable> handleException(ClientResponse h) {
        if (h.statusCode().is4xxClientError()) {
            return Mono.just(new ClientException("shit happens", h.statusCode()));
        }

        if (h.statusCode().is5xxServerError()) {
            return Mono.just(new ServerException("shit happens", h.statusCode()));
        }

        return Mono.just(new ServerException("shit happens", h.statusCode()));
    }
}
