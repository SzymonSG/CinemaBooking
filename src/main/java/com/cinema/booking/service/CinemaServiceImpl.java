package com.cinema.booking.service;
import com.cinema.booking.entities.Cinema;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.entities.PropertiesMovie;
import com.cinema.booking.exceptions.CinemaNotFoundException;
import com.cinema.booking.exceptions.AlreadyEnrolledMovieException;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.exceptions.PropertyMovieNotFoundException;
import com.cinema.booking.mapstructDTO.reservationDTO.BasicInfoAboutMovie;
import com.cinema.booking.repository.CinemaRepository;
import com.cinema.booking.repository.MovieRepository;
import com.cinema.booking.repository.PropertiesMovieRepository;
import com.cinema.booking.service.ServiceInterfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CinemaServiceImpl implements CinemaService, PropertiesMovieService, MovieService, ReservationService,ShowService {


    private final CinemaRepository cinemaRepository;

    private final MovieRepository movieRepository;

    private final PropertiesMovieRepository propertiesMovieRepository;

    @Autowired
    public CinemaServiceImpl(CinemaRepository cinemaRepository, MovieRepository movieRepository, PropertiesMovieRepository propertiesMovieRepository) {
        this.cinemaRepository = cinemaRepository;
        this.movieRepository = movieRepository;
        this.propertiesMovieRepository = propertiesMovieRepository;
    }
    @Override
    public Cinema cinemaSave(Cinema cinemaDepartment) {
        return cinemaRepository.save(cinemaDepartment);
    }
    @Override
    public Movie movieSave(Movie movie) {
        return movieRepository.save(movie);
    }
    @Override
    public List<Cinema> fetchCinemasList() {
        return cinemaRepository.findAll();
    }
    @Override
    public List<Movie> fetchMoviesList() {
        return movieRepository.findAll();
    }
    @Override
    public PropertiesMovie propertySave(PropertiesMovie properitiesMovie) {
        return propertiesMovieRepository.save(properitiesMovie);
    }

    @Override
    public PropertiesMovie fetchPropertyMovieById(Long propertyId) throws PropertyMovieNotFoundException {
        return propertiesMovieRepository.findById(propertyId)
                .orElseThrow(() -> new PropertyMovieNotFoundException(propertyId));
    }

    @Override
    public List<Movie> fetchMoviesByNamesAndRoomsList(String movieName, String movieRoom) {
        List<Movie> list = movieRepository.findByMovieNameAndMovieRoom(movieName, movieRoom);
        System.out.println(list);
        return list;
    }

    @Override
    public Cinema fetchCinemaById(Long cinemaId) throws CinemaNotFoundException{
        return cinemaRepository.findById(cinemaId)
                .orElseThrow(()-> new CinemaNotFoundException(cinemaId));
    }


    @Override
    public List<Movie> checkMoviesAfterDate(LocalDateTime localDate) {
        return movieRepository.findByLocalDateTime(localDate);
    }

    @Transactional
    @Override
    public List<Movie> multiBookedPlaceWithDate(String cinemaName,
                                                String movieName,
                                                List<Integer> wantedPlaces,
                                                LocalDateTime localDateTime) throws MovieNotFoundException {
        List<Movie> seance = movieRepository.getDataCollectionToReservation(cinemaName,movieName,localDateTime);


        if (seance.isEmpty() || seance.contains(null)) {
            throw new MovieNotFoundException("No such seance was found");
        } else {
            //if (wantedPlaces != null && !wantedPlaces.isEmpty() && !wantedPlaces.contains(null)) {
            checkGivenPlacesExist(wantedPlaces, seance);
            List<Movie> foundPlaces = foundWantedPlaces(wantedPlaces, seance);
            checkFoundPlacesAreBooked(foundPlaces);
            foundPlaces.forEach(toBooked -> toBooked.setBooked("BOOKED"));
            return movieRepository.saveAll(foundPlaces);
            }

        }

    private void whatplaceAreBooked(List<Movie> foundPlaces) throws MovieNotFoundException {
        List<Integer> bookedPlaces = foundPlaces.stream()
                .filter(booked -> booked.getBooked()
                .equals("BOOKED"))
                .map(Movie::getSeating)
                .collect(Collectors.toList());
        String info = "booked";
        throw new MovieNotFoundException(bookedPlaces,info);//Niektóre miejsca mogą być już zarezerwowane. Prosimy spróbować ponownie
    }

    private void checkFoundPlacesAreBooked(List<Movie> foundPlaces) throws MovieNotFoundException {
        boolean somePlaceIsCanBeBooked = foundPlaces.stream()
                    .anyMatch(booked -> booked.getBooked().equals("BOOKED")); // lub any match
        if (somePlaceIsCanBeBooked){
            whatplaceAreBooked(foundPlaces);
        }

        //return somePlaceIsCanBeBooked;
    }

    private List<Movie> foundWantedPlaces(List<Integer> wantedPlaces, List<Movie> seance) {
        List<Movie> foundPlaces = seance.stream()
                .filter(orginal -> wantedPlaces.contains(orginal.getSeating()))
                .collect(Collectors.toList());
        return foundPlaces;
    }
    //}

    private void checkGivenPlacesExist(List<Integer> wantedPlaces, List<Movie> seance) throws MovieNotFoundException {
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

    @Override
    public List<Movie> findFreePlacesOnMovie(String cinemaName, String movieName, LocalDateTime localDateTime) throws MovieNotFoundException {
        List<Movie> infoMovies = movieRepository.getAllFreePlacesOnMovie(cinemaName, movieName, localDateTime);
        if (infoMovies.isEmpty()){
            throw new MovieNotFoundException(movieName,localDateTime);
        }
        return infoMovies;

    }

    @Override
    public List<BasicInfoAboutMovie> checkBasicInfoAboutMovies(LocalDateTime localDateTime, String cinemaName) throws MovieNotFoundException {
        List<BasicInfoAboutMovie> wszystkiewolnemiejscawbonarcenadzis = movieRepository.wszystkiewolnemiejscawbonarcenadzis(cinemaName, localDateTime);
            if (wszystkiewolnemiejscawbonarcenadzis.isEmpty() || wszystkiewolnemiejscawbonarcenadzis.contains(null)){
            throw new MovieNotFoundException("Olaboga dzisiaj wszytsko zajęte!!! Prosimy odwiedzić nas jutro");
        }
        return wszystkiewolnemiejscawbonarcenadzis;
    }

    @Override
    public Movie fetchMovieById(Long movieId) throws MovieNotFoundException {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (!movie.isPresent()){
            throw new MovieNotFoundException(movieId);
        }
        return movie.get();
    }

    @Override
    public void deleteMovieById(Long movieId) {
         movieRepository.deleteById(movieId);
    }

    @Override
    public Movie enrolledPropertiesToMovie(Long movieId, Long propertyId) throws MovieNotFoundException, PropertyMovieNotFoundException {
        Movie movie = fetchMovieById(movieId);
        ///tutaj powinno być fetchprropertyBiDI
        PropertiesMovie propertiesMovie = propertiesMovieRepository.findById(propertyId)
                .orElseThrow(() -> new PropertyMovieNotFoundException(movieId));
        movie.assignProperty(propertiesMovie);
        return movieRepository.save(movie);
    }


    //zmiana z movie na Cinema
    @Transactional
    @Override
    public Cinema enrolledCinemaToMovie(Long movieId, Long cinemaId) throws MovieNotFoundException, AlreadyEnrolledMovieException, CinemaNotFoundException {

        Movie movie = fetchMovieById(movieId);
        Cinema cinema = fetchCinemaById(cinemaId);

        if (!movie.getCinemas().isEmpty()){
            throw new AlreadyEnrolledMovieException(movieId,cinema.getCinemaName());
        }
        cinema.enrolledMovie(movie);
        return cinemaRepository.save(cinema);

    }

    @Override
    public List<Movie> showAllPlayingMoviesInCinema(String cinemaName) throws MovieNotFoundException {
        List<Movie> allPlayingMovies = movieRepository.getAllPlayingMovies(cinemaName);
        if (allPlayingMovies.isEmpty() || allPlayingMovies.contains(null)){
            throw new MovieNotFoundException("We are currently creating a new repertoire. We Apologize!");
        }
        return allPlayingMovies;
    }

    @Override
    public List<PropertiesMovie> showDateChosenMovie(String cinemaName, String moviesName ) throws MovieNotFoundException {
        List<PropertiesMovie> dataTimeMovie = movieRepository.getLocalDateTimeForChosenMovie(cinemaName, moviesName);
        if (dataTimeMovie.isEmpty()){
            throw new MovieNotFoundException("Unfortunately we are not playing such a movie.");
        }
        return dataTimeMovie;
    }


}
