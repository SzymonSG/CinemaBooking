package com.cinema.booking.dto2;

import com.cinema.booking.dto.CinemaZ;
import com.cinema.booking.entities.Cinema;


import java.util.stream.Collectors;

public class MapperektoCinema {


    public static CinemaZ withCinema(Cinema cinema){

        CinemaZ cinemaZ = CinemaZ.builder()
                .cinemaId(cinema.getCinemaId())
                .cinemaName(cinema.getCinemaName())
                .moviesDto(cinema.getMovies().stream()
                        .map(MapperHelper::convToMovieBez)
                        .collect(Collectors.toList()))
                .build();

        return cinemaZ;
    }


}
