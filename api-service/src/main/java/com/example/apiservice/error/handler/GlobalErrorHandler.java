package com.example.apiservice.error.handler;

import com.example.apiservice.error.exception.ClientException;
import com.example.apiservice.error.exception.ServerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(ClientException.class)
    public ProblemDetail handleClientError(ClientException e) {
        return ProblemDetail.forStatusAndDetail(e.getHttpStatusCode(), e.getMessage());
    }

    @ExceptionHandler(ServerException.class)
    public ProblemDetail handleServerError(ServerException e) {
        return ProblemDetail.forStatusAndDetail(e.getHttpStatusCode(), e.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public ProblemDetail handleServerError(Exception e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "BOOOOM....shit happens....! :-)");
    }

}
