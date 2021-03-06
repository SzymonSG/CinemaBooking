package com.cinema.booking.repository;

import com.cinema.booking.entities.Cinema;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema,Long> {

    boolean existsByCinemaName(String cinemaName);

    @Query(
            "SELECT c,m FROM Cinema c JOIN c.movies m WHERE c.cinemaName= :cinema AND m.movieName = :movie"
    )
    List<Cinema> getdatabyCinemaAndMovie(String cinema, String movie);



}
