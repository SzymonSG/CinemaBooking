package com.cinema.booking.controllers;

import com.cinema.booking.dto.MovieDTO;
import com.cinema.booking.dto.mappers.MovieDtoMapper;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.mapper.CinemaMapStruct;
import com.cinema.booking.mapstructDTO.MovieDto;
import com.cinema.booking.mapstructDTO.MovieWithCinemaDto;
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

    private final MovieService movieService;
    private final CinemaMapStruct cinemaMapStruct;


    @PostMapping("/save/movie")
    Movie movieSave(@Valid @RequestBody MovieDto movieDto){
        Movie movie = cinemaMapStruct.dtoToMovie(movieDto);
        return movieService.movieSave(movie);
    }
    @GetMapping("/movieList")
    List<MovieWithCinemaDto> fetchMoviesListDto(){
        return cinemaMapStruct.toMovieWithCinemaListDto(movieService.fetchMoviesList());
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
