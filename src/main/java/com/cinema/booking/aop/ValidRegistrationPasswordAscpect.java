package com.cinema.booking.aop;

import com.cinema.booking.entities.User;
import com.cinema.booking.exceptions.PasswordQualifyException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ValidRegistrationPasswordAscpect {

    @Before("execution(* com.cinema.booking.services.authService.UserServiceImpl.registerUser(..)) && args(userModel))")
    public void checkPattern(User userModel) throws PasswordQualifyException {
        String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
        String pas = userModel.getPassword();
        boolean result = pas.matches(regex);
        if (!result) {
            throw new PasswordQualifyException("Password does not meet the criteria");
        }
    }

}
