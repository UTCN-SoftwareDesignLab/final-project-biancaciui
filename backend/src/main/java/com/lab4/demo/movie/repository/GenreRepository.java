package com.lab4.demo.movie.repository;

import com.lab4.demo.movie.model.EGenre;
import com.lab4.demo.movie.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository <Genre, Integer> {
    Optional<Genre> findGenreByGenre(EGenre name);

}
