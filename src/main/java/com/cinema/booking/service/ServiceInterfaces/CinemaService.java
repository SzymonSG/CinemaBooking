package com.cinema.booking.service.ServiceInterfaces;

import com.cinema.booking.entities.Cinema;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.exceptions.AlreadyEnrolledMovieException;
import com.cinema.booking.exceptions.CinemaNotFoundException;
import com.cinema.booking.exceptions.MovieNotFoundException;


import java.util.List;

public interface CinemaService {


    Cinema cinemaSave(Cinema cinema);

    List<Cinema> fetchCinemasList();

    Cinema fetchCinemaById(Long cinemaId) throws CinemaNotFoundException;

    Cinema enrolledCinemaToMovie(Long movieId, Long cinemaId) throws MovieNotFoundException, CinemaNotFoundException, AlreadyEnrolledMovieException;

}
