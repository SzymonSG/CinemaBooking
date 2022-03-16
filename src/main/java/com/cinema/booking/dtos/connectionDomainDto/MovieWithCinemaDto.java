package com.cinema.booking.dtos.connectionDomainDto;

import com.cinema.booking.dtos.cinemaDto.CinemaDto;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Data
public class MovieWithCinemaDto {

    private Long movieDtoId;
    private String movieNameDto;
    private String movieRoomDto;
    private Integer seatingDto;
    private String bookedDto;
    //DTO
    //CinemaDTOComplex
    private List<CinemaDto> cinemas = new ArrayList<>();

}
