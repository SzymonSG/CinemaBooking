package com.cinema.booking.security.common;

public class Utilize {
//Global RequestMapping i będzie krótsza lista, refactor all controllers jedno na @PreAuthorize np zeby pokazać działanie JWT
    public static final String[] WHITE_LIST_URLS ={
            "/singin",
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
            "/animall",
            "reservations",
            "/cinemas/{cinemaName}/repertoiress",
            "/cinemas/{cinemaName}/repertoires",
            "/cinemas/{cinemaName}/movies/{movieName}/date-times",
            "/cinemas/{cinemaName}/movies/{movieName}/avialable-seats/dates-times/**",
            "/cinemas/{cinemaName}/date-times",
            "/cinemas/{cinemaName}/movies/{movieName}/avialable-seats"
    };
}
