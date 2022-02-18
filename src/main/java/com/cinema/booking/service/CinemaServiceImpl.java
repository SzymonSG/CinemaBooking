package com.cinema.booking.service;
import com.cinema.booking.entities.Cinema;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.exceptions.AlreadyEnrolledMovieException;
import com.cinema.booking.exceptions.CinemaNotFoundException;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.repository.CinemaRepository;
import com.cinema.booking.repository.MovieRepository;
import com.cinema.booking.service.ServiceInterfaces.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class CinemaServiceImpl implements CinemaService {

    private final CinemaRepository cinemaRepository;
    private final MovieRepository movieRepository;
    //private final MovieServiceImpl movieServiceImpl;

    @Override
    public Cinema cinemaSave(Cinema cinema){
        return cinemaRepository.save(cinema);
    }

    @Override
    public List<Cinema> fetchCinemasList() {
        return cinemaRepository.findAll();
    }

    @Override
    public Cinema fetchCinemaById(Long cinemaId) throws CinemaNotFoundException {
        return cinemaRepository.findById(cinemaId)
                .orElseThrow(()->new CinemaNotFoundException(cinemaId));
    }

    @Override
    public Cinema enrolledCinemaToMovie(Long movieId, Long cinemaId) throws MovieNotFoundException, CinemaNotFoundException, AlreadyEnrolledMovieException {
        Cinema cinema = fetchCinemaById(cinemaId);
        Movie movie = (movieRepository.findById(movieId).orElseThrow(
                () -> new MovieNotFoundException(movieId)));
//        Movie movie = movieServiceImpl.fetchMovieById(movieId);
        if (!movie.getCinemas().isEmpty()){
            throw new AlreadyEnrolledMovieException(movieId,cinema.getCinemaName());
        }
        cinema.enrolledMovie(movie);
        return cinemaRepository.save(cinema);
    }
}
