package com.cinema.booking.controllers;
import com.cinema.booking.entities.Cinema;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.mapper.CinemaMapStruct;
import com.cinema.booking.mapstructDTO.CinemaDTO;
import com.cinema.booking.repository.CinemaRepository;
import com.cinema.booking.repository.MovieRepository;
import com.cinema.booking.repository.PropertiesMovieRepository;
import com.cinema.booking.service.CinemaService;
import com.cinema.booking.service.MovieService;
import com.cinema.booking.service.PropertiesMovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;




@Slf4j
@RequiredArgsConstructor
@RestController
public class CinemaController {


    private final MovieService movieService;
    private final CinemaService cinemaService;
    private final PropertiesMovieService propertyMovieService;
    private final CinemaRepository cinemaRepository;
    private final MovieRepository movieRepository;
    private final PropertiesMovieRepository propertiesMovieRepository;
    private final CinemaMapStruct cinemaMapper;


    @PostMapping("/save/cinema")
    Cinema cinemaSave(@Valid @RequestBody Cinema cinemaDepartment){
        log.info("Saved inside cinama Saved method");
        return cinemaService.cinemaDepartmentSave(cinemaDepartment);
    }
//
    @PostMapping("/save/cinemaa")
    Cinema cinemaSaveewithDTO(@Valid @RequestBody CinemaDTO cinemaDTO){
        Cinema cinema = cinemaMapper.dtoToCinema(cinemaDTO);
        return cinemaService.cinemaDepartmentSave(cinema);
        //return cinemaService.cinemaDepartmentSave(cinema);
    }


    @GetMapping("/cinemas")
    List<Cinema> fetchCinemaList(){
        return cinemaService.fetchCinemasList();
    }


    @GetMapping("/names/{name}/rooms/{room}")
    List<Movie> fetchujpoImieniuIROMMIE(@PathVariable("name") String movieName,
                                        @PathVariable ("room") String movieRoom){
        return cinemaService.fetchMoviesByNamesAndRoomsList(movieName,movieRoom);
    }


    @GetMapping("/getpower/{cin}/{movie}")
    List<Cinema> getPower(@PathVariable ("cin") String nameCinema, @PathVariable ("movie") String movie){
        return cinemaRepository.getdatabyCinemaAndMovie(nameCinema, movie);
    }


    //@GetMapping("/mapstruct/cinemas")
    //public List<CinemaDTO> showCinemaDto(){
    //    return cinemaMapper.toCinemaListDto(cinemaService.fetchCinemasList());
    //}


    ///DTOs to Entity in our UPDATE method
    //RequestBody ProdcutDTO to Save but we have path variable i to nie pójdzie raczej
//    @PutMapping("/cinemaNamee/{cinemaName}/movieNamee/{movieName}")
//    public List<Movie> multiBookedPlace2(@PathVariable("cinemaName")String cinemaName,
//                                         @PathVariable("movieName")String movieName,
//                                         @RequestBody List<Integer> wantedPlaces) throws MovieNotFoundException {
//
//        //tutaj ta metoda powinna zwracać dto i to złatwi sprawę ale powinenem mieć drugą taką "samą" metodę,
//        //umożliwająca zapis na encji
//        List<MovieDTO> movieDTOS = movieService.multiBookedPlaceDto(cinemaName, movieName, wantedPlaces);
//        return cinemaMapper.dtoToMovieEntitiesList(movieDTOS);
//    }



    //dodanie ładnych controllerów z DTO


}
