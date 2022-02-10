package com.cinema.booking.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class CinemaZ {

    private Long cinemaId;
    private String cinemaName;
    private List<MovieBez> moviesDto = new ArrayList<>();
}
