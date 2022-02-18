package com.cinema.booking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cinemaId;

    @NotBlank(message = "Cinema name is required")
    @NotNull(message = "Cinema cannot be null")
    private String cinemaName;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "seance",
            joinColumns = @JoinColumn(
                    name = "cinema_ID",
                    referencedColumnName = "cinemaId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "movie_ID",
                    referencedColumnName ="movieId"
            )
    )
    private List<Movie> movies = new ArrayList<>();

    public void enrolledMovie(Movie movie){
        movies.add(movie);
    }

}
