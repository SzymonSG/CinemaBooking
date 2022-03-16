package com.cinema.booking.services.propertyService;

import com.cinema.booking.entities.PropertiesMovie;
import com.cinema.booking.exceptions.PropertyMovieNotFoundException;
import com.cinema.booking.repository.PropertiesMovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PropertyMovieServiceImpl implements PropertiesMovieService {

    private final PropertiesMovieRepository propertiesMovieRepository;

    @Override
    public PropertiesMovie propertySave(PropertiesMovie properitiesMovie) {
        return propertiesMovieRepository.save(properitiesMovie);
    }

    @Override
    public PropertiesMovie fetchPropertyMovieById(Long propertyId) throws PropertyMovieNotFoundException {
        return propertiesMovieRepository.findById(propertyId)
                .orElseThrow(() -> new PropertyMovieNotFoundException(propertyId));
    }

    //TODO delete by ID but consider
}
