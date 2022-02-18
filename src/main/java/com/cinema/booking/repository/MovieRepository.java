package com.cinema.booking.repository;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.entities.PropertiesMovie;
import com.cinema.booking.mapstructDTO.reservationDTO.BasicInfoAboutMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

    List<Movie> findByMovieNameAndMovieRoom(String movieName, String movieRoom);


    @Query(
            "SELECT m,c,p FROM Movie m JOIN m.cinemas c JOIN m.properitiesMovie p WHERE p.startTimeOfTheMovie = :localDateTime AND m.booked='free'"
    )
    List<Movie> findByLocalDateTime(LocalDateTime localDateTime);

    @Query(
            "SELECT new com.cinema.booking.mapstructDTO.reservationDTO.BasicInfoAboutMovie(m.movieName,m.seating,m.movieRoom,p.startTimeOfTheMovie)" +
                    "FROM Movie m JOIN m.cinemas c JOIN m.properitiesMovie p WHERE p.startTimeOfTheMovie = :localDateTime AND m.booked='free' " +
                    "AND c.cinemaName=:cinemaName"
    )
    List<BasicInfoAboutMovie> getFreePlacesForSelected_CinemaAndDataTime(String cinemaName, LocalDateTime localDateTime);


    //Czy to powinno zwracać List<Optional> czy moze Optional <List<Movie>>
    @Query(
            "SELECT m,c,p FROM Movie m JOIN m.cinemas c JOIN m.properitiesMovie p " +
                    "WHERE c.cinemaName= :cinema AND m.movieName = :movie AND " +
                    "p.startTimeOfTheMovie= :localDateTime"
    )
     List<Movie> getDataCollectionToReservation(String cinema, String movie,
                                                LocalDateTime localDateTime);

    @Query(
            "SELECT m FROM Movie m JOIN m.cinemas c JOIN m.properitiesMovie p WHERE c.cinemaName=:cinemaName " +
                    "AND m.movieName=:movieName AND p.startTimeOfTheMovie=:localDateTime AND m.booked='free'"

    )
    List<Movie> getAllFreePlacesOnMovie(String cinemaName,String movieName, LocalDateTime localDateTime);


    @Query(
            "SELECT m FROM Movie m JOIN m.cinemas c JOIN m.properitiesMovie p WHERE c.cinemaName=:cinemaName GROUP BY m.movieName"
    )
    List<Movie>getAllPlayingMovies(String cinemaName);


    @Query(
            "SELECT p FROM Movie m JOIN m.cinemas c JOIN m.properitiesMovie p WHERE c.cinemaName= :cinemaName " +
                    "AND m.movieName=:movieName GROUP BY p.startTimeOfTheMovie"
    )
    List<PropertiesMovie>getLocalDateTimeForChosenMovie(String cinemaName, String movieName);
}
