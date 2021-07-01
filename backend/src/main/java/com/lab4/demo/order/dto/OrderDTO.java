package com.lab4.demo.order.dto;

import com.lab4.demo.movie.model.Movie;
import com.lab4.demo.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Long id;
    private User user;
    private Movie movie;
    private Long movie_id;
    private Long user_id;
    private String card_number;

}
