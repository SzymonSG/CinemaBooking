package com.cinema.booking.mapper;

import com.cinema.booking.entities.Movie;
import com.cinema.booking.entities.PropertiesMovie;
import com.cinema.booking.mapstructDTO.DataDto;
import com.cinema.booking.mapstructDTO.FreePlaceDto;
import com.cinema.booking.mapstructDTO.RepertoireDto;
import com.cinema.booking.mapstructDTO.ReservationDto;
import com.cinema.booking.payloads.Reservation;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper
public interface ReservationMapper {

    @Mapping(source = "cinemaName",target = "cinemaNameDto")
    @Mapping(source = "movieName",target = "movieNameDto")
    @Mapping(source = "startMovie",target = "startMovieDto",dateFormat = "yyyy-MM-dd; HH:mm:ss")
    @Mapping(source = "seatsToBooked",target = "seatsToBookedDto")
    ReservationDto toReservationDto (Reservation reservation);

    @InheritInverseConfiguration
    Reservation dtoToReservation (ReservationDto reservationDTO);

    //info data movie
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
    FreePlaceDto toFreePlace(Movie movie);
    List<FreePlaceDto> toFreePlaceListDto(List<Movie> movieList);

    @InheritInverseConfiguration
    Movie dtoFreePlacesToMovie(FreePlaceDto freePlaceDto);
    List<Movie> dtoFreePlacesToMovieList(List<FreePlaceDto>freePlaceDtos);


}
