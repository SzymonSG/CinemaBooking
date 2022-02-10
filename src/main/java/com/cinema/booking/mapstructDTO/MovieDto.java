package com.cinema.booking.mapstructDTO;

import lombok.Data;

@Data
public class MovieDto {
    private Long movieDtoId;
    private String movieNameDto;
    private String movieRoomDto;
    private Integer seatingDto;
    private String bookedDto;
}
