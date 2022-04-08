package com.cinema.booking.mappers;

import com.cinema.booking.dtos.bookingDto.BookingModelDto;
import com.cinema.booking.model.BookingModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper
public interface BookingMapper {


    @Mapping(source = "dateAndTime",target = "dateAndTime",dateFormat = "yyyy-MM-dd; HH:mm:ss")
    BookingModelDto toReservationDto (BookingModel booked);

    @InheritInverseConfiguration
    BookingModel dtoToReservation (BookingModelDto bookedDto);



}
