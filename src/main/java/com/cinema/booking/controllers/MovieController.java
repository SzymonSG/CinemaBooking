package com.cinema.booking.controllers;

import com.cinema.booking.entities.Movie;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.mappers.CinemaMapStruct;
import com.cinema.booking.mappers.MovieMapper;
import com.cinema.booking.dtos.movieDto.MovieDto;
import com.cinema.booking.dtos.connectionDomainDto.MovieWithCinemaDto;
import com.cinema.booking.services.movieService.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MovieController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;
    private final CinemaMapStruct cinemaMapStruct;

    @PostMapping("/movies")
    public Movie movieSave(@Valid @RequestBody MovieDto movieDto) {
        Movie movie = movieMapper.dtoToMovie(movieDto);
        return movieService.movieSave(movie);

    }
    @GetMapping("/movies:include=cinemas")
    public List<MovieWithCinemaDto> fetchMoviesListDto(){
        return cinemaMapStruct.toMovieWithCinemaListDto(movieService.fetchMoviesList());
    }

    @GetMapping("/movies/{id}")
    public MovieDto fetchMovieById(@PathVariable("id") Long movieId) throws MovieNotFoundException {
        Movie movie = movieService.fetchMovieById(movieId);
        return movieMapper.toMovieDto(movie);
    }

    @DeleteMapping("/movies/{id}")
    public String deleteMovieById(@PathVariable("id") Long movieId){
        movieService.deleteMovieById(movieId);
        return "Movie deleted Successfully!";
    }




}
