package com.example.apiservice.error.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class ClientException extends RuntimeException {

    private final HttpStatusCode httpStatusCode;

    public ClientException(String message, HttpStatusCode httpStatusCode) {
        super(message);
        this.httpStatusCode = httpStatusCode;
    }
}
