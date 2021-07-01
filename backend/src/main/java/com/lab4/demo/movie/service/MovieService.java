package com.lab4.demo.movie.service;

import com.lab4.demo.movie.mapper.GenreMapper;
import com.lab4.demo.movie.mapper.MovieMapper;
import com.lab4.demo.movie.model.Genre;
import com.lab4.demo.movie.model.Movie;
import com.lab4.demo.movie.model.dto.GenreDTO;
import com.lab4.demo.movie.model.dto.MovieDTO;
import com.lab4.demo.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final GenreMapper genreMapper;

    public List<MovieDTO> findAll() {
        return movieRepository.findAll().stream()
        .map(movieMapper::toDTO)
                .sorted((o1, o2) -> {
                    if(o1.getRating().equals(o2.getRating()))
                        return o1.getName().compareTo(o2.getName());
                    else if(o1.getRating() > o2.getRating())
                        return 1;
                    else return -1;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public MovieDTO create(MovieDTO movieDTO) {

        return movieMapper.toDTO(movieRepository.save
                (movieMapper.fromDTO(movieDTO))
        );
    }

    public MovieDTO findById(Long id) {
        return movieMapper.toDTO(movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Movie with id="+id+" not found.")));
    }

    public String findLinkById(Long id) {
        return movieRepository.findLinkById(id);
    }

    public MovieDTO findByName(String name) {
        return movieMapper.toDTO(movieRepository.findMovieByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Movie with name= "+name+" not found.")));
    }

    public String findLinkByName(String name) {
        return movieRepository.findLinkByName(name);
    }

    public List<MovieDTO> findByDescription(String desc) {
        return movieRepository.findByDescription(desc).stream()
                .map(movieMapper::toDTO)
                .sorted((o1, o2) -> {
                    if(o1.getRating().equals(o2.getRating()))
                        return o1.getName().compareTo(o2.getName());
                    else if(o1.getRating() > o2.getRating())
                        return 1;
                    else return -1;
                })
                .collect(Collectors.toList());
    }

    public MovieDTO edit(Long id, MovieDTO movieDTO) {
        Movie actMovie = movieMapper.fromDTO(findById(id));
        actMovie.setName(movieDTO.getName());
        actMovie.setDescription(movieDTO.getDescription());
        actMovie.setDirector(movieDTO.getDirector());
        actMovie.setGenres(movieDTO.getGenres());

        Set<Genre> genres = new HashSet<Genre>(movieDTO.getGenres());
        actMovie.setGenres(genres);
        System.out.println("Genres set size = "+genres.size());
        actMovie.setYear(movieDTO.getYear());
        actMovie.setPrice(movieDTO.getPrice());
        actMovie.setRating(movieDTO.getRating());
        actMovie.setLink(movieDTO.getLink());
        return movieMapper.toDTO(
                movieRepository.save(actMovie)
        );
    }

    public void delete(Long id) {
        movieRepository.deleteById(id);
    }
    public void deleteAll(){
        movieRepository.deleteAll();
    }

    public MovieDTO changePrice(Long id, float price) {
        Movie actMovie = movieMapper.fromDTO(findById(id));
        actMovie.setPrice(price);
        return movieMapper.toDTO(
                movieRepository.save(actMovie)
        );
    }

    public MovieDTO increaseNrReviews(Long id) {
        Movie actMovie = movieMapper.fromDTO(findById(id));
        actMovie.setNumber_reviews(actMovie.getNumber_reviews()+1);
        return movieMapper.toDTO(
                movieRepository.save(actMovie)
        );
    }

    public List<MovieDTO> top10ByRating(){
        return movieRepository.findTop10ByRating().stream()
                .map(movieMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<GenreDTO> findGenres(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Movie with id= "+id+" not found."));
        Set<GenreDTO> genreSet = movie.getGenres().stream().map(genreMapper::toDTO).collect(Collectors.toSet());
        return new LinkedList<>(genreSet);
    }
}
