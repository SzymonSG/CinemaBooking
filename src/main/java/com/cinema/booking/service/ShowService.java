package com.cinema.booking.service;

import com.cinema.booking.entities.Movie;
import com.cinema.booking.entities.PropertiesMovie;
import com.cinema.booking.exceptions.MovieNotFoundException;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.List;

public interface ShowService {
    List<Movie> showAllPlayingMoviesInCinema(String cinemaName) throws MovieNotFoundException;

    List<Movie> findFreePlacesOnMovie(String cinemaName, String movieName, LocalDateTime localDateTime) throws MovieNotFoundException;


    List<Movie> checkMoviesAfterDate(LocalDateTime localDateTime);

    List<PropertiesMovie> showDateChosenMovie(String cinemaName, String movieName) throws MovieNotFoundException;
}
