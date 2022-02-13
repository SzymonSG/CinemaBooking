package com.cinema.booking.controllers;


import com.cinema.booking.entities.PropertiesMovie;
import com.cinema.booking.mapper.CinemaMapStruct;
import com.cinema.booking.mapstructDTO.PropertiesMovieDto;
import com.cinema.booking.service.PropertiesMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class PropertiesMovieController {

    private final PropertiesMovieService propertyMovieService;
    private final CinemaMapStruct cinemaMapStruct;

    @PostMapping("/save/propertiesmovie")
    PropertiesMovie propertiesMovieSave(@Valid @RequestBody PropertiesMovieDto propertiesMovie){
        PropertiesMovie property = cinemaMapStruct.dtoToPropertiesMovie(propertiesMovie);
        return propertyMovieService.propertySave(property);

    }


}
