package ru.betrayal.messenger.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.betrayal.messenger.scripts.BadRequestException;
import ru.betrayal.messenger.scripts.NotFoundException;

@Slf4j
@RestControllerAdvice
public class ExceptionResolver {
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> handleException(NotFoundException ex){
        return new ResponseEntity<Object>(ex.getBody(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<Object> handleException(BadRequestException ex){
        return new ResponseEntity<Object>(ex.getBody(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException(Exception ex){
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
