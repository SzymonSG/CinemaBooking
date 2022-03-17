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
public class ValidMovieSaveAspect {

    private final MovieRepository movieRepository;

    @Before("execution(* com.cinema.booking.services.movieService.MovieServiceImpl.movieSave(..)) && args(movie))")
    public void checkSeatingAreUnique(Movie movie) throws ConstraintViolationException {
        boolean seatingExist = movieRepository.existsBySeating(movie.getSeating());
        if (seatingExist){
            throw new ConstraintViolationException("This seat is already saved");
        }
    }
}
