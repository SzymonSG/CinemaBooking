package com.cinema.booking.controllers;

import com.cinema.booking.dtos.showInfoDto.BasicInfoAboutMovieDto;
import com.cinema.booking.dtos.showInfoDto.DataDto;
import com.cinema.booking.dtos.showInfoDto.FreePlaceDto;
import com.cinema.booking.dtos.showInfoDto.RepertoireDto;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.entities.PropertiesMovie;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.mappers.ShowInfoReservationMapper;
import com.cinema.booking.payloads.RepertoireDTO;
import com.cinema.booking.services.infoService.ShowInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

//@RequestMapping("/cinemas/{cinemaName}")
@RequiredArgsConstructor
@RestController
public class InfoController {

    private final ShowInfoReservationMapper showInfoReservationMapper;
    private final ShowInfoService showInfoService;



    @GetMapping("/cinemas/{cinemaName}/repertoires")
    public List<RepertoireDTO> showAllPlayingMovies(@PathVariable("cinemaName")String cinemaName) throws MovieNotFoundException {

        List<RepertoireDTO> movieNameDtos = showInfoService.fetchAllPlayingMoviesInCinema(cinemaName);
        return movieNameDtos;
    }

    @GetMapping("/cinemas/{cinemaName}/repertoiress")
    public List<RepertoireDto>showAllPlayingMoviesV2(@PathVariable("cinemaName")String cinemaName) throws MovieNotFoundException {
        List<Movie> movies = showInfoService.fetchAllPlayingMoviesInCinemaV2(cinemaName);
        return showInfoReservationMapper.toMovieNameListDto(movies);

    }

    //before to jest chyba ok ale przekształć pod postgreSQL bo to chyba nie pójdzie na psql, jesli byłby 2 daty dla jednego filmu
    @GetMapping("/cinemas/{cinemaName}/movies/{movieName}/date-times")
    public List<DataDto>showDateChosenMovie(@PathVariable("cinemaName")String cinemaName,
                                            @PathVariable("movieName")String moviesName) throws MovieNotFoundException {
        List<PropertiesMovie> dataTimeMovie = showInfoService.fetchDateTimesChosenMovie(cinemaName, moviesName);

        return showInfoReservationMapper.toDataDtoListMovie(dataTimeMovie);

    }

    @GetMapping("/cinemas/{cinemaName}/movies/{movieName}/avialable-seats/date-times")
    public List<FreePlaceDto> showFreePlacesOnMovie(@PathVariable ("cinemaName")String cinemaName,
                                                    @PathVariable ("movieName") String movieName,
                                                    @RequestParam("localDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,
                                                            pattern = "yyyy-MM-dd; HH:mm:ss") LocalDateTime localDateTime) throws MovieNotFoundException {
        List<Movie> freePlacesOnMovie = showInfoService.fetchFreeSeatsOnMovie(cinemaName, movieName, localDateTime);
        return showInfoReservationMapper.toFreePlaceListDto(freePlacesOnMovie);
    }
    //
    //cinemas/cinema{cinemaName}/dates-times/avialable-seats
    ////////////// chcesz bardzo isc do kina dzis dostepne wolne miejsca w dzisiajeszym dniu w wybranym kinie
    @GetMapping("/cinemas/{cinemaName}/date-times")
    public List<BasicInfoAboutMovieDto> showAllFreePlacesTodayInCinema(@PathVariable("cinemaName") String cinemaName,
                                                                       @RequestParam("localDate")
                                                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,
                                                                               pattern = "yyyy-MM-dd; HH:mm:ss") LocalDateTime localDateTime) throws MovieNotFoundException {
        List<BasicInfoAboutMovieDto> movies = showInfoService.fetchFreeSeatsForSelectedDay(localDateTime,cinemaName);
        return movies;
    }
}
