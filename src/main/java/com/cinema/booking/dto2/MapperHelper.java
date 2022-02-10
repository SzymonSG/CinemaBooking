package com.cinema.booking.dto2;


import com.cinema.booking.dto.MovieBez;
import com.cinema.booking.entities.Movie;

public class MapperHelper {


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
}