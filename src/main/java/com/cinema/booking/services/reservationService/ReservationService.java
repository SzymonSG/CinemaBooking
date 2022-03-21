package com.cinema.booking.services.reservationService;

import com.cinema.booking.entities.Movie;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.model.ReservationModel;


import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {
    List<Movie> multiBookedPlaceWithDate(String cinemaName, String movieName,String movieRoom, List<Integer> wantedSeats, LocalDateTime localDateTime) throws MovieNotFoundException;

    List<Movie> multiBookedPlaceWithDateV2(ReservationModel reservation) throws MovieNotFoundException;
}
