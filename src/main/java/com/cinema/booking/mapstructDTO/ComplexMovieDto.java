package com.cinema.booking.mapstructDTO;

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
    private List<CinemaDto> cinemasDto = new ArrayList<>();
    private PropertiesMovieDTO properitiesMovieDto;

}
