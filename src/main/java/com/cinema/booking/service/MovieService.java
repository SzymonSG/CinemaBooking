package com.cinema.booking.service;

import com.cinema.booking.entities.Movie;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.mapstructDTO.MovieDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface MovieService {
    List<Movie> bookedUpdate(String nameCinema, String movie);
    //o tym pomyśl
    List<MovieDTO> multiBookedPlaceDto(String cinemaName, String movieName, List<Integer> wantedPlaces) throws MovieNotFoundException;
    List<Movie> multiBookedPlaceEntity(String cinemaName, String movieName, List<Integer> wantedPlaces) throws MovieNotFoundException;

    List<Movie> fetchMoviesList();

    //test
    List<Movie> findByDateQuery(LocalDateTime localDate);

    //test2
   // List<ComplexMovieDTO> multiBookedPlaceWithDTO(String cinemaName, String movieName, List<Integer> wantedPlaces, LocalDateTime localDateTime) throws MovieNotFoundException;
    List<Movie> multiBookedPlaceWithDate(String cinemaName, String movieName, List<Integer> wantedPlaces, LocalDateTime localDateTime) throws MovieNotFoundException;
}
