package com.cinema.booking.mapper;


import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CinemaMapStruct {

    CinemaMapStruct INSTANCE = Mappers.getMapper(CinemaMapStruct.class);

    ////coś nowego do komplexowego test






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
