package com.koerich.cardconnect.exception;

import org.springframework.http.HttpStatus;


public class ApiException extends Exception {

    private final HttpStatus status;

    public HttpStatus getStatus() {
        return status;
    }

    public ApiException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
