package com.cinema.booking.services.reservationService;

import com.cinema.booking.entities.Movie;
import com.cinema.booking.exceptions.MovieNotFoundException;

import java.util.List;

public interface ValidationReservationService {


    void checkBookedSeats(List<Movie> foundPlaces) throws MovieNotFoundException;

    void checkFoundSeatsAreBooked(List<Movie> foundPlaces) throws MovieNotFoundException;

    List<Movie> findWantedSeats(List<Integer> wantedPlaces, List<Movie> seance);

    void checkSeatsToBookedExist(List<Integer> wantedPlaces, List <Movie> seance) throws MovieNotFoundException;


}
