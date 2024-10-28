package com.koerich.cardconnect.exception;

import org.springframework.http.HttpStatus;

public class NoContentException extends ApiException {

    public NoContentException(String message, HttpStatus badRequest) {
        super(message, HttpStatus.NO_CONTENT);
    }
}
