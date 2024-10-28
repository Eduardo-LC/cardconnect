package com.koerich.cardconnect.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends ApiException {

    public UnauthorizedException(String message, HttpStatus unauthorized) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
