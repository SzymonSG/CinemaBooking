package com.cinema.booking.service;

import com.cinema.booking.entities.Cinema;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.exceptions.CinemaNotFoundException;
import com.cinema.booking.exceptions.MovieNotFoundException;


import java.util.List;

public interface CinemaService {

    //TODO
    public Cinema cinemaSave(Cinema cinemaDepartment);
    //TODO
    public List<Cinema> fetchCinemasList();

    public List<Movie> fetchMoviesList();

    public List<Movie> fetchMoviesByNamesAndRoomsList(String movieName, String movieRoom);

    public Cinema fetchCinemaById(Long cinemaId) throws CinemaNotFoundException;

    public Cinema enrolledCinemaToMovie(Long movieId, Long cinemaId) throws MovieNotFoundException, CinemaNotFoundException;

}
