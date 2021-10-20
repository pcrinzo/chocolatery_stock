package com.chocolate.chocoland.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ChocoAlreadyRegisteredException extends Exception{

    public ChocoAlreadyRegisteredException(String chocoName) {
        super(String.format("Chocolate with name %s already registered in the system.", chocoName));
    }
}
