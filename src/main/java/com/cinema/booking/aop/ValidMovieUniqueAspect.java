package com.cinema.booking.aop;

import com.cinema.booking.entities.Movie;
import com.cinema.booking.exceptions.ConstraintViolationException;
import com.cinema.booking.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
@RequiredArgsConstructor
public class ValidMovieUniqueAspect {

    private final MovieRepository movieRepository;


    @Before("execution(* com.cinema.booking.services.movieService.MovieServiceImpl.movieSave(..)) && args(movie))")
    public void checkSeatingAreUnique(Movie movie) throws ConstraintViolationException {
        boolean seatingExist = movieRepository
                .existsBySeatingAndMovieNameAndMovieRoom(movie.getSeating(),movie.getMovieName(),movie.getMovieRoom());
        if (seatingExist){
            throw new ConstraintViolationException("This action isn't compatible with structure DB!");
        }
    }

}
