package com.cinema.booking.validators;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.exceptions.MovieNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import static com.cinema.booking.common.ReservationCheck.BOOKED;


@Component
public class SeatsBookingValidatorComp {

    //TODO WYDZIEL DO KLASY VALIDACJA REZERWACJI
    public  List<Movie> seatsValidation(List<Integer> seatsToBooked, List<Movie> seance) throws MovieNotFoundException {
        List<Movie> foundSeats = filteringSeatsToBooked(seatsToBooked, seance);
        checkBookedSeats(foundSeats);
        return foundSeats;
    }


    public  List<Movie> filteringSeatsToBooked(List<Integer> seatsToBooked, List<Movie> seance) {
        List<Movie> foundSeats = seance.stream()
                .filter(orginal -> seatsToBooked.contains(orginal.getSeating()))
                .collect(Collectors.toList());
        return foundSeats;
    }

    public void checkBookedSeats(List<Movie> foundSeats) throws MovieNotFoundException {
        boolean someSeatsCanBeBooked = foundSeats.stream()
                .anyMatch(booked -> booked.getBooked().equals(BOOKED.name())); // lub all match
        if (someSeatsCanBeBooked){
            showInfoBooked(foundSeats);
        }
    }


    public  void showInfoBooked(List<Movie> foundSeats) throws MovieNotFoundException {
        List<Integer> bookedSeats = foundSeats.stream()
                .filter(booked -> booked.getBooked()
                        .equals(BOOKED.name()))
                .map(Movie::getSeating)
                .collect(Collectors.toList());
        String info = "booked";
        throw new MovieNotFoundException(bookedSeats,info);
    }
}
