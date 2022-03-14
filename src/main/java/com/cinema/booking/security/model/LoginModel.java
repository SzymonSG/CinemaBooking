package com.cinema.booking.security.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Getter

@Setter
@NoArgsConstructor
public class LoginModel {

    private String email;
    private String password;


}
