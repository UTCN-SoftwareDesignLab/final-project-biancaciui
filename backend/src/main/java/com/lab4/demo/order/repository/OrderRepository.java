package com.lab4.demo.order.repository;

import com.lab4.demo.order.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface OrderRepository  extends JpaRepository<Orders, Long> {

    @Query("SELECT COUNT(o.id) from Orders o WHERE o.user_id = ?1")
    Long countOrdersByUserID(Long user_id);

    @Query("SELECT COUNT(o.id) from Orders o where o.movie_id = ?1")
    Long countOrdersByMovieID(Long movie_id);

    @Query("SELECT o.movie_id, COUNT(o.id) AS countOrderMovies FROM Orders o GROUP BY o.movie_id")
    List<Object[]> countOrderGroupByMovie();

}
