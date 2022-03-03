package com.cinema.booking.security.common;

public class Utilize {

    public static final String[] WHITE_LIST_URLS ={
            "/hello",
            "/register",
            "/verifyRegistration/**",
            "/resendVerifyToken/*",
            "/resetPassword/*",
            "/savePassword/*",
            "/cinemas"
    };

    //posprawdzaj czy nie powinno być wszędzie dwóch gwaizdek

    public static final String secretkey =
            "securesecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecure";
}
