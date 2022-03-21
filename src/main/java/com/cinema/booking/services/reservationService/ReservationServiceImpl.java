package com.cinema.booking.services.reservationService;

import com.cinema.booking.entities.Movie;
import com.cinema.booking.exceptions.MovieNotFoundException;

import com.cinema.booking.model.ReservationModel;
import com.cinema.booking.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.cinema.booking.common.ReservationCheck.BOOKED;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService, ValidationReservationService {

    private final MovieRepository movieRepository;

    @Transactional
    @Override
    public List<Movie> multiBookedPlaceWithDate(String cinemaName, String movieName,String movieRoom, List<Integer> wantedSeats, LocalDateTime localDateTime) throws MovieNotFoundException {
        List<Movie> seance = movieRepository.fetchDataToReservation(
                cinemaName,movieName,movieRoom,localDateTime);

        if (seance.isEmpty() || seance.contains(null)) {
            throw new MovieNotFoundException("No such seance was found");
        } else {
            List<Movie> foundSeats = complexValidation(wantedSeats, seance);
            foundSeats.forEach(toBooked -> toBooked.setBooked(BOOKED.name()));
            return movieRepository.saveAll(foundSeats);
        }
    }

    @Override
    public List<Movie> multiBookedPlaceWithDateV2(ReservationModel reservation) throws MovieNotFoundException {
        List<Movie> seance = movieRepository.fetchDataToReservation(
                reservation.getCinemaName(),
                reservation.getMovieName(),
                reservation.getMovieRoom(),
                reservation.getDateAndTime());

        if (seance.isEmpty() || seance.contains(null)) {
            throw new MovieNotFoundException("No such seance was found");
        } else {
            List<Movie> foundSeats = complexValidation(reservation.getSeatsToBooked(), seance);
            foundSeats.forEach(toBooked -> toBooked.setBooked(BOOKED.name()));
            return movieRepository.saveAll(foundSeats);
        }
    }

    //TODO WYDZIEL DO KLASY VALIDACJA REZERWACJI
    private List<Movie> complexValidation(List<Integer> wantedSeats, List<Movie> seance) throws MovieNotFoundException {
        checkSeatsToBookedExist(wantedSeats, seance);
        List<Movie> foundSeats = findWantedSeats(wantedSeats, seance);
        checkFoundSeatsAreBooked(foundSeats);
        return foundSeats;
    }


    @Override
    public void checkSeatsToBookedExist(List<Integer> wantedSeats, List<Movie> seance) throws MovieNotFoundException {
        if (wantedSeats != null && !wantedSeats.isEmpty() && !wantedSeats.contains(null)) {
            List<Integer> collect = seance
                    .stream()
                    .map(Movie::getSeating)
                    .collect(Collectors.toList());
            boolean checkSeatsExist = collect.containsAll(wantedSeats); // contain czy zawiera mniejszy w większym
            if (!checkSeatsExist) {
                throw new MovieNotFoundException(wantedSeats);
            }
        }else {
            throw new MovieNotFoundException("No seats were given for booking");
        }
    }

    @Override
    public List<Movie> findWantedSeats(List<Integer> wantedSeats, List<Movie> seance) {
        List<Movie> foundSeats = seance.stream()
                .filter(orginal -> wantedSeats.contains(orginal.getSeating()))
                .collect(Collectors.toList());
        return foundSeats;
    }

    @Override
    public void checkFoundSeatsAreBooked(List<Movie> foundSeats) throws MovieNotFoundException {
        boolean someSeatsCanBeBooked = foundSeats.stream()
                .anyMatch(booked -> booked.getBooked().equals(BOOKED.name())); // lub all match
        if (someSeatsCanBeBooked){
            checkBookedSeats(foundSeats);
        }
    }

    @Override
    public void checkBookedSeats(List<Movie> foundSeats) throws MovieNotFoundException {
        List<Integer> bookedSeats = foundSeats.stream()
                .filter(booked -> booked.getBooked()
                        .equals(BOOKED.name()))
                .map(Movie::getSeating)
                .collect(Collectors.toList());
        String info = "booked";
        throw new MovieNotFoundException(bookedSeats,info);
    }


}
