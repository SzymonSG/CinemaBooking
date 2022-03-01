package com.cinema.booking.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class TestController {


    @GetMapping("/login")
    public String getLoginView(){
        return "lol";
    }
}
