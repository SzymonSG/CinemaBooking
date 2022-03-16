package com.cinema.booking.mappers;

import com.cinema.booking.dtos.reservationDto.ReservationModelDto;
import com.cinema.booking.model.ReservationModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper
public interface ReservationMapper {


    @Mapping(source = "dateAndTime",target = "dateAndTime",dateFormat = "yyyy-MM-dd; HH:mm:ss")
    ReservationModelDto toReservationDto (ReservationModel reservation);

    @InheritInverseConfiguration
    ReservationModel dtoToReservation (ReservationModelDto reservationDTO);
//



}
