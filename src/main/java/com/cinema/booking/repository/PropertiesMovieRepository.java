package com.cinema.booking.repository;

import com.cinema.booking.entities.ProperitiesMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertiesMovieRepository extends JpaRepository<ProperitiesMovie,Long> {
}
