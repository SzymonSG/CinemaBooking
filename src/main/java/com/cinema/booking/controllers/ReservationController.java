package com.cinema.booking.controllers;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.entities.PropertiesMovie;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.mapper.CinemaMapStruct;
import com.cinema.booking.mapstructDTO.*;
import com.cinema.booking.mapstructDTO.reservationDTO.BasicInfoAboutMovie;

import com.cinema.booking.payloads.MovieName;
import com.cinema.booking.payloads.Reservation;
import com.cinema.booking.service.ServiceInterfaces.MovieService;
import com.cinema.booking.service.ServiceInterfaces.ReservationService;
import com.cinema.booking.service.ServiceInterfaces.ShowInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReservationController {

    private final MovieService movieService;
    private final ReservationService reservationService;
    private final CinemaMapStruct cinemaMapStruct;
    private final ShowInfoService showInfoService;

//+
    //boooking/cinema-names/{cinema-name}/movie-names/{movie-name}/date-times
    @PutMapping("/cinemaName/{cinemaName}/movieName/{movieName}/date")
    public List<Movie> multiBookedPlaceWithDate( @PathVariable("cinemaName")String cinemaName,
                                                 @PathVariable("movieName")String movieName,
                                                 @RequestBody List<Integer> wantedPlaces,
                                                 @RequestParam("localDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,
                                                 pattern = "yyyy-MM-dd; HH:mm:ss") LocalDateTime localDateTime) throws MovieNotFoundException {
        return reservationService.multiBookedPlaceWithDate(cinemaName,movieName,wantedPlaces,localDateTime);
    }
//+
    @PutMapping("/reservations")
    public List<Movie> multiBookedPlaceWithDates(@RequestBody ReservationDto reservationInfo) throws MovieNotFoundException {
        Reservation reservation = cinemaMapStruct.dtoToReservation(reservationInfo);
        return reservationService.multiBookedPlaceWithDateV2(reservation);
    }

//+
    //1 Lista Filmów+
    //cinemas/{cinemaName}/repertoire
    //cienmas/{cinemaName}/repertoire
    //DISTINCT potrzebuje przerobienia na MovieNameDTO
    @GetMapping("/cinemas/{cinemaName}/movies")
    public List<MovieNameDto>showAllPlayingMovies(@PathVariable("cinemaName")String cinemaName) throws MovieNotFoundException {
        List<MovieName> movies = showInfoService.showAllPlayingMoviesInCinema(cinemaName);
//        return cinemaMapStruct.to(movies);
        return cinemaMapStruct.toMovieNamesListDto(movies);
    }
//+//java stream działa z movie
    @GetMapping("/cinemas/{cinemaName}/moviess")
    public List<MovieNameDto>showAllPlayingMoviesV2(@PathVariable("cinemaName")String cinemaName) throws MovieNotFoundException {
        List<Movie> movies = showInfoService.showAllPlayingMoviesInCinemaV2(cinemaName);
        return cinemaMapStruct.toMovieNameListDto(movies);
        //return null;
    }


    //Kiedy grany jest mój film ?
    //2 Godzina i data filmu not work ale w innej wersji work+ (-)
    //cinema-names/{cinemaName}/movie-names/{movieName}/date-times
    @GetMapping("/dates/cinemas/{cinemaName}/movies/{movieName}")
    public List<DataDto>showDateChosenMovie(@PathVariable("cinemaName")String cinemaName,
                                            @PathVariable("movieName")String moviesName) throws MovieNotFoundException {
        List<PropertiesMovie> dataTimeMovie = showInfoService.showDateChosenMovie(cinemaName, moviesName);

        return cinemaMapStruct.toDataDtoListMovie(dataTimeMovie);
//        return cinemaMapStruct.toPropertiesMovieListDto(dataTimeMovie);
    }

//+
    //3.wolne miejsca na konkrenty film (moj)
    //cinema/name/{cinemaName}/movie/name/{movieName}/free-places/date-times/
    @GetMapping("/findFreePlaces/cinemaName/{cinemaName}/movieName/{movieName}/date")
    public List<FreePlaceDto> findFreePlacesOnMovie(@PathVariable ("cinemaName")String cinemaName,
                                                    @PathVariable ("movieName") String movieName,
                                                    @RequestParam("localDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,
                                                    pattern = "yyyy-MM-dd; HH:mm:ss") LocalDateTime localDateTime) throws MovieNotFoundException {
        List<Movie> freePlacesOnMovie = showInfoService.findFreePlacesOnMovie(cinemaName, movieName, localDateTime);
        return cinemaMapStruct.toFreePlaceListDto(freePlacesOnMovie);
    }



////////////// chcesz bardzo isc do kina dzis dostepne wolne miejsca w dzisiajeszym dniu
//+  wyrzuć to zrób to samo tylko
   //TODO gdzie mamy wolne miejsca dzisiaj w  całym  kinie, to w sumie nie koniecznnie bo to mogą być wrażliwe info
    @GetMapping("/findByDateFree/{cinemaName}/date")
    public List<BasicInfoAboutMovie> findAllFreePlacesTodayInCinema(@PathVariable("cinemaName") String cinemaName,
                                                                    @RequestParam("localDate")
                                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,
                                                                    pattern = "yyyy-MM-dd; HH:mm:ss") LocalDateTime localDateTime) throws MovieNotFoundException {
        //dtos pakietowy
        List<BasicInfoAboutMovie> movies = showInfoService.showFreePlacesForSelectedDay(localDateTime,cinemaName);
        return movies;
    }

}
