package com.cinema.booking.dtos.showInfoDto;

import com.cinema.booking.dtos.propertyDto.PropertiesMovieDto;
import lombok.Data;

@Data
public class AvailableSeatsInfoDto {
    private Integer freePlaceDto;
    private String movieHallDto;
    private PropertiesMovieDto properitiesMovie;
}
