package com.cinema.booking.services.infoService;
import com.cinema.booking.dtos.showInfoDto.DataDto;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.dtos.showInfoDto.BasicInfoAboutMovieDto;
import com.cinema.booking.dtos.showInfoDto.RepertoireDto;
import com.cinema.booking.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class InfoMovieServiceImpl implements ShowInfoService {

    private final MovieRepository movieRepository;


    @Override
    public List<RepertoireDto> fetchAllPlayingMoviesInCinema(String cinemaName) throws MovieNotFoundException {
        List<RepertoireDto> allPlayingMovies = movieRepository.fetchAllPlayingMovies(cinemaName);
        if (allPlayingMovies.isEmpty() || allPlayingMovies.contains(null)){
            throw new MovieNotFoundException("We are currently creating a new repertoire. We Apologize!");
        }
        return allPlayingMovies;
    }


    @Override
    public List<Movie> fetchAvailableSeatsWithDateTimeOnSeance(String cinemaName,
                                                               String movieName,
                                                               LocalDateTime localDateTime) throws MovieNotFoundException {
        List<Movie> infoMovies = movieRepository
                .fetchAvialableSeatsWithDateTimeOnSeance(cinemaName, movieName, localDateTime);
        if (infoMovies.isEmpty()){
            throw new MovieNotFoundException(movieName,localDateTime);
        }
        return infoMovies;
    }

    @Override
    public List<Movie> fetchAvialableSeatsOnSeance(String cinemaName,
                                                   String movieName) throws MovieNotFoundException {

        List<Movie> movies = movieRepository
                .fetchAvialableSeatsOnSeance(cinemaName, movieName);

        if (movies.isEmpty()){
            throw new MovieNotFoundException(cinemaName, movieName);
        }
        return movies;
    }


    @Override
    public List<DataDto> fetchDateTimesChoosenMovie(String cinemaName,
                                                            String movieName) throws MovieNotFoundException {

        List<DataDto> dataTimeMovie = movieRepository
                .fetchLocalDateTimeForChosenMovie(cinemaName, movieName);
        if (dataTimeMovie.isEmpty()) {
            throw new MovieNotFoundException("Unfortunately we are not playing such a movie.");
        }
        return dataTimeMovie;
    }

    @Override
    public List<BasicInfoAboutMovieDto> fetchFreeSeatsForSelectedDay(LocalDateTime localDateTime,
                                                                     String cinemaName) throws MovieNotFoundException {

        List<BasicInfoAboutMovieDto> allFreePlacesForSelected_CinemaAndDataTime = movieRepository.fetchFreePlacesForSelected_CinemaAndDataTime(cinemaName, localDateTime);
        if (allFreePlacesForSelected_CinemaAndDataTime.isEmpty() || allFreePlacesForSelected_CinemaAndDataTime.contains(null)){
            throw new MovieNotFoundException("Unfortunately, it is already booked today. Please check other days.");
        }
        return allFreePlacesForSelected_CinemaAndDataTime;
    }




}
