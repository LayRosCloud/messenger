package ru.betrayal.messenger.scripts;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends ResponseStatusException {
    public NotFoundException(){
        super(HttpStatus.NOT_FOUND,"Error! Object is not founded");
    }

    public NotFoundException(String message){
        super(HttpStatus.NOT_FOUND, message);
    }
}
