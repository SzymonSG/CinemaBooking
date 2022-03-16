package com.cinema.booking.dtos.movieDto;


import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class MovieDto {
    private Long movieDtoId;

    @NotBlank(message = "Movie name is requierd")
    @NotEmpty(message = "Movie cannot be empty")
    private String movieNameDto;

    @NotBlank(message = "Movie room is requierd")
    private String movieRoomDto;

    @NotNull(message = "Seating place is requierd")
    @Range(min=1, max =100, message="Seating available: 0-100")
    private Integer seatingDto;
    private String bookedDto;

}
