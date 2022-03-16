package com.cinema.booking.dtos.connectionDomainDto;

import com.cinema.booking.dtos.movieDto.MovieDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CinemaWithMovieDto {

    private Long cinemaDtoId;

    private String cinemaNameDto;
    //MovieDTOComplex
    private List<MovieDto> movies = new ArrayList<>();
}
