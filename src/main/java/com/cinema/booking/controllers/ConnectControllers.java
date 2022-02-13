package com.cinema.booking.controllers;

import com.cinema.booking.entities.Cinema;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.entities.PropertiesMovie;
import com.cinema.booking.repository.CinemaRepository;
import com.cinema.booking.repository.MovieRepository;
import com.cinema.booking.repository.PropertiesMovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class ConnectControllers {

    private final CinemaRepository cinemaRepository;
    private final MovieRepository movieRepository;
    private final PropertiesMovieRepository propertiesMovieRepository;

    @PostMapping("/movieid/{movieId}/cinemaid/{cinemaId}")
    Movie enrolledCinemaToMovie(@PathVariable("movieId") Long movieName,
                                @PathVariable ("cinemaId") Long cinemaName)
    {
        Movie movie = movieRepository.findById(movieName).get();
        Cinema cinema = cinemaRepository.findById(cinemaName).get();
        movie.enrolledCinema(cinema);
        return movieRepository.save(movie);
    }

    @PostMapping("/movieid/{movieId}/propertyid/{propertyid}")
    Movie assignPropertiesToMovie(@PathVariable ("movieId") Long movieName,
                                  @PathVariable ("propertyid") Long propertyMovieName)
    {
        Movie movie = movieRepository.findById(movieName).get();
        PropertiesMovie properitiesMovie = propertiesMovieRepository.findById(propertyMovieName).get();
        movie.assignProperty(properitiesMovie);
        return movieRepository.save(movie);
    }
}
