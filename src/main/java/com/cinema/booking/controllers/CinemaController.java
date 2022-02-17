package com.cinema.booking.controllers;
import com.cinema.booking.entities.Cinema;
import com.cinema.booking.mapper.CinemaMapStruct;
import com.cinema.booking.mapstructDTO.CinemaDto;
import com.cinema.booking.mapstructDTO.CinemaWithMovieDto;
import com.cinema.booking.repository.CinemaRepository;
import com.cinema.booking.service.ServiceInterfaces.CinemaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CinemaController {


    private final CinemaService cinemaService;
    private final CinemaRepository cinemaRepository;
    private final CinemaMapStruct cinemaMapStruct;


    ///"/cinemas"
    @PostMapping("/cinemas")
    public Cinema cinemaSave(@Valid @RequestBody CinemaDto cinemaDto){
        log.info("Saved inside cinama Saved method");
        Cinema cinema = cinemaMapStruct.dtoToCinema(cinemaDto);
        return cinemaService.cinemaSave(cinema);
    }
    //cinemas-(filmy)
    // /articles?include=author
    //"cinemas?include=movies
    @GetMapping("/cinemas:include=movies")
    public List<CinemaWithMovieDto> fetchCinemaListDto(){
        return cinemaMapStruct.toCinemaWithMovieListDto(cinemaService.fetchCinemasList());
    }


//    @GetMapping("/names/{name}/rooms/{room}")
//    List<Movie> fetchujpoImieniuIROMMIE(@PathVariable("name") String movieName,
//                                        @PathVariable ("room") String movieRoom){
//        return cinemaService.fetchMoviesByNamesAndRoomsList(movieName,movieRoom);
//    }
//
//
//    @GetMapping("/getpower/{cin}/{movie}")
//    List<Cinema> getPower(@PathVariable ("cin") String nameCinema, @PathVariable ("movie") String movie){
//        return cinemaRepository.getdatabyCinemaAndMovie(nameCinema, movie);
//    }


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
