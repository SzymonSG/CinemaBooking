package com.cinema.booking.validators;

import com.cinema.booking.entities.User;
import com.cinema.booking.exceptions.ConstraintViolationException;
import com.cinema.booking.exceptions.UserPasswordNotQualifyException;
import com.cinema.booking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class UserRegistartionValidator {

    private final UserRepository userRepository;

    @Before("execution(* com.cinema.booking.services.userService.UserServiceImpl.registerUser(..)) && args(userModel))")
    public void checkPatternPassword(User userModel) throws UserPasswordNotQualifyException {
        String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
        String pas = userModel.getPassword();
        boolean result = pas.matches(regex);

        if (!result) {
            throw new UserPasswordNotQualifyException("Password does not meet the criteria");
        }
    }

    @Before("execution(* com.cinema.booking.services.userService.UserServiceImpl.registerUser(..)) && args(userModel))")
    public void checkEmailUnique(User userModel) throws ConstraintViolationException {

        boolean registredEmail = userRepository.existsByEmail(userModel.getEmail());
        if (registredEmail) {
            throw new ConstraintViolationException("Email is allready registred");
        }
    }


}
