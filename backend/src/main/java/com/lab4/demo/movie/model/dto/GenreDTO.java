package com.lab4.demo.movie.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenreDTO {
    private Integer id;
    private String genre;
}

