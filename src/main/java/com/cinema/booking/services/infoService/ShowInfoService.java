package com.cinema.booking.services.infoService;

import com.cinema.booking.entities.Movie;
import com.cinema.booking.entities.PropertiesMovie;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.dtos.showInfoDto.BasicInfoAboutMovieDto;
import com.cinema.booking.dtos.RepertoireDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowInfoService {

    List<RepertoireDTO> fetchAllPlayingMoviesInCinema(String cinemaName) throws MovieNotFoundException;

    List<Movie> fetchAllPlayingMoviesInCinemaV2(String cinemaName) throws MovieNotFoundException;

    List<PropertiesMovie> fetchDateTimesChoosenMovie(String cinemaName, String movieName) throws MovieNotFoundException;

    List<Movie> fetchAvailableSeatsWithDateTimeOnSeance(String cinemaName, String movieName, LocalDateTime localDateTime) throws MovieNotFoundException;

    List<BasicInfoAboutMovieDto> fetchFreeSeatsForSelectedDay(LocalDateTime localDateTime, String cinemaName) throws MovieNotFoundException;

    List<Movie> fetchAvialableSeatsOnSeance(String cinemaName, String movieName) throws MovieNotFoundException;
}

