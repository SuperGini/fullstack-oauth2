package com.example.apiservice.error.handler;

import com.example.apiservice.error.exception.ClientException;
import com.example.apiservice.error.exception.ServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(ClientException.class)
    public ProblemDetail handleClientError(ClientException e) {
        log.error("Error: ", e);
        return ProblemDetail.forStatusAndDetail(e.getHttpStatusCode(), e.getMessage());
    }

    @ExceptionHandler(ServerException.class)
    public ProblemDetail handleServerError(ServerException e) {
        log.error("Error: ", e);
        return ProblemDetail.forStatusAndDetail(e.getHttpStatusCode(), e.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public ProblemDetail handleServerError(Exception e) {
        log.error("Error: ", e);
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "BOOOOM....shit happens....! :-)");
    }

}
