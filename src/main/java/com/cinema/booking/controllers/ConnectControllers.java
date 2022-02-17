package com.cinema.booking.controllers;

import com.cinema.booking.entities.Cinema;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.exceptions.CinemaNotFoundException;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.exceptions.PropertyMovieNotFoundException;
import com.cinema.booking.mapper.CinemaMapStruct;
import com.cinema.booking.mapstructDTO.CinemaWithMovieDto;
import com.cinema.booking.mapstructDTO.MovieIncludePropertiesDto;
import com.cinema.booking.service.ServiceInterfaces.CinemaService;
import com.cinema.booking.service.ServiceInterfaces.MovieService;
import com.cinema.booking.service.ServiceInterfaces.PropertiesMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



@RequiredArgsConstructor
@RestController
public class ConnectControllers {

    private final MovieService movieService;
    private final CinemaService cinemaService;
    private final PropertiesMovieService propertiesMovieService;
    private final CinemaMapStruct cinemaMapStruct;



    //movies/{movieId}/cinemas{cinemaId}
    @PostMapping("/movieid/{movieId}/cinemaid/{cinemaId}")
    CinemaWithMovieDto enrolledCinemaToMovie(@PathVariable("movieId") Long movieId,
                                             @PathVariable ("cinemaId") Long cinemaId) throws MovieNotFoundException, CinemaNotFoundException {

        Cinema cinema = cinemaService.enrolledCinemaToMovie(movieId, cinemaId);
        return cinemaMapStruct.toCinemaWithMovieDto(cinema);
    }

    //movies/{movieId}/properties_movie/{propertyId}
    @PostMapping("/movieid/{movieId}/propertyid/{propertyid}")
    MovieIncludePropertiesDto assignPropertiesToMovie(@PathVariable ("movieId") Long movieId,
                                                      @PathVariable ("propertyid") Long propertyId) throws MovieNotFoundException, PropertyMovieNotFoundException {


        Movie movie = movieService.enrolledPropertiesToMovie(movieId, propertyId);
        return cinemaMapStruct.toMovieIncludePropertiesDto(movie);
    }
}
