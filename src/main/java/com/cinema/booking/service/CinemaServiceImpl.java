package com.cinema.booking.service;
import com.cinema.booking.entities.Cinema;
import com.cinema.booking.entities.Movie;

import com.cinema.booking.entities.PropertiesMovie;
import com.cinema.booking.exceptions.CinemaNotFoundException;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.exceptions.PropertyMovieNotFoundException;
import com.cinema.booking.mapstructDTO.DataDto;
import com.cinema.booking.mapstructDTO.reservationDTO.BasicInfoAboutMovie;
import com.cinema.booking.repository.CinemaRepository;
import com.cinema.booking.repository.MovieRepository;
import com.cinema.booking.repository.PropertiesMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CinemaServiceImpl implements CinemaService,PropertiesMovieService,MovieService,ReservationService,ShowService{


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
                .orElseThrow(() -> new PropertyMovieNotFoundException("Properties Not Found!"));
    }
    /////////dotąd jest legit

    @Override
    public List<Movie> fetchMoviesByNamesAndRoomsList(String movieName, String movieRoom) {
        List<Movie> list = movieRepository.findByMovieNameAndMovieRoom(movieName, movieRoom);
        System.out.println(list);
        return list;
    }

    @Override
    public Cinema fetchCinemaById(Long cinemaId) throws CinemaNotFoundException {
        return cinemaRepository.findById(cinemaId)
                .orElseThrow(()->new CinemaNotFoundException("Cinema Not Available"));
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
            throw new MovieNotFoundException("Nie znaleziono takiego seansu");
        } else {
            //to git dopisałem !WantedPlaces.contains(null) czy która kollwiek wartość nie została posłana jako null
            if (wantedPlaces != null && !wantedPlaces.isEmpty() && !wantedPlaces.contains(null)) {
                //sprawdz czy istnieją takie miejsca siedzące
                List<Integer> collect = seance.stream().map(Movie::getSeating).collect(Collectors.toList());
                boolean checkPlacesExist = collect.containsAll(wantedPlaces); // contain czy zawiera mniejszy w większym
                if (!checkPlacesExist){
                    throw new MovieNotFoundException("Sprawdź czy podałeś właściwe miejsca");
                }
                //jeśli miejsca istnieją w bazie to odfiltruj i sprawdz czy zajęte , expand zeby zobaczyć jak normalnie by to wyglądało i o ile trudniej....
                List<Movie> foundPlaces = seance.stream()
                        .filter(orginal -> wantedPlaces.contains(orginal.getSeating())) // aa tu działa na odwrót przy pomocy metody contain
                        .collect(Collectors.toList());
//pamietaj ze any booked sprawdz i dodaj enums
                boolean free = foundPlaces.stream()
                        .anyMatch(book -> book.getBooked().equals("BOOKED")); // lub any match
                if (free) {
                    throw new MovieNotFoundException("Niektóre miejsca mogą być już zarezerwowane. Prosimy spróbować ponownie");
                } else {
                    foundPlaces.forEach(toBooked -> toBooked.setBooked("BOOKED"));
                    return movieRepository.saveAll(foundPlaces);
                }
            }else {
                throw new MovieNotFoundException("Nie podano miejsc do rezerwacji");
            }
        }
    }

    @Override
    public List<Movie> findFreePlacesOnMovie(String cinemaName, String movieName, LocalDateTime localDateTime) throws MovieNotFoundException {
        List<Movie> infoMovies = movieRepository.getAllFreePlacesOnMovie(cinemaName, movieName, localDateTime);
        if (infoMovies.isEmpty()){
            throw new MovieNotFoundException("Wszytskie miejsca są aktualnie zarazerwone, prosimy przejrzeć inną ofertę kina");
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
            throw new MovieNotFoundException("Movie Not Available");
        }
        return movie.get();
    }

    @Override
    public void deleteMovieById(Long movieId) {
         movieRepository.deleteById(movieId);
    }

    @Override
    public Movie enrolledCinemaToMovie(Long movieId, Long cinemaId) throws MovieNotFoundException, CinemaNotFoundException {

        Movie movie = fetchMovieById(movieId);
        Cinema cinema = fetchCinemaById(cinemaId);

        if (!movie.getCinemas().isEmpty()){
            throw new CinemaNotFoundException("This film yet is adding to cinema.!");
        }

        movie.enrolledCinema(cinema);
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> showAllPlayingMoviesInCinema(String cinemaName) throws MovieNotFoundException {
        List<Movie> allPlayingMovies = movieRepository.getAllPlayingMovies(cinemaName);
        if (allPlayingMovies.isEmpty() || allPlayingMovies.contains(null)){
            throw new MovieNotFoundException("Aktualnie tworzymy nowy repertuar! Przepraszamy!");
        }
        return allPlayingMovies;
    }

    @Override
    public List<PropertiesMovie> showDateChosenMovie(String cinemaName, String moviesName ) throws MovieNotFoundException {
        List<PropertiesMovie> dataTimeMovie = movieRepository.getLocalDateTimeForChosenMovie(cinemaName, moviesName);
        if (dataTimeMovie.isEmpty()){
            throw new MovieNotFoundException("Niestety nie ma takiego filmu!");
        }
        return dataTimeMovie;
    }
}
