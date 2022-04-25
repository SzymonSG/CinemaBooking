package com.cinema.booking.services.reservationService;

import com.cinema.booking.entities.Cinema;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.entities.PropertiesMovie;
import com.cinema.booking.exceptions.CinemaNotFoundException;
import com.cinema.booking.exceptions.MovieNotFoundException;
import com.cinema.booking.exceptions.PropertyMovieNotFoundException;
import com.cinema.booking.repository.CinemaRepository;
import com.cinema.booking.repository.MovieRepository;
import com.cinema.booking.repository.PropertiesMovieRepository;
import com.cinema.booking.services.cinemaService.CinemaService;
import com.cinema.booking.services.movieService.MovieService;
import com.cinema.booking.services.propertyService.PropertiesMovieService;
import com.cinema.booking.validators.BookingModelValidator;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.cinema.booking.common.ReservationCheck.FREE;


@SpringBootTest
class BookingServiceImplTest {


    @Autowired
    private BookingService bookingService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private CinemaService cinemaService;
    @Autowired
    private PropertiesMovieService propertyService;

    @MockBean
    private MovieRepository movieRepository;

    @MockBean
    private CinemaRepository cinemaRepository;

    @MockBean
    private PropertiesMovieRepository propertiesMovieRepository;

    public Cinema cinemaBuilder(){
        Cinema cinema = Cinema.builder()
                .cinemaId(1L)
                .cinemaName("Bonarka")
                .build();
        return cinema;
    }

    public PropertiesMovie propertiesBuilder(){
        LocalDateTime dateTime = generateLocalDateTime();
        PropertiesMovie propertiesMovie = PropertiesMovie.builder()
                .propertyId(1L)
                .startTimeOfTheMovie(dateTime)
                .build();

        return propertiesMovie;
    }

    public Movie movieBuilder(Cinema cinema,PropertiesMovie propertiesMovie){
        Movie movie = Movie.builder()
                .movieId(1L)
                .movieName("Batman")
                .seating(99)
                .booked(FREE.name())
                .movieRoom("VIP")
                .cinemas(List.of(cinema))
                .properitiesMovie(propertiesMovie)
                .build();

        return movie;
    }

    public BookingModelValidator bookingModelBuilder() {
        LocalDateTime dateTime = generateLocalDateTime();
        List<Integer> seatsToBooked = new ArrayList<>();
        seatsToBooked.add(99);
        BookingModelValidator correctModel = BookingModelValidator.builder()
                .cinemaName("Bonarka")
                .movieName("Batman")
                .movieRoom("VIP")
                .dateAndTime(dateTime)
                .seatsToBooked(seatsToBooked)
                .build();
        return correctModel;
    }

    public LocalDateTime generateLocalDateTime() {
        String str = "2002-04-18; 12:30:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd; HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str,formatter);
        return dateTime;
    }

    @BeforeEach()
    void setUP() throws MovieNotFoundException, CinemaNotFoundException {
        Cinema cinema = cinemaBuilder();
        PropertiesMovie propertiesMovie = propertiesBuilder();
        Movie movie = movieBuilder(cinema, propertiesMovie);

        ArrayList<Movie> listMovies = new ArrayList<>();
        listMovies.add(movie);


        Mockito.when(movieRepository.fetchDataToBooking(
                cinema.getCinemaName(),
                movie.getMovieName(),
                movie.getMovieRoom(),
                propertiesMovie.getStartTimeOfTheMovie()
        )).thenReturn(listMovies);

        Mockito.when(movieRepository.save(movie)).thenReturn(movie);
        Mockito.when(cinemaRepository.save(cinema)).thenReturn(cinema);
        Mockito.when(propertiesMovieRepository.save(propertiesMovie)).thenReturn(propertiesMovie);

        Mockito.when(cinemaService.enrolledCinemaToMovie(movie.getMovieId(),cinema.getCinemaId()))
                .thenReturn((Cinema) cinema.getMovies());

//        Mockito.when(movieService.enrolledPropertiesToMovie(movie.getMovieId(),propertiesMovie.getPropertyId()))
//                        .thenReturn(movie.assignProperty(propertiesMovie));


        Mockito.when(cinemaRepository.findById(cinema.getCinemaId())).thenReturn(Optional.of(cinema));
        Mockito.when(movieRepository.findById(movie.getMovieId())).thenReturn(Optional.of(movie));
        Mockito.when(propertiesMovieRepository.findById(propertiesMovie.getPropertyId())).thenReturn(Optional.of(propertiesMovie));



    }


    @Test
    @DisplayName("Test Booking method, in overview scenario")
    public void checkMovieServiceCorecctlyBooked() throws MovieNotFoundException, CinemaNotFoundException, PropertyMovieNotFoundException {

        //given
        Cinema cinema = cinemaBuilder();
        PropertiesMovie propertiesMovie = propertiesBuilder();
        Movie movie = movieBuilder(cinema, propertiesMovie);
        List<Movie> listMovie = new ArrayList<>();
        listMovie.add(movie);
        System.out.println("Generate movie list to Compare: "+listMovie);

        //when
        cinemaService.cinemaSave(cinema);
        movieService.movieSave(movie);
        propertyService.propertySave(propertiesMovie);
        cinemaService.enrolledCinemaToMovie(1L,1L);
        movieService.enrolledPropertiesToMovie(1L,1L);

        BookingModelValidator correctModel = bookingModelBuilder();
        List<Movie> bookedSeats = bookingService.bookingSeats(correctModel);
        System.out.println("This is bookedSeats from bookingSeats(): "+ bookedSeats);

        //then
        //FREE->BOOKED
        Assertions.assertIterableEquals(listMovie,bookedSeats);
    }


}