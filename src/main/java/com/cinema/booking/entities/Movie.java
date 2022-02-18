package com.cinema.booking.entities;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    @NotBlank(message = "Movie name is requierd")
    @NotEmpty(message = "Movie cannot be empty")
    private String movieName;
    //Intgers i min max
    private String movieRoom;
//    @Range(min=0, max =1000, message="Seating")
    @Min(value = 1)
    @Max(value = 100, message = "Maksymalna wartość 100")
    private Integer seating;
    //tutaj zmienić na boolan +service
    private String booked;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "movies")
    private List<Cinema> cinemas = new ArrayList<>();


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "property_id",referencedColumnName = "propertyId")
    private PropertiesMovie properitiesMovie;

    public void assignProperty(PropertiesMovie properitiesMovie) {
        this.properitiesMovie = properitiesMovie;
    }

}

