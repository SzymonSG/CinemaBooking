package com.cinema.booking.controllers;

import com.cinema.booking.entities.ProperitiesMovie;
import com.cinema.booking.service.PropertiesMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class PropertyMovieController {

    private final PropertiesMovieService propertyMovieService;

    @PostMapping("/save/propertiesmovie")
    ProperitiesMovie propertiesMovieSave(@Valid @RequestBody ProperitiesMovie properitiesMovie){
        return propertyMovieService.propertySave(properitiesMovie);
    }



}
