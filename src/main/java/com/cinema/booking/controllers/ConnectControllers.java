package com.cinema.booking.controllers;

import com.cinema.booking.entities.Cinema;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.exceptions.CinemaNotFoundException;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.exceptions.PropertyMovieNotFoundException;
import com.cinema.booking.mappers.CinemaMapStruct;
import com.cinema.booking.dtos.connectionDomainDto.CinemaWithMovieDto;
import com.cinema.booking.dtos.connectionDomainDto.MovieIncludePropertiesDto;
import com.cinema.booking.services.cinemaService.CinemaService;
import com.cinema.booking.services.movieService.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



@RequiredArgsConstructor
@RestController
public class ConnectControllers {

    private final MovieService movieService;
    private final CinemaService cinemaService;
    private final CinemaMapStruct cinemaMapStruct;


    //movies/{movieId}/cinemas{cinemaId}
    //register-movie/cinemas/{cinemaId}/movies/{movieId}
    @PostMapping("/movieid/{movieId}/cinemaid/{cinemaId}")
    CinemaWithMovieDto enrolledCinemaToMovie(@PathVariable("movieId") Long movieId,
                                             @PathVariable ("cinemaId") Long cinemaId) throws MovieNotFoundException, CinemaNotFoundException {

        Cinema cinema = cinemaService.enrolledCinemaToMovie(movieId, cinemaId);
        return cinemaMapStruct.toCinemaWithMovieDto(cinema);
    }
    //assignment/properties/{propertyId}/movies/{movieId}
    //movies/{movieId}/properties_movie/{propertyId}
    @PostMapping("/movieid/{movieId}/propertyid/{propertyid}")
    MovieIncludePropertiesDto assignPropertiesToMovie(@PathVariable ("movieId") Long movieId,
                                                      @PathVariable ("propertyid") Long propertyId) throws MovieNotFoundException, PropertyMovieNotFoundException {

        Movie movie = movieService.enrolledPropertiesToMovie(movieId, propertyId);
        return cinemaMapStruct.toMovieIncludePropertiesDto(movie);
    }
}
