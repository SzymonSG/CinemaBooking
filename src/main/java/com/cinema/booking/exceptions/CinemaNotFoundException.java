package com.cinema.booking.exceptions;

import java.text.MessageFormat;

public class CinemaNotFoundException extends Exception{
    public CinemaNotFoundException() {
        super();
    }
    public CinemaNotFoundException(Long id) {
        super(MessageFormat.format("Not found Cinema with this id: {0}",id));
    }

    public CinemaNotFoundException(String message) {
        super(message);
    }

    public CinemaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CinemaNotFoundException(Throwable cause) {
        super(cause);
    }

    protected CinemaNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
