package com.cinema.booking.controllers;

import com.cinema.booking.dto.MovieDto;
import com.cinema.booking.dto.mappers.MovieDtoMapper;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.service.CinemaService;
import com.cinema.booking.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class MovieController {


    private final CinemaService cinemaService;
    private final MovieService movieService;

    @PostMapping("/save/movie")
    Movie movieSave(@Valid @RequestBody Movie movie){

        return cinemaService.movieSave(movie);
    }


    @GetMapping("/moviess")
    List<MovieDto> fetchMoviesListDto() {
        List<MovieDto> collect = movieService.fetchMoviesList().stream()
                .map(MovieDtoMapper::movieWithCinemasToDto)
                .collect(Collectors.toList());
        return collect;
    }

    @GetMapping("/movies")
    List<Movie> fetchMoviesList(){
        return cinemaService.fetchMoviesList();
    }

}
