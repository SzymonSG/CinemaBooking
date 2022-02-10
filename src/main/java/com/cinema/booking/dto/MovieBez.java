package com.cinema.booking.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieBez {

    private Long movieId;
    private String movieName;
    private String movieRoom;
    private Integer seating;
    private String booked;
}