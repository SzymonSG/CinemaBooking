package com.cinema.booking.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class PropertiesMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propertyId;
    @JsonFormat(pattern="yyyy-MM-dd; HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalDateTime startTimeOfTheMovie;

    @JsonIgnore
    @OneToMany(mappedBy = "properitiesMovie")
    private Set<Movie> movie;


}
