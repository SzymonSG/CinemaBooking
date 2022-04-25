package com.cinema.booking.services.reservationService;

import com.cinema.booking.entities.Movie;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.repository.MovieRepository;
import com.cinema.booking.validators.BookingModelValidator;
import com.cinema.booking.validators.SeatsBookingValidatorComp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import static com.cinema.booking.common.ReservationCheck.BOOKED;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements BookingService, BookingValidators {

    private final MovieRepository movieRepository;
//    private final BookingValidators bookingValidators;
    private final SeatsBookingValidatorComp seatsBookingValidator;

    @Transactional
    @Override
    public List<Movie> multiBookedPlaceWithDate(String cinemaName, String movieName,String movieRoom, List<Integer> wantedSeats, LocalDateTime localDateTime) throws MovieNotFoundException {
        List<Movie> seance = movieRepository.fetchDataToBooking(
                cinemaName,movieName,movieRoom,localDateTime);

        if (seance.isEmpty() || seance==null) {
            throw new MovieNotFoundException("No such seance was found");
        } else {
            List<Movie> foundSeats = seatsValidation(wantedSeats, seance);
            foundSeats.forEach(toBooked -> toBooked.setBooked(BOOKED.name()));
            return movieRepository.saveAll(foundSeats);
        }
    }

    @Transactional
    @Override
    public List<Movie> bookingSeats(BookingModelValidator correctModel) throws MovieNotFoundException {
        //version with Optional
        Optional<List<Movie>> seanceOptional = Optional.ofNullable(movieRepository.fetchDataToBookingg(
                correctModel.getCinemaName(),
                correctModel.getMovieName(),
                correctModel.getMovieRoom(),
                correctModel.getDateAndTime()
        )).orElseThrow(() -> new MovieNotFoundException("JPQL Query not work!, correctModel is Valid before"));

        List<Movie> seance = seanceOptional.stream()
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());

//        List<Movie> seance = movieRepository.fetchDataToBooking(
//                correctModel.getCinemaName(),
//                correctModel.getMovieName(),
//                correctModel.getMovieRoom(),
//                correctModel.getDateAndTime()
//        );

        List<Movie> filteredSeats = filteringSeatsToBooked(correctModel.getSeatsToBooked(), seance);
        boolean bookedSeats = isBookedSeats(filteredSeats);
        if (!bookedSeats) {
            filteredSeats.forEach(toBooked -> toBooked.setBooked(BOOKED.name()));
        }
        return movieRepository.saveAll(filteredSeats);

    }


    private List<Movie> seatsValidation(List<Integer> seatsToBooked, List<Movie> seance) throws MovieNotFoundException {
        List<Movie> foundSeats = filteringSeatsToBooked(seatsToBooked, seance);
        isBookedSeats(foundSeats);
        return foundSeats;
    }

    //odfiltorwoanie miejsc do rezerwacji
    @Override
    public List<Movie> filteringSeatsToBooked(List<Integer> seatsToBooked, List<Movie> seance) {
        List<Movie> foundSeats = seance.stream()
                .filter(orginal -> seatsToBooked.contains(orginal.getSeating()))
                .collect(Collectors.toList());
        return foundSeats;
    }

    @Override
    public boolean isBookedSeats(List<Movie> foundSeats) throws MovieNotFoundException {
        boolean bookedSeats = foundSeats.stream()
                .anyMatch(booked -> booked.getBooked().equals(BOOKED.name())); // lub all match
        if (bookedSeats) {
            showInfoAboutBooked(foundSeats);
        }
        return bookedSeats;
    }

    @Override
    public void showInfoAboutBooked(List<Movie> foundSeats) throws MovieNotFoundException {
        List<Integer> bookedSeats = foundSeats.stream()
                .filter(booked -> booked.getBooked()
                        .equals(BOOKED.name()))
                .map(Movie::getSeating)
                .collect(Collectors.toList());
        String info = "booked";
        throw new MovieNotFoundException(bookedSeats, info);
    }


}
