package com.cinema.booking.controllers;

import com.cinema.booking.dtos.showInfoDto.*;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.mappers.ShowInfoReservationMapper;
import com.cinema.booking.dtos.showInfoDto.RepertoireDto;
import com.cinema.booking.services.infoService.ShowInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class InfoController {

    private final ShowInfoReservationMapper showInfoReservationMapper;
    private final ShowInfoService showInfoService;


    @GetMapping("/cinemas/{cinemaName}/repertoires")
    public List<RepertoireDto> showAllPlayingMovies(@PathVariable("cinemaName") String cinemaName) throws MovieNotFoundException {

        List<RepertoireDto> movieNameDtos = showInfoService
                .fetchAllPlayingMoviesInCinema(cinemaName);
        return movieNameDtos;
    }


    @GetMapping("/cinemas/{cinemaName}/movies/{movieName}/date-times")
    public List<DataDto> showDateChosenMovie(@PathVariable("cinemaName") String cinemaName,
                                             @PathVariable("movieName") String moviesName) throws MovieNotFoundException {

        List<DataDto> dataTimeMovie = showInfoService
                .fetchDateTimesChoosenMovie(cinemaName, moviesName);
        return dataTimeMovie;
    }

    @GetMapping("/cinemas/{cinemaName}/movies/{movieName}/avialable-seats")
    public List<AvailableSeatsInfoDto> showAvailableSeatsOnSeance(@PathVariable("cinemaName") String cinemaName,
                                                                  @PathVariable("movieName") String movieName) throws MovieNotFoundException {
        List<Movie> freePlacesOnMovie = showInfoService
                .fetchAvialableSeatsOnSeance(cinemaName, movieName);
        return showInfoReservationMapper.toAvailableSeatsInfoListDto(freePlacesOnMovie);
    }

    @GetMapping("/cinemas/{cinemaName}/movies/{movieName}/avialable-seats/date-times")
    public List<AvailableSeatsDto> showAvailableSeatsWithTimeOnSeance(@PathVariable("cinemaName") String cinemaName,
                                                                      @PathVariable("movieName") String movieName,
                                                                      @RequestParam("localDate")
                                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,
                                                                              pattern = "yyyy-MM-dd; HH:mm:ss")
                                                                              LocalDateTime localDateTime) throws MovieNotFoundException {
        List<Movie> freePlacesOnMovie = showInfoService
                .fetchAvailableSeatsWithDateTimeOnSeance(cinemaName, movieName, localDateTime);
        return showInfoReservationMapper.toAvailableSeatsListDto(freePlacesOnMovie);
    }


    @GetMapping("/cinemas/{cinemaName}/date-times")
    public List<BasicInfoAboutMovieDto> showAllFreePlacesTodayInCinema(@PathVariable("cinemaName") String cinemaName,
                                                                       @RequestParam("localDate")
                                                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,
                                                                               pattern = "yyyy-MM-dd; HH:mm:ss")
                                                                               LocalDateTime localDateTime) throws MovieNotFoundException {
        List<BasicInfoAboutMovieDto> movies = showInfoService
                .fetchFreeSeatsForSelectedDay(localDateTime, cinemaName);
        return movies;
    }
}
