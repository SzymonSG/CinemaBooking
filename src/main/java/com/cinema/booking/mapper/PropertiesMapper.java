package com.cinema.booking.mapper;


import com.cinema.booking.entities.PropertiesMovie;
import com.cinema.booking.mapstructDTO.DataDto;
import com.cinema.booking.mapstructDTO.PropertiesMovieDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface PropertiesMapper {

    //Complex
    @Mapping(source = "propertyId",target = "propertyDtoId")
    @Mapping(source = "startTimeOfTheMovie",target = "startTimeOfTheMovieDto",dateFormat = "yyyy-MM-dd; HH:mm:ss")
    PropertiesMovieDto toPropertiesMovieDto(PropertiesMovie properitiesMovie);
    List<PropertiesMovieDto> toPropertiesMovieListDto(List<PropertiesMovie> propertiesMovie);


    @InheritInverseConfiguration
    PropertiesMovie dtoToPropertiesMovie(PropertiesMovieDto propertiesMovieDto);





}
