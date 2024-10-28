package com.koerich.cardconnect.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ApiException {

    public BadRequestException(String message, HttpStatus badRequest) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
