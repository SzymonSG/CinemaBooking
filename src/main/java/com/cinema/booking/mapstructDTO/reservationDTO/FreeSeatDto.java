package com.cinema.booking.mapstructDTO.reservationDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Max;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FreeSeatDto {

    private String movieNameDto;
    private Integer seatingDto;


}
