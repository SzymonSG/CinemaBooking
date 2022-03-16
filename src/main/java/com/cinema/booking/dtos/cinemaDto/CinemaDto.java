package com.cinema.booking.dtos.cinemaDto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class CinemaDto {
    private Long cinemaDtoId;
    @NotBlank(message = "Cinema name is required")
    @NotNull(message = "Cinema cannot be null")
    private String cinemaNameDto;
}
