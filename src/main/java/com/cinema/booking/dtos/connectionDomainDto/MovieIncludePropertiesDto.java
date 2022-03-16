package com.cinema.booking.dtos.connectionDomainDto;

import com.cinema.booking.dtos.propertyDto.PropertiesMovieDto;
import lombok.Data;

@Data
public class MovieIncludePropertiesDto {
    private Long movieDtoId;
    private String movieNameDto;
    private String movieRoomDto;
    private Integer seatingDto;
    private String bookedDto;

    private PropertiesMovieDto properitiesMovie;
}
