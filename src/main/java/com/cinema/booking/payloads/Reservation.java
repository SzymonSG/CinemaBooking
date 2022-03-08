package com.cinema.booking.payloads;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class Reservation {

    private String cinemaName;
    private String movieName;
    @JsonFormat(pattern="yyyy-MM-dd; HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalDateTime startMovie;
    private List<Integer> seatsToBooked;

}
