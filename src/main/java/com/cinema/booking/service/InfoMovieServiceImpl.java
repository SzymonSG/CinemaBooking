package com.cinema.booking.service;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.entities.PropertiesMovie;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.mapstructDTO.reservationDTO.BasicInfoAboutMovie;
import com.cinema.booking.payloads.MovieName;
import com.cinema.booking.repository.MovieRepository;
import com.cinema.booking.service.ServiceInterfaces.ShowInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class InfoMovieServiceImpl implements ShowInfoService {

    private final MovieRepository movieRepository;

    //Distinct version
    @Override
    public List<MovieName> showAllPlayingMoviesInCinema(String cinemaName) throws MovieNotFoundException {
        List<MovieName> allPlayingMovies = movieRepository.getAllPlayingMovies(cinemaName);

//        List<Movie> allData = movieRepository.getAllData();
//        Map<String, List<Movie>> allPlayingMovies = allData.stream()
//                .filter(c -> c.equals(cinemaName))
//                .collect(Collectors.groupingBy((Movie m) -> m.getMovieName()));

        if (allPlayingMovies.isEmpty() || allPlayingMovies.contains(null)){
            throw new MovieNotFoundException("We are currently creating a new repertoire. We Apologize!");
        }
        return allPlayingMovies;
    }

    //version stream filter
    @Override
    public List<Movie> showAllPlayingMoviesInCinemaV2(String cinemaName) throws MovieNotFoundException {
        List<Movie> allData = movieRepository.getAllDataFromCinema(cinemaName);
        List<Movie> allPlayingMovies = allData.stream()
                .filter(distinctByKey(movie -> movie.getMovieName()))
                .collect(Collectors.toList());
        if (allPlayingMovies.isEmpty() || allPlayingMovies.contains(null)){
            throw new MovieNotFoundException("We are currently creating a new repertoire. We Apologize!");
        }
        return allPlayingMovies;
    }

    public static <T> Predicate<T> distinctByKey(
            Function<? super T, ?> keyExtractor) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
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
    public List<PropertiesMovie> showDateChosenMovie(String cinemaName, String movieName) throws MovieNotFoundException {
        List<PropertiesMovie> dataTimeMovie = movieRepository.getLocalDateTimeForChosenMovie(cinemaName, movieName);
        if (dataTimeMovie.isEmpty()) {
            throw new MovieNotFoundException("Unfortunately we are not playing such a movie.");
        }
        return dataTimeMovie;
    }

    @Override
    public List<BasicInfoAboutMovie> showFreePlacesForSelectedDay(LocalDateTime localDateTime, String cinemaName) throws MovieNotFoundException {
        List<BasicInfoAboutMovie> allFreePlacesForSelected_CinemaAndDataTime = movieRepository.getFreePlacesForSelected_CinemaAndDataTime(cinemaName, localDateTime);
        if (allFreePlacesForSelected_CinemaAndDataTime.isEmpty() || allFreePlacesForSelected_CinemaAndDataTime.contains(null)){
            throw new MovieNotFoundException("Olaboga dzisiaj wszytsko zajęte!!! Prosimy odwiedzić nas jutro");
        }
        return allFreePlacesForSelected_CinemaAndDataTime;
    }




}
