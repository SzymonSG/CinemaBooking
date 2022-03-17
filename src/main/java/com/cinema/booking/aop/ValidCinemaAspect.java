package com.cinema.booking.aop;

import com.cinema.booking.entities.Cinema;
import com.cinema.booking.exceptions.ConstraintViolationException;
import com.cinema.booking.repository.CinemaRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@RequiredArgsConstructor
public class ValidCinemaAspect {

    private final CinemaRepository cinemaRepository;

    @Before("execution(* com.cinema.booking.services.cinemaService.CinemaServiceImpl.cinemaSave(..)) && args(cinema))")
    public void checkCinemaNameAreUnique(Cinema cinema) throws ConstraintViolationException {
        boolean cinemaNameExist = cinemaRepository.existsByCinemaName(cinema.getCinemaName());
        if (cinemaNameExist){
            throw new ConstraintViolationException("This cinema is already saved");
        }
    }


}
