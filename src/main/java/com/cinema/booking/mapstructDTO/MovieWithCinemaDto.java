package com.cinema.booking.mapstructDTO;

import com.cinema.booking.entities.Cinema;
import com.cinema.booking.entities.Movie;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Data
public class MovieWithCinemaDto {

    private Long movieIdDto;
    private String movieNameDto;
    private String movieRoomDto;
    private Integer seatingDto;
    private String bookedDto;
    //DTO
    //CinemaDTOComplex
    private List<CinemaDto> cinemasDto = new ArrayList<>();

}
