package com.cinema.booking.repository;

import com.cinema.booking.entities.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CinemaRepository extends JpaRepository<Cinema,Long> {

    @Query(
            "SELECT c,m FROM Cinema c JOIN c.movies m WHERE c.cinemaName= :cinema AND m.movieName = :movie"
    )
    List<Cinema> getdatabyCinemaAndMovie(String cinema, String movie);


}
