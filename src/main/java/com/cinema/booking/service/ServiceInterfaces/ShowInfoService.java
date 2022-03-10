package com.cinema.booking.service.ServiceInterfaces;

import com.cinema.booking.entities.Movie;
import com.cinema.booking.entities.PropertiesMovie;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.mapstructDTO.BasicInfoAboutMovieDto;
import com.cinema.booking.mapstructDTO.RepertoireDto;
import com.cinema.booking.payloads.RepertoireDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowInfoService {
    List<RepertoireDTO> showAllPlayingMoviesInCinema(String cinemaName) throws MovieNotFoundException;

    List<Movie> findFreePlacesOnMovie(String cinemaName, String movieName, LocalDateTime localDateTime) throws MovieNotFoundException;

    List<PropertiesMovie> showDateChosenMovie(String cinemaName, String movieName) throws MovieNotFoundException;

    //TODO Consider
    List<BasicInfoAboutMovieDto> showFreePlacesForSelectedDay(LocalDateTime localDateTime, String cinemaName) throws MovieNotFoundException;

    List<Movie> showAllPlayingMoviesInCinemaV2(String cinemaName) throws MovieNotFoundException;
}

