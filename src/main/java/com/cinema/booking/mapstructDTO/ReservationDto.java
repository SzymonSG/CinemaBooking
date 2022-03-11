package com.cinema.booking.mapstructDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotBlank;

import java.util.List;

@AllArgsConstructor
@Data
public class ReservationDto {

    @NotBlank(message = "Cinema name are required")
    private String cinemaNameDto;
    @NotBlank(message = "Movie name are required")
    private String movieNameDto;
    @NotBlank(message = "Properties are required")
    private String startMovieDto;
    private List<Integer> seatsToBookedDto;
}
