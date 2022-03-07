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
            "/cinemas/**/**",
            "/dates/cinemas/**",
            "/movies/**",
            "/date-times",
            "/dates/**",
            "/movieid/**/cinemaid/**",
            "/movieid/**/propertyid/**",
            "/findFreePlaces/cinemaName/**/movieName/**",
            "/findByDateFree/**",
            "/cinemaName/**/movieName/**",
            "/movies:include=cinemas",
            "/cinemas:include=movies",
            "/findByDate/**",
            "/date-times"
    };
}
