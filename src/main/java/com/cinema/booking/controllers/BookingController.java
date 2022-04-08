package com.cinema.booking.controllers;
import com.cinema.booking.dtos.bookingDto.BookingModelDto;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.exceptions.CinemaNotFoundException;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.exceptions.PropertyMovieNotFoundException;
import com.cinema.booking.mappers.BookingMapper;
import com.cinema.booking.model.BookingModel;
import com.cinema.booking.services.reservationService.BookingService;
import com.cinema.booking.validators.BookingModelValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BookingController {

    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    @PutMapping("/cinemaName/{cinemaName}/movieName/{movieName}/movieRoom/{movieRoom}/date")
    public List<Movie> multiBookedPlaceWithDate(@PathVariable("cinemaName")String cinemaName,
                                                @PathVariable("movieName")String movieName,
                                                @PathVariable("movieRoom")String movieRoom,
                                                @RequestBody List<Integer> wantedPlaces,
                                                @RequestParam("localDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,
                                                 pattern = "yyyy-MM-dd; HH:mm:ss") LocalDateTime localDateTime) throws MovieNotFoundException {
        return bookingService.multiBookedPlaceWithDate(cinemaName,movieName,movieRoom,wantedPlaces,localDateTime);
    }

    private final BookingModelValidator bookingRequestValidator;
    @PutMapping("/reservations")
    public List<Movie> bookingSeats(@Valid @RequestBody BookingModelDto bookingModelDto) throws MovieNotFoundException, PropertyMovieNotFoundException, CinemaNotFoundException {
        BookingModel booking = bookingMapper.dtoToReservation(bookingModelDto);
        BookingModelValidator correctBookingModel = bookingRequestValidator.buildCorrectModel(booking);
        log.info(correctBookingModel.getCinemaName()+" , " + ""+correctBookingModel.getMovieName()+" , " +
                ""+correctBookingModel.getSeatsToBooked()+" , "+correctBookingModel.getMovieRoom());

        return bookingService.bookingSeats(correctBookingModel);
    }


}
