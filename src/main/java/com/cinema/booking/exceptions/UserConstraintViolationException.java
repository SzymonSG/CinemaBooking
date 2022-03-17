package com.cinema.booking.exceptions;

public class UserConstraintViolationException extends Exception {

    public UserConstraintViolationException(String message) {
        super(message);
    }
}
