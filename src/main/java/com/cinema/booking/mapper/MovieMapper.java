package com.cinema.booking.mapper;

import com.cinema.booking.entities.Movie;
import com.cinema.booking.mapstructDTO.FreePlaceDto;
import com.cinema.booking.mapstructDTO.MovieDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface MovieMapper {

    @Mapping(source = "movieId",target = "movieDtoId")
    @Mapping(source = "movieName",target = "movieNameDto")
    @Mapping(source = "movieRoom",target = "movieRoomDto")
    @Mapping(source = "seating",target = "seatingDto")
    @Mapping(source = "booked",target = "bookedDto")
    MovieDto toMovieDto(Movie movie);

    @InheritInverseConfiguration
    Movie dtoToMovie(MovieDto movieDto);


    //freePlace



}
