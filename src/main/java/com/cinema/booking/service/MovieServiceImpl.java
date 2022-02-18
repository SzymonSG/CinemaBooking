package com.cinema.booking.service;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.entities.PropertiesMovie;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.exceptions.PropertyMovieNotFoundException;
import com.cinema.booking.repository.MovieRepository;
import com.cinema.booking.repository.PropertiesMovieRepository;
import com.cinema.booking.service.ServiceInterfaces.MovieService;
import com.sun.xml.bind.v2.TODO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final PropertiesMovieRepository propertiesMovieRepository;

    @Override
    public Movie movieSave(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> fetchMoviesList() {
        return movieRepository.findAll();
    }

    //ew na orElseThrow
    @Override
    public Movie fetchMovieById(Long movieId) throws MovieNotFoundException {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (!movie.isPresent()){
            throw new MovieNotFoundException(movieId);
        }
        return movie.get();
    }

    @Override
    public void deleteMovieById(Long movieId) {
        movieRepository.deleteById(movieId);
    }

    @Override
    public Movie enrolledPropertiesToMovie(Long movieId, Long propertyId) throws MovieNotFoundException, PropertyMovieNotFoundException {
        Movie movie = fetchMovieById(movieId);
        //TODO ID property?
        PropertiesMovie propertiesMovie = propertiesMovieRepository.findById(propertyId)
                .orElseThrow(() -> new PropertyMovieNotFoundException(movieId));
        movie.assignProperty(propertiesMovie);
        return movieRepository.save(movie);
    }
}
