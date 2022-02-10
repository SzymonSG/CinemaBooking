package com.cinema.booking.mapstructDTO;

import com.cinema.booking.entities.Movie;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class PropertiesMovieDTO {
    private Long propertyId;
    private String startTimeOfTheMovieDto;
}
