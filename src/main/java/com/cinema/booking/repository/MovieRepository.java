package com.cinema.booking.repository;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.entities.PropertiesMovie;
import com.cinema.booking.dtos.showInfoDto.BasicInfoAboutMovieDto;
import com.cinema.booking.dtos.RepertoireDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {


    boolean existsBySeatingAndMovieNameAndMovieRoom(Integer seating,String movieName, String movieRoom);

    @Query(
            "SELECT new com.cinema.booking.dtos.showInfoDto.BasicInfoAboutMovieDto" +
                    "(m.movieName,m.seating,m.movieRoom,p.startTimeOfTheMovie)" +
                    "FROM Movie m JOIN m.cinemas c JOIN m.properitiesMovie p " +
                    "WHERE p.startTimeOfTheMovie = :localDateTime AND m.booked='FREE' " +
                    "AND c.cinemaName=:cinemaName"
    )
    List<BasicInfoAboutMovieDto> fetchFreePlacesForSelected_CinemaAndDataTime(String cinemaName, LocalDateTime localDateTime);


    @Query(
            "SELECT m,c,p FROM Movie m JOIN m.cinemas c JOIN m.properitiesMovie p " +
                    "WHERE c.cinemaName= :cinema" +
                    " AND m.movieName = :movie " +
                    "AND m.movieRoom= :movieRoom " +
                    "AND p.startTimeOfTheMovie= :localDateTime"
    )
     List<Movie> fetchDataToReservation(String cinema, String movie, String movieRoom,
                                        LocalDateTime localDateTime);


    @Query(
            "SELECT m,c,p FROM Movie m JOIN m.cinemas c JOIN m.properitiesMovie p WHERE c.cinemaName=:cinemaName"
    )
    List<Movie> fetchAllDataFromCinema(String cinemaName);

    @Query(
            "SELECT m FROM Movie m JOIN m.cinemas c JOIN m.properitiesMovie p WHERE c.cinemaName=:cinemaName " +
                    "AND m.movieName=:movieName AND p.startTimeOfTheMovie=:localDateTime AND m.booked='FREE'"

    )
    List<Movie> fetchAvialableSeatsWithDateTimeOnSeance(String cinemaName, String movieName, LocalDateTime localDateTime);

    @Query(
            "SELECT m FROM Movie m JOIN m.cinemas c JOIN m.properitiesMovie p WHERE c.cinemaName=:cinemaName " +
                    "AND m.movieName=:movieName AND m.booked='FREE'"

    )
    List<Movie> fetchAvialableSeatsOnSeance(String cinemaName, String movieName);

//    @Query(
//            "SELECT m FROM Movie m JOIN m.cinemas c JOIN m.properitiesMovie p WHERE c.cinemaName=:cinemaName " +
//                    "GROUP BY m.movieName, m.movieId"
//    )
    @Query(
            "SELECT DISTINCT new com.cinema.booking.dtos.RepertoireDTO(m.movieName)" +
                    "FROM Movie m JOIN m.cinemas c JOIN m.properitiesMovie p WHERE c.cinemaName=:cinemaName"
    )
    List<RepertoireDTO> fetchAllPlayingMovies(String cinemaName);


    @Query(
            "SELECT p FROM Movie m JOIN m.cinemas c JOIN m.properitiesMovie p WHERE c.cinemaName= :cinemaName " +
                    "AND m.movieName=:movieName GROUP BY p.startTimeOfTheMovie, p.propertyId"
    )
    List<PropertiesMovie> fetchLocalDateTimeForChosenMovie(String cinemaName, String movieName);
}
