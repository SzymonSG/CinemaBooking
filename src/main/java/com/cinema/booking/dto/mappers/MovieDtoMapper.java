package com.cinema.booking.dto.mappers;



import com.cinema.booking.dto.MovieBez;
import com.cinema.booking.dto.MovieDTO;
import com.cinema.booking.entities.Movie;

import java.util.stream.Collectors;

public class MovieDtoMapper {
    // w przypadku zwracanie filmów to bez kolekcji cinemasDTO,//a w przypadku zwracania
    public static MovieDTO movieWithCinemasToDto(Movie movie){
        MovieDTO movieDto = MovieDTO.builder()
                .movieId(movie.getMovieId())
                .movieName(movie.getMovieName())
                .movieRoom(movie.getMovieRoom())
                .booked(movie.getBooked())
                .seating(movie.getSeating())
                .cinemasDto(movie.getCinemas()
                        .stream()
                        .map(CinemaDtoMapper::convListCinemasToDto)
                        .collect(Collectors.toList()))
                .build();
        return movieDto;
    }

    public static MovieBez convToMovieBez(Movie movie){

        MovieBez movieBez = MovieBez.builder()
                .movieId(movie.getMovieId())
                .movieName(movie.getMovieName())
                .movieRoom(movie.getMovieRoom())
                .booked(movie.getBooked())
                .seating(movie.getSeating())
                .build();

        return movieBez;

    }
    //public static MovieBez mapToDto(Movie movie){
    //    MovieBez movieDto = MovieBez.builder()
    //            .movieId(movie.getMovieId())
    //            .movieName(movie.getMovieName())
    //            .movieRoom(movie.getMovieRoom())
    //            .booked(movie.getBooked())
    //            .seating(movie.getSeating())
    //            .build();
    //    return movieDto;
    //}


    //metody do zamiany dto na encji


}
