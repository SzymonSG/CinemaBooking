package com.cinema.booking.repository;
import com.cinema.booking.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,Long> {

    List<Movie> findByMovieNameAndMovieRoom(String movieName, String movieRoom);


    @Query(
            "SELECT m,c FROM Movie m JOIN m.cinemas c WHERE c.cinemaName= :cinema AND m.movieName = :movie"
    )
    List<Movie> wlasciwe(String cinema, String movie);

    @Query(
            "SELECT m,c,p FROM Movie m JOIN m.cinemas c JOIN m.properitiesMovie p WHERE p.startTimeOfTheMovie = :localDateTime"
    )
    List<Movie> seacherByDatetimeJacson(LocalDateTime localDateTime);


    //Czy to powinno zwracać List<Optional> czy moze Optional <List<Movie>>
    @Query(
            "SELECT m,c,p FROM Movie m JOIN m.cinemas c JOIN m.properitiesMovie p " +
                    "WHERE c.cinemaName= :cinema AND m.movieName = :movie AND " +
                    "p.startTimeOfTheMovie= :localDateTime"
    )
     List<Movie>searchByDiffrentFieldsWithDate(String cinema, String movie,
                                               LocalDateTime localDateTime);

}
