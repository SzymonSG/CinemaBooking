package com.cinema.booking.service;

import com.cinema.booking.entities.Cinema;
import com.cinema.booking.entities.Movie;



import java.util.List;

public interface CinemaService {

    //TODO
    Cinema cinemaSave(Cinema cinemaDepartment);
    //TODO
    List<Cinema> fetchCinemasList();

    List<Movie> fetchMoviesList();

    List<Movie> fetchMoviesByNamesAndRoomsList(String movieName, String movieRoom);




}
