package com.cinema.booking.services.reservationService;

import com.cinema.booking.entities.Movie;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.validators.BookingModelValidator;


import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {
    List<Movie> multiBookedPlaceWithDate(String cinemaName, String movieName,String movieRoom, List<Integer> wantedSeats, LocalDateTime localDateTime) throws MovieNotFoundException;

    List<Movie> bookingSeats(BookingModelValidator correctModel) throws MovieNotFoundException;
}
