package com.lab4.demo.order.service;

import com.lab4.demo.order.MovieOrderCount;
import com.lab4.demo.order.Orders;
import com.lab4.demo.order.dto.OrderDTO;
import com.lab4.demo.order.mapper.OrderMapper;
import com.lab4.demo.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    public List<OrderDTO> findAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO create(OrderDTO orderDTO) {
        return orderMapper.toDTO(orderRepository.save
                (orderMapper.fromDTO(orderDTO))
        );
    }

    public OrderDTO findById(Long id) {
        return orderMapper.toDTO(orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order with id="+id+" not found.")));
    }

    public Long countOrdersByMovieID(Long movie_id){
        return orderRepository.countOrdersByMovieID(movie_id);
    }

    public Long countOrdersByUserID(Long user_id){
        return orderRepository.countOrdersByUserID(user_id);
    }

    public OrderDTO edit(Long id, OrderDTO orderDTO) {
        Orders actOrder = orderMapper.fromDTO(findById(id));
        actOrder.setUser(orderDTO.getUser());
        actOrder.setMovie(orderDTO.getMovie());
        actOrder.setCard_number(orderDTO.getCard_number());
        return orderMapper.toDTO(
                orderRepository.save(actOrder)
        );
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
    public void deleteAll(){
        orderRepository.deleteAll();
    }

    public List<Long> getTop10SoldIDs() {
        List<Object[]> objects = orderRepository.countOrderGroupByMovie();
        List<MovieOrderCount> orderCounts = new ArrayList<>();
        for (Object[] ob : objects) {
            Long movie = (Long) ob[0];
            Long count = (Long) ob[1];
            orderCounts.add(new MovieOrderCount(movie, count));
        }
        orderCounts.sort(Comparator.comparingLong(MovieOrderCount::getCount_orders));
        Collections.reverse(orderCounts);
        return orderCounts.stream().limit(10).map(MovieOrderCount::getId).collect(Collectors.toList());
    }



}
