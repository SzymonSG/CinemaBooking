package com.cinema.booking.dtos.connectionDomainDto;

import com.cinema.booking.dtos.cinemaDto.CinemaDto;
import com.cinema.booking.dtos.propertyDto.PropertiesMovieDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ComplexMovieDto {

    private Long movieDtoId;
    private String movieNameDto;
    private String movieRoomDto;
    private Integer seatingDto;
    private String bookedDto;
    //usunięte DTO2
    private List<CinemaDto> cinemas = new ArrayList<>();
    private PropertiesMovieDto properitiesMovie;

}
