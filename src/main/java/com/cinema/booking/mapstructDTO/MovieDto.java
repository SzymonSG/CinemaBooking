package com.cinema.booking.mapstructDTO;

import com.cinema.booking.entities.Cinema;

import lombok.Data;

import java.util.List;

@Data
public class MovieDto {
    private Long movieDtoId;
    private String movieNameDto;
    private String movieRoomDto;
    private Integer seatingDto;
    private String bookedDto;
//    private List<Cinema> cinemas;
}
