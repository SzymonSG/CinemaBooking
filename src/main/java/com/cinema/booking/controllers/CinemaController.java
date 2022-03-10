package com.cinema.booking.controllers;
import com.cinema.booking.entities.Cinema;
import com.cinema.booking.mapper.CinemaMapStruct;
import com.cinema.booking.mapper.CinemaMapper;
import com.cinema.booking.mapstructDTO.CinemaDto;
import com.cinema.booking.mapstructDTO.CinemaWithMovieDto;
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
    private final CinemaMapStruct cinemaMapStruct;
    private final CinemaMapper cinemaMapper;


    ///"/cinemas"
    @PostMapping("/cinemas")
    public Cinema cinemaSave(@Valid @RequestBody CinemaDto cinemaDto){
        log.info("Saved inside cinama Saved method");
        Cinema cinema = cinemaMapper.dtoToCinema(cinemaDto);
        return cinemaService.cinemaSave(cinema);
    }
    //cinemas-(filmy)
    // /articles?include=author
    //"cinemas?include=movies
    @GetMapping("/cinemas:include=movies")
    public List<CinemaWithMovieDto> fetchCinemaListDto(){
        return cinemaMapStruct.toCinemaWithMovieListDto(cinemaService.fetchCinemasList());

    }


}
