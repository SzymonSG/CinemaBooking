package com.cinema.booking.service.ServiceInterfaces;

import com.cinema.booking.entities.Movie;
import com.cinema.booking.exceptions.MovieNotFoundException;

import java.util.List;

public interface ValidationInterface {


    public void whatplaceAreBooked(List<Movie> foundPlaces) throws MovieNotFoundException;
    public void checkFoundPlacesAreBooked(List<Movie> foundPlaces) throws MovieNotFoundException;
    public List<Movie> foundWantedPlaces (List<Integer> wantedPlaces, List<Movie> seance);
    public void checkGivenPlacesExist (List<Integer> wantedPlaces, List <Movie> seance) throws MovieNotFoundException;



}
