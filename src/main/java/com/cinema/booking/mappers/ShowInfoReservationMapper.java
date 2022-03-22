package com.cinema.booking.mappers;


import com.cinema.booking.dtos.showInfoDto.AvailableSeatsDto;
import com.cinema.booking.dtos.showInfoDto.AvailableSeatsInfoDto;
import com.cinema.booking.dtos.showInfoDto.DataDto;
import com.cinema.booking.dtos.showInfoDto.RepertoireDto;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.entities.PropertiesMovie;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {PropertiesMapper.class})
public interface ShowInfoReservationMapper{
    //    //info data movie
    @Mapping(source = "startTimeOfTheMovie",target = "startFilm",dateFormat = "yyyy-MM-dd; HH:mm:ss")
    DataDto toDataDto(PropertiesMovie propertiesMoviemovie);
    List<DataDto> toDataDtoListMovie(List<PropertiesMovie>propertiesMovieList);


    @InheritInverseConfiguration
    PropertiesMovie dtoDataDtoToPropertiesMovie(DataDto dataDTO);
    List<PropertiesMovie>dtoDataDtoToPropertiesListMovie(List<DataDto>dataDtos);


    //repertuar
    @Mapping(source = "movieName",target = "filmNameDto")
    RepertoireDto toMovieNameDto(Movie movie);
    List<RepertoireDto> toMovieNameListDto(List<Movie> movieList);


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
