package com.cinema.booking.exceptions;

import com.cinema.booking.entities.Movie;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;

public class MovieNotFoundException extends Exception{


    public MovieNotFoundException(String message) {
        super(message);
    }

    public MovieNotFoundException(Long id) {
        super(MessageFormat.format("Movie not found with this id",id));
    }


    public MovieNotFoundException(List<Integer> message, String cause) {
        super(MessageFormat.format("Unfortunately, but places with numbers: {0} are: {1} ",message,cause));

    }

    public MovieNotFoundException(List<Integer>place){
        super(MessageFormat.format("Unfortunately, we dont have these places: {0}. Please check available places.",place));
    }


    public MovieNotFoundException(String movieName, LocalDateTime localDateTime) {
        super(MessageFormat.format("We are sorry, but all seats for the: {0} movie for: {1} are reserved. " +
                "We encourage you to browse our repertoire.",movieName,localDateTime));
    }
}
