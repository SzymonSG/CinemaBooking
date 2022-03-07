package com.cinema.booking.security.common;

public class Utilize {

    public static final String[] WHITE_LIST_URLS ={
            "/hello",
            "/register",
            "/verifyRegistration/**",
            "/resendVerifyToken/**",
            "/resetPassword/**",
            "/savePassword/**",
            "/changePassword/**",
            "/singup",
            "/cinemas",
            "/movies",
            "/date-times",
            "/movieid/**/cinemaid/**",
            "/movieid/**/propertyid/**"
    };
}
