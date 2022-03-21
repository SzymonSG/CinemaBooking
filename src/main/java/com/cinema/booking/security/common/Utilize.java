package com.cinema.booking.security.common;

public class Utilize {
//Global RequestMapping i będzie krótsza lista, refactor all controllers jedno na @PreAuthorize np zeby pokazać działanie JWT
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
            "/reservations",
            "/cinemas/**/moviess",
            "/animal",
            "/animall"
    };
}
