package com.lab4.demo.movie.mapper;


import com.lab4.demo.movie.model.Genre;
import com.lab4.demo.movie.model.dto.GenreDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDTO toDTO(Genre genre);

    Genre fromDTO(GenreDTO genre);

}
