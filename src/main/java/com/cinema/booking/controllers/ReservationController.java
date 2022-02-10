package com.cinema.booking.controllers;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.mapper.CinemaMapStruct;
import com.cinema.booking.mapstructDTO.ComplexMovieDTO;
import com.cinema.booking.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReservationController {

    private final MovieService movieService;
    private final CinemaMapStruct cinemaMapper;

//    @PutMapping("/getpower/{cin}/{movie}")
//    List<Movie> booked(@PathVariable("cin") String nameCinema, @PathVariable ("movie") String movie){
//        return movieService.bookedUpdate(nameCinema,movie);
//    }
//
//    @PutMapping("/cinemaName/{cinemaName}/movieName/{movieName}")
//    public List<Movie> multiBookedPlace( @PathVariable("cinemaName")String cinemaName,
//                                         @PathVariable("movieName")String movieName,
//                                         @RequestBody List<Integer> wantedPlaces) throws MovieNotFoundException {
//        return movieService.multiBookedPlace(cinemaName,movieName,wantedPlaces);
//    }

//Co dokładnie daje @DateTimeFormat przeczytaj pozwala przekształcić paramter string do localdate
// wraz z custom patternem
//co daje JsonFormat
//I czy konieczne jest globalne serializabale i deserializable daty
    @PutMapping("/cinemaName/{cinemaName}/movieName/{movieName}/date")
    public List<Movie> multiBookedPlacewithDate( @PathVariable("cinemaName")String cinemaName,
                                                 @PathVariable("movieName")String movieName,
                                                 @RequestBody List<Integer> wantedPlaces,
                                                 @RequestParam("localDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,
                                                         pattern = "yyyy-MM-dd; HH:mm:ss") LocalDateTime localDateTime) throws MovieNotFoundException {
        return movieService.multiBookedPlaceWithDate(cinemaName,movieName,wantedPlaces,localDateTime);
    }


    ///DTO VERSION RESERVATION
    @PutMapping("/cinemaName/{cinemaName}/movieName/{movieName}/datee")
    public List<Movie> multiBookedPlacewithDateeee( @PathVariable("cinemaName")String cinemaName,
                                                  @PathVariable("movieName")String movieName,
                                                  @RequestBody List<Integer> wantedPlaces,
                                                  @RequestParam("localDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,
                                                          pattern = "yyyy-MM-dd; HH:mm:ss") LocalDateTime localDateTime) throws MovieNotFoundException {
        List<Movie> movies = movieService.multiBookedPlaceWithDate(cinemaName, movieName, wantedPlaces, localDateTime);
        List<ComplexMovieDTO> complexMovieDTOS = cinemaMapper.toComplexListMovieDto(movies);
        List<Movie> moviesAgain = cinemaMapper.dtoToComplexMovieList(complexMovieDTOS);
        return moviesAgain;
    }

    @GetMapping("/findByDate")
    public List<ComplexMovieDTO> findbyDateTimeShowMovie(@RequestParam("localDate")
                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,
                                                       pattern = "yyyy-MM-dd; HH:mm:ss") LocalDateTime localDate){
        movieService.findByDateQuery(localDate);
        return cinemaMapper.toComplexListMovieDto(movieService.findByDateQuery(localDate));
    }
    // do dorobienia
//    @GetMapping("/cinemaPlayingMovies/{cinemaName}")
//    public List<String> showAllPlayingMovies(@PathVariable ("cinemaName")String cinemaName){
//        return movieService.showAllPlayingMovies(cinemaName);
//    }
//
//    @GetMapping("/findFreePlacesOnMovie/cinemaName/{cinemaName}/movieName/{movieName}")
//    public List<InfoMoviesDTO> findFreeMoviesInCinema(@PathVariable ("cinemaName")String cinemaName, @PathVariable ("movieName") String movieName){
//        return movieService.findFreePlacesOnMovie(cinemaName,movieName);
//    }

    //gropuingby for filter add only, + doczytanie o Desrailizer and Serializer Date

}
