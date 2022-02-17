package com.cinema.booking.exceptions;

import java.text.MessageFormat;

public class PropertyMovieNotFoundException extends Exception {
    public PropertyMovieNotFoundException() {
        super();
    }

    public PropertyMovieNotFoundException(Long id) {
        super(MessageFormat.format("Property with this id:{0} not found",id));
    }

    public PropertyMovieNotFoundException(String message) {
        super(message);
    }

    public PropertyMovieNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertyMovieNotFoundException(Throwable cause) {
        super(cause);
    }

    protected PropertyMovieNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
