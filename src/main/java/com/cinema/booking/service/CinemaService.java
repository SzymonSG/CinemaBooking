package com.cinema.booking.service;

import com.cinema.booking.entities.Cinema;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.mapstructDTO.CinemaDTO;


import java.util.List;

public interface CinemaService {

    Cinema cinemaDepartmentSave(Cinema cinemaDepartment);


    Movie movieSave(Movie movie);

    List<Cinema> fetchCinemasList();

    List<Movie> fetchMoviesList();

    List<Movie> fetchMoviesByNamesAndRoomsList(String movieName, String movieRoom);




}
