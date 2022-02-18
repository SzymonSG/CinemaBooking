package com.cinema.booking.service;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.entities.PropertiesMovie;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.mapstructDTO.reservationDTO.BasicInfoAboutMovie;
import com.cinema.booking.repository.MovieRepository;
import com.cinema.booking.service.ServiceInterfaces.ShowInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class InfoMovieServiceImpl implements ShowInfoService {

    private final MovieRepository movieRepository;

    @Override
    public List<Movie> showAllPlayingMoviesInCinema(String cinemaName) throws MovieNotFoundException {
        List<Movie> allPlayingMovies = movieRepository.getAllPlayingMovies(cinemaName);
        if (allPlayingMovies.isEmpty() || allPlayingMovies.contains(null)){
            throw new MovieNotFoundException("We are currently creating a new repertoire. We Apologize!");
        }
        return allPlayingMovies;
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
    public List<Movie> checkMoviesAfterDate(LocalDateTime localDateTime) {
        return movieRepository.findByLocalDateTime(localDateTime);
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
