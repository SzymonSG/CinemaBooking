package com.cinema.booking.services.propertyService;

import com.cinema.booking.entities.PropertiesMovie;
import com.cinema.booking.exceptions.PropertyMovieNotFoundException;

public interface PropertiesMovieService {
    //TODO
    public PropertiesMovie propertySave(PropertiesMovie properitiesMovie);

    public PropertiesMovie fetchPropertyMovieById(Long propertyId) throws PropertyMovieNotFoundException;
}
