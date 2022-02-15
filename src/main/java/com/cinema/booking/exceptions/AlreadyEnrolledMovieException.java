package com.cinema.booking.exceptions;

import java.text.MessageFormat;

public class AlreadyEnrolledMovieException extends RuntimeException {

    public AlreadyEnrolledMovieException(Long movieId, String cinemaName) {
        super(MessageFormat.format("Movie id: {0} is already enrolled by cinema: {1}",movieId,cinemaName));
    }
}
