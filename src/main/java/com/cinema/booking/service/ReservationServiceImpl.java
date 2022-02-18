package com.cinema.booking.service;

import com.cinema.booking.entities.Movie;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.repository.MovieRepository;
import com.cinema.booking.service.ServiceInterfaces.ReservationService;
import com.cinema.booking.service.ServiceInterfaces.ValidationInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService, ValidationInterface {

    private final MovieRepository movieRepository;

    @Transactional
    @Override
    public List<Movie> multiBookedPlaceWithDate(String cinemaName, String movieName, List<Integer> wantedPlaces, LocalDateTime localDateTime) throws MovieNotFoundException {
        List<Movie> seance = movieRepository.getDataCollectionToReservation(
                cinemaName,movieName,localDateTime);

        if (seance.isEmpty() || seance.contains(null)) {
            throw new MovieNotFoundException("No such seance was found");
        } else {
            List<Movie> foundPlaces = ComplexValidation(wantedPlaces, seance);
            foundPlaces.forEach(toBooked -> toBooked.setBooked("BOOKED"));
            return movieRepository.saveAll(foundPlaces);
        }
    }

    private List<Movie> ComplexValidation(List<Integer> wantedPlaces, List<Movie> seance) throws MovieNotFoundException {
        checkGivenPlacesExist(wantedPlaces, seance);
        List<Movie> foundPlaces = foundWantedPlaces(wantedPlaces, seance);
        checkFoundPlacesAreBooked(foundPlaces);
        return foundPlaces;
    }

    @Override
    public void whatplaceAreBooked(List<Movie> foundPlaces) throws MovieNotFoundException {
        List<Integer> bookedPlaces = foundPlaces.stream()
                .filter(booked -> booked.getBooked()
                        .equals("BOOKED"))
                .map(Movie::getSeating)
                .collect(Collectors.toList());
        String info = "booked";
        throw new MovieNotFoundException(bookedPlaces,info);
    }

    @Override
    public void checkFoundPlacesAreBooked(List<Movie> foundPlaces) throws MovieNotFoundException {
        boolean somePlaceIsCanBeBooked = foundPlaces.stream()
                .anyMatch(booked -> booked.getBooked().equals("BOOKED")); // lub any match
        if (somePlaceIsCanBeBooked){
            whatplaceAreBooked(foundPlaces);
        }
    }

    @Override
    public List<Movie> foundWantedPlaces(List<Integer> wantedPlaces, List<Movie> seance) {
        List<Movie> foundPlaces = seance.stream()
                .filter(orginal -> wantedPlaces.contains(orginal.getSeating()))
                .collect(Collectors.toList());
        return foundPlaces;
    }

    @Override
    public void checkGivenPlacesExist(List<Integer> wantedPlaces, List<Movie> seance) throws MovieNotFoundException {
        if (wantedPlaces != null && !wantedPlaces.isEmpty() && !wantedPlaces.contains(null)) {
            List<Integer> collect = seance
                    .stream()
                    .map(Movie::getSeating)
                    .collect(Collectors.toList());
            boolean checkPlacesExist = collect.containsAll(wantedPlaces); // contain czy zawiera mniejszy w większym
            if (!checkPlacesExist) {
                throw new MovieNotFoundException(wantedPlaces);
            }
        }else {
            throw new MovieNotFoundException("No seats were given for booking");
        }
    }
}
