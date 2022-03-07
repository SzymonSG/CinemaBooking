package com.cinema.booking.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Data
public class ReservationDTO {

    private LocalDateTime localDateTimeDto;
    private List<Integer> wantedDto;
}
