package com.cinema.booking.security.aop;

import com.cinema.booking.security.exceptions.PasswordQualifyException;
import com.cinema.booking.security.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ValidRegistrationAscpect {

    @Before("execution(* com.cinema.booking.security.service.UserServiceImpl.registerUser(..)) && args(userModel))")
    public void checkPattern(UserModel userModel) throws PasswordQualifyException {
        String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
        String pas = userModel.getPassword();
        boolean result = pas.matches(regex);
        if (!result) {
            throw new PasswordQualifyException("Password does not meet the criteria");
        }
    }

}
