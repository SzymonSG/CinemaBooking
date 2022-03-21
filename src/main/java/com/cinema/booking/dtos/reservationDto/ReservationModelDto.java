package com.cinema.booking.dtos.reservationDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class ReservationModelDto {

    @NotBlank(message = "Cinema name is required")
    private String cinemaName;
    @NotBlank(message = "Movie name is required")
    private String movieName;
    @NotBlank(message = "Movie room is requierd")
    private String movieRoom;
    @NotBlank(message = "Data and times begin movie are required")
    private String dateAndTime;
    private List<Integer> seatsToBooked;
}
