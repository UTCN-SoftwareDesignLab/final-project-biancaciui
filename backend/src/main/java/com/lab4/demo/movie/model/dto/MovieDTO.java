package com.lab4.demo.movie.model.dto;

import com.lab4.demo.movie.model.Genre;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    private Long id;
    private String name;
    private Integer year;
    private String director;
    private Float rating;
    private Long number_reviews;
    private String description;
    private Float price;
    private Set<Genre> genres;
    private String link;
}
