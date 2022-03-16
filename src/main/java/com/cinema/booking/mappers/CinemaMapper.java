package com.cinema.booking.mappers;

import com.cinema.booking.dtos.cinemaDto.CinemaDto;
import com.cinema.booking.entities.Cinema;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CinemaMapper {

    @Mapping(source = "cinema.cinemaId",target = "cinemaDtoId")
    @Mapping(source = "cinema.cinemaName",target = "cinemaNameDto")
    CinemaDto toCinemaDto(Cinema cinema);

    @InheritInverseConfiguration
    Cinema dtoToCinema(CinemaDto cinemaDto);



}
