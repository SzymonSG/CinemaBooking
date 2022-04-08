package com.cinema.booking.services.reservationService;

import com.cinema.booking.entities.Movie;
import com.cinema.booking.exceptions.MovieNotFoundException;

import java.util.List;
import java.util.Optional;

public interface BookingValidators {


    void showInfoAboutBooked(List<Movie> foundPlaces) throws MovieNotFoundException;

    boolean isBookedSeats(List<Movie> foundPlaces) throws MovieNotFoundException;

    List<Movie> filteringSeatsToBooked(List<Integer> wantedPlaces, List<Movie> seance);




}
