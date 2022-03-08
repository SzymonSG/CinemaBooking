package com.cinema.booking.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ReservationDTOO {

    private String cinemaNameDto;
    private String movieNameDto;
    private String beginMovieDto;
    private List<Integer> placeToBookedDto;
}
