package com.lab4.demo.movie.repository;

import com.lab4.demo.movie.model.Genre;
import com.lab4.demo.movie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository  extends JpaRepository<Movie, Long> {

    Optional<Movie> findMovieByName(String name);

    @Query("SELECT movie from Movie movie where movie.name LIKE ?1 OR movie.description LIKE ?1 OR movie.director LIKE ?1")
    List<Movie> findByDescription(String description);

    @Query("SELECT movie.link from Movie movie where movie.id = ?1")
    String findLinkById(Long id);

    @Query("SELECT movie.link from Movie movie where movie.name LIKE ?1")
    String findLinkByName(String name);

    @Query("SELECT movie.name from Movie movie where movie.id = ?1")
    String findNameById(Long id);

    @Query(value="SELECT * FROM Movie movie ORDER BY movie.rating DESC Limit 0, 10", nativeQuery=true)
    List<Movie> findTop10ByRating();
}
