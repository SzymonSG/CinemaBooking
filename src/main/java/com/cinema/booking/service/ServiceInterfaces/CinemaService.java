package com.cinema.booking.service.ServiceInterfaces;

import com.cinema.booking.entities.Cinema;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.exceptions.AlreadyEnrolledMovieException;
import com.cinema.booking.exceptions.CinemaNotFoundException;
import com.cinema.booking.exceptions.MovieNotFoundException;


import java.util.List;

public interface CinemaService {

    //TODO
    public Cinema cinemaSave(Cinema cinema);
    //TODO
    public List<Cinema> fetchCinemasList();

    public Cinema fetchCinemaById(Long cinemaId) throws CinemaNotFoundException;

    public Cinema enrolledCinemaToMovie(Long movieId, Long cinemaId) throws MovieNotFoundException, CinemaNotFoundException, AlreadyEnrolledMovieException;

}
