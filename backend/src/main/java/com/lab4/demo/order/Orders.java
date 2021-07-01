package com.lab4.demo.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lab4.demo.movie.model.Movie;
import com.lab4.demo.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Movie movie;

    @Column(name="movie_id", updatable=false, insertable=false)
    public Long movie_id;

    @ManyToOne
    @JsonIgnore
    private User user;

    @Column(name="user_id", updatable=false, insertable=false)
    public Long user_id;

    @Column(length = 16)
    private String card_number;

}
