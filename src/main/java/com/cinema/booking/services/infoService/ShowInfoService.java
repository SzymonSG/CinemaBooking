package com.cinema.booking.services.infoService;

import com.cinema.booking.entities.Movie;
import com.cinema.booking.entities.PropertiesMovie;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.dtos.showInfoDto.BasicInfoAboutMovieDto;
import com.cinema.booking.payloads.RepertoireDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowInfoService {
    //movie
    List<RepertoireDTO> showAllPlayingMoviesInCinema(String cinemaName) throws MovieNotFoundException;
    //movie
    List<Movie> findFreePlacesOnMovie(String cinemaName, String movieName, LocalDateTime localDateTime) throws MovieNotFoundException;
    //properties
    List<PropertiesMovie> showDateChosenMovie(String cinemaName, String movieName) throws MovieNotFoundException;
    //movie
    //TODO Consider
    List<BasicInfoAboutMovieDto> showFreePlacesForSelectedDay(LocalDateTime localDateTime, String cinemaName) throws MovieNotFoundException;
    //movie
    List<Movie> showAllPlayingMoviesInCinemaV2(String cinemaName) throws MovieNotFoundException;
}

