package com.cinema.booking.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {


    @GetMapping("/animal")
    public String getLoginVieww(){
        return "YOOO!";
    }
}
