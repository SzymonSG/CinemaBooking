package com.cinema.booking.security.exceptions;
import com.cinema.booking.security.errors.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@ResponseStatus
public class ExceptionHandling {

    @ExceptionHandler({PasswordQualifyException.class})
    ResponseEntity<Error> passwordNotQualify (PasswordQualifyException exception,
                                              WebRequest webRequest){
        Error message = new Error(HttpStatus.CONFLICT,
                exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(message);
    }



}
