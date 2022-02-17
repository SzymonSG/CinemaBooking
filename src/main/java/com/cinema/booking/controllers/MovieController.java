package com.cinema.booking.controllers;

import com.cinema.booking.entities.Movie;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.mapper.CinemaMapStruct;
import com.cinema.booking.mapstructDTO.MovieDto;
import com.cinema.booking.mapstructDTO.MovieWithCinemaDto;
import com.cinema.booking.service.ServiceInterfaces.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RequiredArgsConstructor
@RestController
public class MovieController {

    private final MovieService movieService;
    private final CinemaMapStruct cinemaMapStruct;

    //movies
    @PostMapping("/movies")
    public Movie movieSave(@Valid @RequestBody MovieDto movieDto){
        Movie movie = cinemaMapStruct.dtoToMovie(movieDto);
        return movieService.movieSave(movie);
    }
    //movies?include=cinemas
    @GetMapping("/movies:include=cinemas")
    public List<MovieWithCinemaDto> fetchMoviesListDto(){
        return cinemaMapStruct.toMovieWithCinemaListDto(movieService.fetchMoviesList());
    }
    //check this
    @GetMapping("/movies/{id}")
    public MovieDto fetchMovieById(@PathVariable("id") Long movieId) throws MovieNotFoundException {
        Movie movie = movieService.fetchMovieById(movieId);
        return cinemaMapStruct.toMovieDto(movie);
    }


    @DeleteMapping("/movies/{id}")
    public String deleteMovieById(@PathVariable("id") Long movieId){
        movieService.deleteMovieById(movieId);
        return "Movie deleted Successfully!";
    }



//    @GetMapping("/moviess")
//    List<MovieDTO> fetchMoviesListDto() {
//        List<MovieDTO> collect = movieService.fetchMoviesList().stream()
//                .map(MovieDtoMapper::movieWithCinemasToDto)
//                .collect(Collectors.toList());
//        return collect;
//    }
//


}
