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
    //before
    @GetMapping("/dates/cinemas/{cinemaName}/movies/{movieName}")
    public List<DataDto>showDateChosenMovie(@PathVariable("cinemaName")String cinemaName,
                                            @PathVariable("movieName")String moviesName) throws MovieNotFoundException {
        List<PropertiesMovie> dataTimeMovie = showInfoService.fetchDateTimesChosenMovie(cinemaName, moviesName);

        return showInfoReservationMapper.toDataDtoListMovie(dataTimeMovie);

    }

    //cinema/name/{cinemaName}/movie/name/{movieName}/free-places/date-times/
    @GetMapping("/findFreePlaces/cinemaName/{cinemaName}/movieName/{movieName}/date")
    public List<FreePlaceDto> showFreePlacesOnMovie(@PathVariable ("cinemaName")String cinemaName,
                                                    @PathVariable ("movieName") String movieName,
                                                    @RequestParam("localDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,
                                                            pattern = "yyyy-MM-dd; HH:mm:ss") LocalDateTime localDateTime) throws MovieNotFoundException {
        List<Movie> freePlacesOnMovie = showInfoService.fetchFreeSeatsOnMovie(cinemaName, movieName, localDateTime);
        return showInfoReservationMapper.toFreePlaceListDto(freePlacesOnMovie);
    }


    ////////////// chcesz bardzo isc do kina dzis dostepne wolne miejsca w dzisiajeszym dniu
    @GetMapping("/findByDateFree/{cinemaName}/date")
    public List<BasicInfoAboutMovieDto> showAllFreePlacesTodayInCinema(@PathVariable("cinemaName") String cinemaName,
                                                                       @RequestParam("localDate")
                                                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,
                                                                               pattern = "yyyy-MM-dd; HH:mm:ss") LocalDateTime localDateTime) throws MovieNotFoundException {
        List<com.cinema.booking.dtos.showInfoDto.BasicInfoAboutMovieDto> movies = showInfoService.fetchFreeSeatsForSelectedDay(localDateTime,cinemaName);
        return movies;
    }
}
