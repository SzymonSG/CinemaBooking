package com.cinema.booking.service.ServiceInterfaces;

import com.cinema.booking.entities.Movie;
import com.cinema.booking.exceptions.MovieNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {
    List<Movie> multiBookedPlaceWithDate(String cinemaName, String movieName, List<Integer> wantedPlaces, LocalDateTime localDateTime) throws MovieNotFoundException;
}
