package com.cinema.booking.mapstructDTO.reservationDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BasicInfoAboutMovie {


    private String movieRoomDto;
    private Integer seatingDto;
    private String cinemaHall;
    @JsonFormat(pattern="yyyy-MM-dd; HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalDateTime beginMovieDto;


}
