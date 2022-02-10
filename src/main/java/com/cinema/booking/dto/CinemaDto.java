package com.cinema.booking.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CinemaDto {

    private Long cinemaId;
    private String cinemaName;
}
