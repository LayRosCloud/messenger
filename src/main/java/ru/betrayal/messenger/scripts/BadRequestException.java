package ru.betrayal.messenger.scripts;

import org.springframework.http.HttpStatus;

import org.springframework.web.server.ResponseStatusException;

public class BadRequestException extends ResponseStatusException {

    public BadRequestException(){
        super(HttpStatus.BAD_REQUEST, "Error! Bad request");
    }

    public BadRequestException(String message){
        super(HttpStatus.BAD_REQUEST, message);
    }
}
