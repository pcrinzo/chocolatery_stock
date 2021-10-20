package com.chocolate.chocoland.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ChocoNotFoundException extends Exception {

    public ChocoNotFoundException(String chocoName) {
        super(String.format("Chocolate with name %s not found in the system.", chocoName));
    }

    public ChocoNotFoundException(Long id) {
        super(String.format("Chocolate with id %s not found in the system.", id));
    }
}
