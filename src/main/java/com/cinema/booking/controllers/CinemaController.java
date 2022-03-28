package com.cinema.booking.controllers;
import com.cinema.booking.entities.Cinema;
import com.cinema.booking.exceptions.CinemaNotFoundException;
import com.cinema.booking.mappers.CinemaMapStruct;
import com.cinema.booking.mappers.CinemaMapper;
import com.cinema.booking.dtos.cinemaDto.CinemaDto;
import com.cinema.booking.dtos.connectionDomainDto.CinemaWithMovieDto;
import com.cinema.booking.services.cinemaService.CinemaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CinemaController {


    private final CinemaService cinemaService;

    private final CinemaMapStruct cinemaMapStruct;

    private final CinemaMapper cinemaMapper;


    @PostMapping("/cinemas")
    public Cinema cinemaSave(@Valid @RequestBody CinemaDto cinemaDto) {
        log.info("Saved inside cinama Saved method");
        Cinema cinema = cinemaMapper.dtoToCinema(cinemaDto);
        return cinemaService.cinemaSave(cinema);
    }

    @GetMapping("/cinemas:include=movies")
    public List<CinemaWithMovieDto> fetchCinemaListDto(){
        return cinemaMapStruct.toCinemaWithMovieListDto(cinemaService.fetchCinemasList());

    }


}
