package com.cinema.booking.security.common;

public class Utilize {

    public static final String[] WHITE_LIST_URLS ={
            "/hello",
            "/register",
            "/verifyRegistartion/*",
            "/resendVerifyToken/*",
            "/resetPassword/*",
            "/savePassword/*"
    };
}
