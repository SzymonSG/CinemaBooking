package com.cinema.booking.repository;



import com.cinema.booking.entities.PropertiesMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertiesMovieRepository extends JpaRepository<PropertiesMovie,Long> {

}
