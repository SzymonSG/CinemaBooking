package com.cinema.booking.services.infoService;

import com.cinema.booking.dtos.showInfoDto.DataDto;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.dtos.showInfoDto.BasicInfoAboutMovieDto;
import com.cinema.booking.dtos.showInfoDto.RepertoireDto;
import com.cinema.booking.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InfoMovieServiceImpl implements ShowInfoService {

    private final MovieRepository movieRepository;



    @Override
    public List<RepertoireDto> fetchRepertoire(String cinemaName) throws MovieNotFoundException {

        List<RepertoireDto> allPlayingMovies =
                movieRepository.fetchRepertoire(cinemaName);

        if (CollectionUtils.isEmpty(allPlayingMovies)){
            throw new MovieNotFoundException("We are currently creating a new repertoire. We Apologize!");
        }
        return allPlayingMovies;
    }


    @Override
    public List<Movie> fetchAvailableSeatsForDay(String cinemaName,
                                                 String movieName,
                                                 LocalDateTime localDateTime) throws MovieNotFoundException {

        List<Movie> infoMovies = movieRepository
                .fetchAvialableSeatsForDay(cinemaName, movieName, localDateTime);
        if (infoMovies==null || infoMovies.isEmpty()){
            throw new MovieNotFoundException(movieName,localDateTime);
        }
        return infoMovies;
    }

    @Override
    public List<Movie> fetchAvialableSeats(String cinemaName,
                                           String movieName) throws MovieNotFoundException {

        List<Movie> movies = movieRepository
                .fetchAvialableSeats(cinemaName, movieName);

        if (movies==null || movies.isEmpty()){
            throw new MovieNotFoundException(cinemaName, movieName);
        }
        return movies;
    }


    @Override
    public List<DataDto> fetchMoviesAfterDate(String cinemaName,
                                              String movieName) throws MovieNotFoundException {

        List<DataDto> dataTimeMovie = movieRepository
                .fetchLocalDateTimeForChosenMovie(cinemaName, movieName);
        if (dataTimeMovie==null || dataTimeMovie.isEmpty()) {
            throw new MovieNotFoundException("Unfortunately we are not playing such a movie.");
        }
        return dataTimeMovie;
    }

    @Override
    public List<BasicInfoAboutMovieDto> fetchFreeSeatsForSelectedDay(LocalDateTime localDateTime,
                                                                     String cinemaName) throws MovieNotFoundException {

        List<BasicInfoAboutMovieDto> allFreePlacesForSelected_CinemaAndDataTime = movieRepository.fetchFreePlacesForSelected_CinemaAndDataTime(cinemaName, localDateTime);
        if (allFreePlacesForSelected_CinemaAndDataTime==null || allFreePlacesForSelected_CinemaAndDataTime.isEmpty()){
            throw new MovieNotFoundException("Unfortunately, it is already booked today. Please check other days.");
        }
        return allFreePlacesForSelected_CinemaAndDataTime;
    }




}
