package com.cinema.booking.controllers.domainControllers;

import com.cinema.booking.entities.PropertiesMovie;
import com.cinema.booking.mappers.PropertiesMapper;
import com.cinema.booking.dtos.propertyDto.PropertiesMovieDto;
import com.cinema.booking.services.propertyService.PropertiesMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PropertiesMovieController {

    private final PropertiesMovieService propertyMovieService;
    private final PropertiesMapper propertiesMapper;


    @PostMapping("/date-times")
    public PropertiesMovie propertiesMovieSave(@Valid @RequestBody PropertiesMovieDto propertiesMovie){
        PropertiesMovie property = propertiesMapper.dtoToPropertiesMovie(propertiesMovie);
        return propertyMovieService.propertySave(property);

    }



}
