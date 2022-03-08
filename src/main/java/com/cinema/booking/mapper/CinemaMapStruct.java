package com.cinema.booking.mapper;


import com.cinema.booking.entities.Cinema;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.entities.PropertiesMovie;
import com.cinema.booking.mapstructDTO.*;
import com.cinema.booking.mapstructDTO.MovieNameDto;
import com.cinema.booking.payloads.MovieName;
import com.cinema.booking.payloads.Reservation;
import org.mapstruct.*;

import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
//(componentModel = "spring")
public interface CinemaMapStruct {

    CinemaMapStruct INSTANCE = Mappers.getMapper(CinemaMapStruct.class);

    //sprawdzimy deafualt bo moze to deafualt warunkuje to ustawienie na stałe, zeby zmniejszyć ilość mappingow
    //podstawowe składowe DTOsów
    @Mapping(source = "cinema.cinemaId",target = "cinemaDtoId")
    @Mapping(source = "cinema.cinemaName",target = "cinemaNameDto")
    CinemaDto toCinemaDto(Cinema cinema);


    @Mapping(source = "movieId",target = "movieDtoId")
    @Mapping(source = "movieName",target = "movieNameDto")
    @Mapping(source = "movieRoom",target = "movieRoomDto")
    @Mapping(source = "seating",target = "seatingDto")
    @Mapping(source = "booked",target = "bookedDto")
    MovieDto toMovieDto(Movie movie);


    @Mapping(source = "propertyId",target = "propertyDtoId")
    @Mapping(source = "startTimeOfTheMovie",target = "startTimeOfTheMovieDto",dateFormat = "yyyy-MM-dd; HH:mm:ss")
    PropertiesMovieDto toPropertiesMovieDto(PropertiesMovie properitiesMovie);
    List<PropertiesMovieDto>toPropertiesMovieListDto(List<PropertiesMovie> propertiesMovie);

    // Cinemas With Movies and Movies With Cinemas

    @Mapping(source = "cinema.cinemaId",target = "cinemaDtoId")
    @Mapping(source = "cinema.cinemaName",target = "cinemaNameDto")
    CinemaWithMovieDto toCinemaWithMovieDto(Cinema cinema);
    List<CinemaWithMovieDto>toCinemaWithMovieListDto(List<Cinema>cinemaList);

    @Mapping(source = "movieId",target = "movieDtoId")
    @Mapping(source = "movieName",target = "movieNameDto")
    @Mapping(source = "movieRoom",target = "movieRoomDto")
    @Mapping(source = "seating",target = "seatingDto")
    @Mapping(source = "booked",target = "bookedDto")
    MovieWithCinemaDto toMovieWithCinemaDto(Movie movie);
    List<MovieWithCinemaDto>toMovieWithCinemaListDto(List<Movie>movieList);

    @Mapping(source = "cinemaName",target = "cinemaNameDto")
    @Mapping(source = "movieName",target = "movieNameDto")
    @Mapping(source = "startMovie",target = "startMovieDto",dateFormat = "yyyy-MM-dd; HH:mm:ss")
    @Mapping(source = "seatsToBooked",target = "seatsToBookedDto")
    ReservationDto toReservationDto (Reservation reservation);

    @InheritInverseConfiguration
    Reservation dtoToReservation (ReservationDto reservationDTO);




    ///ComplexMovieDto
    @Mapping(source = "movieId",target = "movieDtoId")
    @Mapping(source = "movieName",target = "movieNameDto")
    @Mapping(source = "movieRoom",target = "movieRoomDto")
    @Mapping(source = "seating",target = "seatingDto")
    @Mapping(source = "booked",target = "bookedDto")
    ComplexMovieDto toComplexMovieDto(Movie movie);
    List<ComplexMovieDto> toComplexMovieListDto(List<Movie>movieList);

    //trying

    @Mapping(source = "seating",target = "freePlaceDto")
    @Mapping(source = "movieRoom",target = "movieHallDto")
    FreePlaceDto toFreePlace(Movie movie);
    List<FreePlaceDto> toFreePlaceListDto(List<Movie> movieList);

    //TODO !!!!!!!!!!!!!!
    //to chyba do usunięcia będzie/
    //repertuarDTO
    @Mapping(source = "movieName",target = "filmName")
    MovieNameDto toMovieNameDto(Movie movie);
    List<MovieNameDto> toMovieNameListDto(List<Movie> movieList);

    //zastanów sie nad wersja
    @Mapping(source = "movieName",target = "filmName")
    MovieNameDto toMovieNamesDto(MovieName movieName);
    List<MovieNameDto> toMovieNamesListDto (List<MovieName> movieNames);





    @Mapping(source = "startTimeOfTheMovie",target = "startFilm",dateFormat = "yyyy-MM-dd; HH:mm:ss")
    DataDto toDataDto(PropertiesMovie propertiesMoviemovie);
    List<DataDto> toDataDtoListMovie(List<PropertiesMovie>propertiesMovieList);


//    @Mapping(source = "movieRoom",target = "movieRoomDto")
//    @Mapping(source = "seating",target = "seatingDto")
//    InfoMovieDto toInfoMovieDto(Movie movie);
//    List<InfoMovieDto> toInfoMovieListDto(List<Movie> infoMovieDtoList);

    //only one
    @Mapping(source = "movieId",target = "movieDtoId")
    @Mapping(source = "movieName",target = "movieNameDto")
    @Mapping(source = "movieRoom",target = "movieRoomDto")
    @Mapping(source = "seating",target = "seatingDto")
    @Mapping(source = "booked",target = "bookedDto")
    MovieIncludePropertiesDto toMovieIncludePropertiesDto (Movie movie);




    ///////////////// składowe
    @InheritInverseConfiguration
    Cinema dtoToCinema(CinemaDto cinemaDto);
    @InheritInverseConfiguration
    Movie dtoToMovie(MovieDto movieDto);
    @InheritInverseConfiguration
    PropertiesMovie dtoToPropertiesMovie(PropertiesMovieDto propertiesMovieDto);



    //cinemaWithMovie and movieWithCinema
    @InheritInverseConfiguration
    Cinema dtoCinemaWithMovieToCinema(CinemaWithMovieDto cinemaWithMovieDto);
    List<Cinema> dtoCinemaWithMovieToCinemaList(List<CinemaWithMovieDto> cinemaWithMovieDtos);

    @InheritInverseConfiguration
    Movie dtoMovieWithCinemaToMovie(MovieWithCinemaDto movieWithCinemaDto);
    List<Movie>dtoMovieWithCinemaToMovieList(List<MovieWithCinemaDto>movieWithCinemaDtos);


    ///////////////complex
    @InheritInverseConfiguration
    Movie dtoComplexMovieToMovie(ComplexMovieDto complexMovieDto);
    List<Movie>dtoComplexMovieToMovieList(List<CinemaWithMovieDto> cinemaWithMovieDtos);

    //freeplaces
    @InheritInverseConfiguration
    Movie dtoFreePlacesToMovie(FreePlaceDto freePlaceDto);
    List<Movie> dtoFreePlacesToMovieList(List<FreePlaceDto>freePlaceDtos);

    //movieNames
    @InheritInverseConfiguration
    Movie dtoMovieNamesToMovie(MovieNameDto freePlaceDto);
    List<Movie> dtoMovieNamesToMovieList(List<MovieNameDto>freePlaceDtos);

    //DataDto
    @InheritInverseConfiguration
    PropertiesMovie dtoDataDtoToPropertiesMovie(DataDto dataDTO);
    List<PropertiesMovie>dtoDataDtoToPropertiesListMovie(List<DataDto>dataDtos);


}
