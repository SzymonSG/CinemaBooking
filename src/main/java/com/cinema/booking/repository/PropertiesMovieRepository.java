package com.cinema.booking.repository;



import com.cinema.booking.entities.PropertiesMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PropertiesMovieRepository extends JpaRepository<PropertiesMovie,Long> {

    boolean existsByStartTimeOfTheMovie(LocalDateTime property);
}
