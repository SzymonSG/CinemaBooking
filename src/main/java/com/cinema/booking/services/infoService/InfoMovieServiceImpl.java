package com.cinema.booking.services.infoService;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.entities.PropertiesMovie;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.dtos.showInfoDto.BasicInfoAboutMovieDto;
import com.cinema.booking.payloads.RepertoireDTO;
import com.cinema.booking.repository.MovieRepository;
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
    public List<RepertoireDTO> fetchAllPlayingMoviesInCinema(String cinemaName) throws MovieNotFoundException {
        List<RepertoireDTO> allPlayingMovies = movieRepository.fetchAllPlayingMovies(cinemaName);
        if (allPlayingMovies.isEmpty() || allPlayingMovies.contains(null)){
            throw new MovieNotFoundException("We are currently creating a new repertoire. We Apologize!");
        }
        return allPlayingMovies;
    }

    //version stream filter
    @Override
    public List<Movie> fetchAllPlayingMoviesInCinemaV2(String cinemaName) throws MovieNotFoundException {
        List<Movie> allData = movieRepository.fetchAllDataFromCinema(cinemaName);
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
    public List<Movie> fetchFreeSeatsOnMovie(String cinemaName, String movieName, LocalDateTime localDateTime) throws MovieNotFoundException {
        List<Movie> infoMovies = movieRepository.fetchAllFreePlacesOnMovie(cinemaName, movieName, localDateTime);
        if (infoMovies.isEmpty()){
            throw new MovieNotFoundException(movieName,localDateTime);
        }
        return infoMovies;
    }


    @Override
    public List<PropertiesMovie> fetchDateTimesChosenMovie(String cinemaName, String movieName) throws MovieNotFoundException {
        List<PropertiesMovie> dataTimeMovie = movieRepository.fetchLocalDateTimeForChosenMovie(cinemaName, movieName);
        if (dataTimeMovie.isEmpty()) {
            throw new MovieNotFoundException("Unfortunately we are not playing such a movie.");
        }
        return dataTimeMovie;
    }

    @Override
    public List<BasicInfoAboutMovieDto> fetchFreeSeatsForSelectedDay(LocalDateTime localDateTime, String cinemaName) throws MovieNotFoundException {
        List<BasicInfoAboutMovieDto> allFreePlacesForSelected_CinemaAndDataTime = movieRepository.fetchFreePlacesForSelected_CinemaAndDataTime(cinemaName, localDateTime);
        if (allFreePlacesForSelected_CinemaAndDataTime.isEmpty() || allFreePlacesForSelected_CinemaAndDataTime.contains(null)){
            throw new MovieNotFoundException("Unfortunately, it is already booked today. Please check other days.");
        }
        return allFreePlacesForSelected_CinemaAndDataTime;
    }




}
