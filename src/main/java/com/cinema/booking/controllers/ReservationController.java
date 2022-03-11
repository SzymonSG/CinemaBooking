package com.cinema.booking.controllers;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.entities.PropertiesMovie;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.mapper.ReservationMapper;
import com.cinema.booking.mapstructDTO.*;
import com.cinema.booking.mapstructDTO.BasicInfoAboutMovieDto;
import com.cinema.booking.payloads.RepertoireDTO;
import com.cinema.booking.payloads.Reservation;
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

    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;
    private final ShowInfoService showInfoService;

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
        Reservation reservation = reservationMapper.dtoToReservation(reservationInfo);
        return reservationService.multiBookedPlaceWithDateV2(reservation);
    }

//+
    //1 Lista Filmów+
    //cinemas/{cinemaName}/repertoire
    //cienmas/{cinemaName}/repertoire
    //DISTINCT potrzebuje przerobienia na MovieNameDTO
    @GetMapping("/cinemas/{cinemaName}/movies")
    public List<RepertoireDTO>showAllPlayingMovies(@PathVariable("cinemaName")String cinemaName) throws MovieNotFoundException {

        List<RepertoireDTO> movieNameDtos = showInfoService.showAllPlayingMoviesInCinema(cinemaName);
        return movieNameDtos;


//        return reservationMapper.toMovieNamesListDto(movies);

    }
//+//java stream działa z movie
    @GetMapping("/cinemas/{cinemaName}/moviess")
    public List<RepertoireDto>showAllPlayingMoviesV2(@PathVariable("cinemaName")String cinemaName) throws MovieNotFoundException {
        List<Movie> movies = showInfoService.showAllPlayingMoviesInCinemaV2(cinemaName);
        return reservationMapper.toMovieNameListDto(movies);

    }

    //czy wyrzuić to w osobny kontroller, czy i tak samo dtos wyjebać w osobny controller

    //Kiedy grany jest mój film ?
    //2 Godzina i data filmu not work ale w innej wersji work+ (-)
    //cinema-names/{cinemaName}/movie-names/{movieName}/date-times
    @GetMapping("/dates/cinemas/{cinemaName}/movies/{movieName}")
    public List<DataDto>showDateChosenMovie(@PathVariable("cinemaName")String cinemaName,
                                            @PathVariable("movieName")String moviesName) throws MovieNotFoundException {
        List<PropertiesMovie> dataTimeMovie = showInfoService.showDateChosenMovie(cinemaName, moviesName);

        return reservationMapper.toDataDtoListMovie(dataTimeMovie);

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
        return reservationMapper.toFreePlaceListDto(freePlacesOnMovie);
    }


////////////// chcesz bardzo isc do kina dzis dostepne wolne miejsca w dzisiajeszym dniu
    @GetMapping("/findByDateFree/{cinemaName}/date")
    public List<BasicInfoAboutMovieDto> findAllFreePlacesTodayInCinema(@PathVariable("cinemaName") String cinemaName,
                                                                       @RequestParam("localDate")
                                                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,
                                                                               pattern = "yyyy-MM-dd; HH:mm:ss") LocalDateTime localDateTime) throws MovieNotFoundException {
        //dtos pakietowy
        List<BasicInfoAboutMovieDto> movies = showInfoService.showFreePlacesForSelectedDay(localDateTime,cinemaName);
        return movies;
    }

}
