package com.koerich.cardconnect.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException {

    public NotFoundException(String message, HttpStatus unauthorized) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
