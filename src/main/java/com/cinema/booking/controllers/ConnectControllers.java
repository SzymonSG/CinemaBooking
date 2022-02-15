package com.cinema.booking.controllers;

import com.cinema.booking.entities.Cinema;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.entities.PropertiesMovie;
import com.cinema.booking.exceptions.CinemaNotFoundException;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.exceptions.PropertyMovieNotFoundException;
import com.cinema.booking.mapper.CinemaMapStruct;
import com.cinema.booking.mapstructDTO.MovieWithCinemaDto;
import com.cinema.booking.service.CinemaService;
import com.cinema.booking.service.MovieService;
import com.cinema.booking.service.PropertiesMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



@RequiredArgsConstructor
@RestController
public class ConnectControllers {

    private final MovieService movieService;
    private final PropertiesMovieService propertiesMovieService;
    private final CinemaMapStruct cinemaMapStruct;



    //movies/{movieId}/cinemas{cinemaId}
    @PostMapping("/movieid/{movieId}/cinemaid/{cinemaId}")
    MovieWithCinemaDto enrolledCinemaToMovie(@PathVariable("movieId") Long movieId,
                                             @PathVariable ("cinemaId") Long cinemaId) throws MovieNotFoundException, CinemaNotFoundException {

// chyba git mozna sie zastanowić nad zmianą dto na CinemaWithMovie, lub stworzenie enrolledCinema w cinemaService
        Movie movie = movieService.enrolledCinemaToMovie(movieId, cinemaId);
        return cinemaMapStruct.toMovieWithCinemaDto(movie);
//        cinemaMapStruct.toComplexMovieDto(movie);
//        Movie movie = movieService.fetchMovieById(movieId);
//        Cinema cinema = cinemaService.fetchCinemaById(cinemaId);
//
//        //to nam sprawdza czy film o tym id jest juz doddany, ale jesli ma inne id to mozna juz dodać.
//        if (!movie.getCinemas().isEmpty()){
//            throw new CinemaNotFoundException("This film yet is adding to cinema.!");
//        }
//
//        movie.enrolledCinema(cinema);
//        return movieService.movieSave(movie);
    }

    //movies/{movieId}/properties_movie/{propertyId}
    @PostMapping("/movieid/{movieId}/propertyid/{propertyid}")
    Movie assignPropertiesToMovie(@PathVariable ("movieId") Long movieId,
                                  @PathVariable ("propertyid") Long propertyId) throws MovieNotFoundException, PropertyMovieNotFoundException {

        Movie movie = movieService.fetchMovieById(movieId);
        PropertiesMovie propertiesMovie = propertiesMovieService.fetchPropertyMovieById(propertyId);

        movie.assignProperty(propertiesMovie);
        return movieService.movieSave(movie);
    }
}
