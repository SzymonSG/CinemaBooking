package com.cinema.booking.service;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.repository.MovieRepository;
import com.cinema.booking.service.ServiceInterfaces.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class CinemaServiceImplTest {

    @Autowired
    private MovieService movieService;

    @MockBean
    private  MovieRepository movieRepository;

    @BeforeEach
    void setUp() throws MovieNotFoundException {
        Movie movie = Movie.builder()
                .movieId(1L)
                .movieName("Batman")
                .seating(99)
                .booked("free")
                .movieRoom("VIP")
                .build();
        Mockito.when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
    }

    @Test
    @DisplayName("FindByID")
    public void findByIdTest() throws MovieNotFoundException {

        Long expectedId = 1L;
        Movie found = movieService.fetchMovieById(expectedId);
        assertEquals(expectedId,found.getMovieId());

    }
}