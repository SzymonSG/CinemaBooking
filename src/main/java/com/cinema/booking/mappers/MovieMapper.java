package com.cinema.booking.mappers;

import com.cinema.booking.dtos.movieDto.MovieDto;
import com.cinema.booking.entities.Movie;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
