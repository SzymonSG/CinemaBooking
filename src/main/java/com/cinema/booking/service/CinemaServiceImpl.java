package com.cinema.booking.service;
import com.cinema.booking.entities.Cinema;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.entities.ProperitiesMovie;
import com.cinema.booking.repository.CinemaRepository;
import com.cinema.booking.repository.MovieRepository;
import com.cinema.booking.repository.PropertiesMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaServiceImpl implements CinemaService,PropertiesMovieService{


    private final CinemaRepository cinemaRepository;

    private final MovieRepository movieRepository;

    private final PropertiesMovieRepository propertiesMovieRepository;

    @Autowired
    public CinemaServiceImpl(CinemaRepository cinemaRepository, MovieRepository movieRepository, PropertiesMovieRepository propertiesMovieRepository) {
        this.cinemaRepository = cinemaRepository;
        this.movieRepository = movieRepository;
        this.propertiesMovieRepository = propertiesMovieRepository;
    }


    @Override
    public Cinema cinemaDepartmentSave(Cinema cinemaDepartment) {
        return cinemaRepository.save(cinemaDepartment);
    }

    @Override
    public Movie movieSave(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<Cinema> fetchCinemasList() {
        return cinemaRepository.findAll();
    }

    @Override
    public List<Movie> fetchMoviesList() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> fetchMoviesByNamesAndRoomsList(String movieName, String movieRoom) {
        List<Movie> list = movieRepository.findByMovieNameAndMovieRoom(movieName, movieRoom);
        System.out.println(list);
        return list;
    }


    @Override
    public ProperitiesMovie propertySave(ProperitiesMovie properitiesMovie) {
        return propertiesMovieRepository.save(properitiesMovie);
    }
}
