package com.cinema.booking.dto.mappers;



import com.cinema.booking.dto.CinemaDTO;
import com.cinema.booking.dto.CinemaZ;
import com.cinema.booking.entities.Cinema;

import java.util.stream.Collectors;

public class CinemaDtoMapper {

    //public static CinemaDto cinemasWithMovieToDto(Cinema cinema) {
//
    //    CinemaDto cinemaDto = CinemaDto.builder()
    //            .cinemaId(cinema.getCinemaId())
    //            .cinemaName(cinema.getCinemaName())
    //            //.moviesDto(cinema.getMovies()
    //            //        .stream()
    //            //        .map(MovieDtoMapper::mapToDto)
    //            //        .collect(Collectors.toList()))
    //            .build();
//
    //    return cinemaDto;
    //}
    public static CinemaZ withCinema(Cinema cinema){

        CinemaZ cinemaZ = CinemaZ.builder()
                .cinemaId(cinema.getCinemaId())
                .cinemaName(cinema.getCinemaName())
                .moviesDto(cinema.getMovies().stream()
                        .map(MovieDtoMapper::convToMovieBez)
                        .collect(Collectors.toList()))
                .build();

        return cinemaZ;
    }

    public static CinemaDTO convListCinemasToDto(Cinema cinema) {
        CinemaDTO cinemaDto = CinemaDTO.builder()
                .cinemaId(cinema.getCinemaId())
                .cinemaName(cinema.getCinemaName())
                .build();
        return cinemaDto;
    }





}
