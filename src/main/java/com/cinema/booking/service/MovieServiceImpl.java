package com.cinema.booking.service;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.repository.CinemaRepository;
import com.cinema.booking.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

//    @Autowired
//    private MovieRepository movieRepository;
    private final MovieRepository movieRepository;
    private final CinemaRepository cinemaRepository;


    @Override
    public Movie movieSave(Movie movie) {
        return null;
    }

    @Override
    public List<Movie> bookedUpdate(String nameCinema, String movie) {

        List<Movie> listaDoPofiltorowania = movieRepository.wlasciwe(nameCinema, movie);

        //listaDoPofiltorowania.stream()
        //        .filter(x->filtr.contains(x.getSeating()))
        //        .collect(Collectors.toList());

        List<Movie> free = listaDoPofiltorowania.stream()
                .filter(x -> x.getBooked().equals("free"))
                .collect(Collectors.toList());

        free.forEach(x->x.setBooked("BlaBla"));

        return movieRepository.saveAll(free);
    }



    @Transactional
    @Override
    public List<Movie> multiBookedPlace(String cinemaName, String movieName, List<Integer> wantedPlaces) throws MovieNotFoundException {
        List<Movie> seance = movieRepository.wlasciwe(cinemaName,movieName);
        //zamiast tak sprawdzic jak isEmpty or contains(null) czy nie lepiej uzyć Optionala
//        Optional.ofNullable(seance).stream().flatMap(x->x.stream().filter(c->c.getMovieName().equals(movieName))).collect(Collectors.toList()).isEmpty();
//          Optional.ofNullable(seance).filter(x->x.equals(movieName)&& x.equals(cinemaName) ).orElseThrow(()->new RuntimeException("Nie znaleziono takich"));
//          Optional.ofNullable(seance).filter(x->x.equals(movieName)).stream().map(x->x.stream().filter(z->z.getMovieName().equals(movieName))).
        if (seance.isEmpty() || seance.contains(null)) {
            throw new MovieNotFoundException("Nie znaleziono takiego seansu");
        } else {
            //to git dopisałem !WantedPlaces.contains(null) czy która kollwiek wartość nie została posłana jako null
            //Objects.n
            if (Objects.nonNull(wantedPlaces) && !wantedPlaces.isEmpty() && !wantedPlaces.contains(null)) {
                //sprawdz czy istnieją takie miejsca siedzące sa
                List<Integer> collect = seance.stream().map(Movie::getSeating).collect(Collectors.toList());
                boolean checkPlacesExist = collect.containsAll(wantedPlaces); // contain czy zawiera mniejszy w większym
                if (!checkPlacesExist){
                    throw new MovieNotFoundException("Sprawdź czy podałeś właściwe miejsca");
                }
                //jeśli miejsca istnieją w bazie to odfiltruj i sprawdz czy zajęte
                //odfiltorowanie po miejscach
                List<Movie> foundPlaces = seance.stream()
                        .filter(orginal -> wantedPlaces.contains(orginal.getSeating())) // aa tu działa na odwrót przy pomocy metody contain
                        .collect(Collectors.toList());

                boolean free = foundPlaces.stream()
                        .anyMatch(book -> book.getBooked().equals("free")); // lub any match
                if (!free) {
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
    public List<Movie> fetchMoviesList() {
        return movieRepository.findAll();
    }



    @Override
    public List<Movie> findByDateQuery(LocalDateTime localDate) {
        return movieRepository.seacherByDatetimeJacson(localDate);
    }

    @Override
    public List<Movie> multiBookedPlaceWithDate(String cinemaName,
                                                          String movieName,
                                                          List<Integer> wantedPlaces,
                                                          LocalDateTime localDateTime) throws MovieNotFoundException {
        List<Movie> seance = movieRepository.searchByDiffrentFieldsWithDate(cinemaName,movieName,localDateTime);


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
}
