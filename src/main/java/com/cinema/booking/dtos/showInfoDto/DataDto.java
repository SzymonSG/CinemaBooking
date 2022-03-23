package com.cinema.booking.dtos.showInfoDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
public class DataDto {

    @JsonFormat(pattern="yyyy-MM-dd; HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalDateTime startFilm;
}
