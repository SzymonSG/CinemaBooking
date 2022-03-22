package com.cinema.booking.controllers;
import com.cinema.booking.dtos.reservationDto.ReservationModelDto;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.mappers.ReservationMapper;
import com.cinema.booking.model.ReservationModel;
import com.cinema.booking.services.reservationService.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;


@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;

    @PutMapping("/cinemaName/{cinemaName}/movieName/{movieName}/movieRoom/{movieRoom}/date")
    public List<Movie> multiBookedPlaceWithDate( @PathVariable("cinemaName")String cinemaName,
                                                 @PathVariable("movieName")String movieName,
                                                 @PathVariable("movieRoom")String movieRoom,
                                                 @RequestBody List<Integer> wantedPlaces,
                                                 @RequestParam("localDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,
                                                 pattern = "yyyy-MM-dd; HH:mm:ss") LocalDateTime localDateTime) throws MovieNotFoundException {
        return reservationService.multiBookedPlaceWithDate(cinemaName,movieName,movieRoom,wantedPlaces,localDateTime);
    }

    @PutMapping("/reservations")
    public List<Movie> multiBookedPlaceWithDates(@Valid @RequestBody ReservationModelDto reservationInfo) throws MovieNotFoundException {
        ReservationModel reservation = reservationMapper.dtoToReservation(reservationInfo);
        return reservationService.multiBookedPlaceWithDateV2(reservation);
    }





}
