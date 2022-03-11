package com.cinema.booking.mapstructDTO;


import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Data
public class MovieDto {
    private Long movieDtoId;
    @NotBlank(message = "Movie name is requierd")
    @NotEmpty(message = "Movie cannot be empty")
    private String movieNameDto;
    private String movieRoomDto;

    @Range(min=1, max =1000, message="Seating available: 0-1000")
    private Integer seatingDto;
    private String bookedDto;

}
