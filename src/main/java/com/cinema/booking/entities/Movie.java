package com.cinema.booking.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
//@Table(uniqueConstraints={@UniqueConstraint(columnNames={"seating"})})
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    private String movieName;

    private String movieRoom;

    private Integer seating;

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

