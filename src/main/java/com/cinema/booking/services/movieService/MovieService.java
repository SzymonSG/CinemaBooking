package com.cinema.booking.services.movieService;

import com.cinema.booking.entities.Movie;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.exceptions.PropertyMovieNotFoundException;


import java.util.List;

public interface MovieService {

    Movie movieSave(Movie movie);

    List<Movie> fetchMoviesList();

    Movie fetchMovieById(Long movieId) throws MovieNotFoundException;

    void deleteMovieById(Long movieId);

    Movie enrolledPropertiesToMovie(Long movieId, Long propertyId) throws MovieNotFoundException, PropertyMovieNotFoundException;

}
