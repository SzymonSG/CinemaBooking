package com.cinema.booking.dtos.reservationDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Data
public class ReservationModelDto {

    @NotBlank(message = "Cinema name are required")
    private String cinemaName;
    @NotBlank(message = "Movie name are required")
    private String movieName;
    @NotBlank(message = "Data and times begin movie is required")
    private String dateAndTime;
    private List<Integer> seatsToBooked;
}
