package com.cinema.booking.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class MovieDTO {

    private Long movieId;
    private String movieName;
    private String movieRoom;
    private Integer seating;
    private String booked;
    private List<CinemaDTO> cinemasDto = new ArrayList<>();

}