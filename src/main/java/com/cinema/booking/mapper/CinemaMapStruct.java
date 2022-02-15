package com.cinema.booking.mapper;


import com.cinema.booking.entities.Cinema;
import com.cinema.booking.entities.Movie;
import com.cinema.booking.entities.PropertiesMovie;
import com.cinema.booking.mapstructDTO.*;
import com.cinema.booking.mapstructDTO.MovieNameDto;
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

    @Mapping(source = "movieName",target = "filmName")
    MovieNameDto toMovieNameDto(Movie movie);
    List<MovieNameDto> toMovieNameListDto(List<Movie> movieList);

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

    //InfoMovie
//    @InheritInverseConfiguration
//    Movie dtoInfoMovieToMovie(InfoMovieDto infoMovieDto);
//    List<Movie>dtoInfoMovieToMovieList(List<InfoMovieDto>infoMovieDtos);


    






//    @Mapping(target = "startTimeOfTheMovie",source = "startTimeOfTheMovie",dateFormat = "yyyy-MM-dd; HH:mm:ss")
//    PropertiesMovieDTO toPropertyDto(ProperitiesMovie properitiesMovie);
//    @InheritInverseConfiguration
//    ProperitiesMovie dtoToProperty(PropertiesMovieDTO propertiesMovieDTO);
//    @Mapping(source = "cinema.cinemaName",target = "cinemaNameDto",defaultValue ="cinemaNameDto")
//    CinemaDTOComplex toCinemaDTOComplex(Cinema cinema);
//    @InheritInverseConfiguration
//    Cinema dtoToCinemaDTOComplex(CinemaDTOComplex cinemaDTO2);
//    // to nie jest konieczne= map struct robi to za nas - o ile dobrze zdefinujesz DTO.
////    List<CinemaDTO2> toCinemaListDto2(List<Cinema>cinemas);
////    List<Cinema> dtoToCinemaDto2(List<CinemaDTO2>cinemaDTO2List);
//    ComplexMovieDTO toComplexMovieDto(Movie movie);
//    Movie dtoToComplexMovie(ComplexMovieDTO complexMovieDTO);
//
//    List<ComplexMovieDTO>toComplexListMovieDto(List<Movie> movieList);
//    List<Movie> dtoToComplexMovieList (List<ComplexMovieDTO> complexMovieDTOList);
//
//
//    ///////////////////////movies with cinemas this method
//    MovieDTOComplex movieToDto(Movie movie);
//    Movie dtoToMovie(MovieDTOComplex movieDTOComplex);
//
//    MovieDTO movieCinemaToDto(Movie movie);
//    Movie dtoToMovieCinema(MovieDTO movieDTO);
//
//    List<MovieDto>movieCinemaListToDto(List<Movie> movieList);
//    List<Movie>dtosToMovieCinemaList(List<MovieDto>movieDtoList);
//
//    @Mapping(source = "cinema.cinemaName",target = "cinemaNameDto",defaultValue ="cinemaNameDto")
//    CinemaDTO cinemaMovieToDto(Cinema cinema);
//    @InheritInverseConfiguration
//    Cinema dtoToCinema(CinemaDTO cinemaDTO);
//
//    List<CinemaDTO>cinemaMovieListToDto(List<Cinema> cinemaList);
//    List<Cinema>dtosToCinemaMovieList(List<CinemaDTO>cinemaDTOS);



    //To Dto
    //@Mapping(source = "cinema.movies", target = "moviesDto")
//    @Mapping(source = "cinema.cinemaName",target = "cinemaNameDto",defaultValue ="cinemaNameDto")
//    CinemaDTO toCinemaDto(Cinema cinema);
//
//    //list jest potrzebne jesli wewnątrz DTO korzystamy np. z listy Encji a nie DTO.
//    @Mapping(source = "cinema.cinemaName",target = "cinemaNameDto",defaultValue ="cinemaNameDto")
//    List<CinemaDTO> toCinemaListDto(List<Cinema>cinemas);
//
//
//    MovieDTO toMovieDto(Movie movie);
//
//    List<MovieDTO> toMovieListDto (List<Movie>movies);
//
//    ///From Dto
//
//    ////////////////toooooooooooo
//    @InheritInverseConfiguration
//    Cinema dtoToCinema(CinemaDTO cinemaDTO);
//    Movie dtoToMovie(MovieDTO movieDTO);
//
//    @InheritInverseConfiguration
//    List<Cinema> dtoToCinemaEntitiesList (List<CinemaDTO> cinemaDTOS);
//
//    List<Movie> dtoToMovieEntitiesList(List<MovieDTO> movieDTOS);




}
