package com.cinema.booking.dto2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
//this was form JavaGuide pakcages converting to DTOS from QUERY
public class InfoMoviesDTO {
    private String movieRoom;
    private int seating;
}