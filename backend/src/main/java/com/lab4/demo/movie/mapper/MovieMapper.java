package com.lab4.demo.movie.mapper;

import com.lab4.demo.movie.model.Movie;
import com.lab4.demo.movie.model.dto.MovieDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieDTO toDTO(Movie movie);

    Movie fromDTO(MovieDTO movie);

}
