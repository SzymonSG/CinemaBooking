package com.cinema.booking.mapstructDTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PropertiesMovieDto {
    private Long propertyDtoId;
    @NotBlank(message = "Properties are required")
    private String startTimeOfTheMovieDto;
}
