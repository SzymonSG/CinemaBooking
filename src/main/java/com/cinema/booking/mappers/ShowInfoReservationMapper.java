package com.cinema.booking.mappers;


import com.cinema.booking.dtos.showInfoDto.AvailableSeatsDto;
import com.cinema.booking.dtos.showInfoDto.AvailableSeatsInfoDto;
import com.cinema.booking.entities.Movie;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {PropertiesMapper.class})
public interface ShowInfoReservationMapper{

    //repertuar
//    @Mapping(source = "movieName",target = "filmNameDto")
//    RepertoireDto toMovieNameDto(Movie movie);
//    List<RepertoireDto> toMovieNameListDto(List<Movie> movieList);


    @Mapping(source = "seating",target = "freePlaceDto")
    @Mapping(source = "movieRoom",target = "movieHallDto")
    AvailableSeatsDto toAvailableSeatsDto(Movie movie);
    List<AvailableSeatsDto> toAvailableSeatsListDto(List<Movie> movieList);

    @InheritInverseConfiguration
    Movie dtoAvailableSeats(AvailableSeatsDto freeSeatsDto);
    List<Movie> dtoAvailableSeatsList(List<AvailableSeatsDto>freeSeatsDtos);

    @Mapping(source = "seating",target = "freePlaceDto")
    @Mapping(source = "movieRoom",target = "movieHallDto")
    AvailableSeatsInfoDto toAvailableSeatsInfoDto(Movie movie);
    List<AvailableSeatsInfoDto> toAvailableSeatsInfoListDto(List<Movie>movieList);

}
