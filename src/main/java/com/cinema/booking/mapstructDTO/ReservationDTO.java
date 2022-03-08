package com.cinema.booking.mapstructDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Data
public class ReservationDTO {

    private String cinemaNameDto;
    private String movieNameDto;
    private String localDateTimeDto;
    private List<Integer> wantedDto;
}
