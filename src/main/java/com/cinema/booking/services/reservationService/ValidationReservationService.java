package com.cinema.booking.services.reservationService;

import com.cinema.booking.entities.Movie;
import com.cinema.booking.exceptions.MovieNotFoundException;

import java.util.List;

public interface ValidationReservationService {


    public void whatPlaceAreBooked(List<Movie> foundPlaces) throws MovieNotFoundException;

    public void checkFoundPlacesAreBooked(List<Movie> foundPlaces) throws MovieNotFoundException;

    public List<Movie> foundWantedPlaces (List<Integer> wantedPlaces, List<Movie> seance);

    public void checkGivenPlacesExist (List<Integer> wantedPlaces, List <Movie> seance) throws MovieNotFoundException;



}