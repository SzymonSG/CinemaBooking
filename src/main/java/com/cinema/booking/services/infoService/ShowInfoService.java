package com.cinema.booking.services.infoService;

import com.cinema.booking.dtos.showInfoDto.DataDto;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.dtos.showInfoDto.BasicInfoAboutMovieDto;
import com.cinema.booking.dtos.showInfoDto.RepertoireDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowInfoService {

    List<RepertoireDto> fetchAllPlayingMoviesInCinema(String cinemaName) throws MovieNotFoundException;

    List<DataDto> fetchDateTimesChoosenMovie(String cinemaName, String movieName) throws MovieNotFoundException;

    List<Movie> fetchAvailableSeatsWithDateTimeOnSeance(String cinemaName, String movieName, LocalDateTime localDateTime) throws MovieNotFoundException;

    List<BasicInfoAboutMovieDto> fetchFreeSeatsForSelectedDay(LocalDateTime localDateTime, String cinemaName) throws MovieNotFoundException;

    List<Movie> fetchAvialableSeatsOnSeance(String cinemaName, String movieName) throws MovieNotFoundException;
}

