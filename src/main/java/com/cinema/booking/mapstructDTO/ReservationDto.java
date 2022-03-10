package com.cinema.booking.mapstructDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ReservationDto {

    private String cinemaNameDto;
    private String movieNameDto;
    private String startMovieDto;
    private List<Integer> seatsToBookedDto;
}
