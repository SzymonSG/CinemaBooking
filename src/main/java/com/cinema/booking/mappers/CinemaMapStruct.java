package com.cinema.booking.mappers;


import com.cinema.booking.dtos.connectionDomainDto.CinemaWithMovieDto;
import com.cinema.booking.dtos.connectionDomainDto.ComplexMovieDto;
import com.cinema.booking.dtos.connectionDomainDto.MovieIncludePropertiesDto;
import com.cinema.booking.dtos.connectionDomainDto.MovieWithCinemaDto;
import com.cinema.booking.entities.Cinema;
import com.cinema.booking.entities.Movie;
import org.mapstruct.*;

import java.util.List;


@Mapper(uses = {CinemaMapper.class,MovieMapper.class,PropertiesMapper.class})
//(componentModel = "spring")
public interface CinemaMapStruct {

//    CinemaMapStruct INSTANCE = Mappers.getMapper(CinemaMapStruct.class);

    @Mapping(source = "cinema.cinemaId",target = "cinemaDtoId")
    @Mapping(source = "cinema.cinemaName",target = "cinemaNameDto")
    CinemaWithMovieDto toCinemaWithMovieDto(Cinema cinema);
    List<CinemaWithMovieDto>toCinemaWithMovieListDto(List<Cinema>cinemaList);

    @Mapping(source = "movieId",target = "movieDtoId")
    @Mapping(source = "movieName",target = "movieNameDto")
    @Mapping(source = "movieRoom",target = "movieRoomDto")
    @Mapping(source = "seating",target = "seatingDto")
    @Mapping(source = "booked",target = "bookedDto")
    MovieWithCinemaDto toMovieWithCinemaDto(Movie movie);
    List<MovieWithCinemaDto>toMovieWithCinemaListDto(List<Movie>movieList);

    ///ComplexMovieDto
    @Mapping(source = "movieId",target = "movieDtoId")
    @Mapping(source = "movieName",target = "movieNameDto")
    @Mapping(source = "movieRoom",target = "movieRoomDto")
    @Mapping(source = "seating",target = "seatingDto")
    @Mapping(source = "booked",target = "bookedDto")
    ComplexMovieDto toComplexMovieDto(Movie movie);
    List<ComplexMovieDto> toComplexMovieListDto(List<Movie>movieList);

    @Mapping(source = "movieId",target = "movieDtoId")
    @Mapping(source = "movieName",target = "movieNameDto")
    @Mapping(source = "movieRoom",target = "movieRoomDto")
    @Mapping(source = "seating",target = "seatingDto")
    @Mapping(source = "booked",target = "bookedDto")
    MovieIncludePropertiesDto toMovieIncludePropertiesDto (Movie movie);


    @InheritInverseConfiguration
    Cinema dtoCinemaWithMovieToCinema(CinemaWithMovieDto cinemaWithMovieDto);
    List<Cinema> dtoCinemaWithMovieToCinemaList(List<CinemaWithMovieDto> cinemaWithMovieDtos);

    @InheritInverseConfiguration
    Movie dtoMovieWithCinemaToMovie(MovieWithCinemaDto movieWithCinemaDto);
    List<Movie>dtoMovieWithCinemaToMovieList(List<MovieWithCinemaDto>movieWithCinemaDtos);

    ///////////////complex
    @InheritInverseConfiguration
    Movie dtoComplexMovieToMovie(ComplexMovieDto complexMovieDto);
    List<Movie> dtoComplexMovieToMovieList(List<CinemaWithMovieDto> cinemaWithMovieDtos);

}
