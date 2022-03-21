package com.cinema.booking.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ReservationModel {

    private String cinemaName;
    private String movieName;
    private String movieRoom;
    @JsonFormat(pattern="yyyy-MM-dd; HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalDateTime dateAndTime;
    private List<Integer> seatsToBooked;

}
