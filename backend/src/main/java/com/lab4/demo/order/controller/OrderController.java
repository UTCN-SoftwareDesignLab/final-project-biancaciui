package com.lab4.demo.order.controller;

import com.lab4.demo.movie.model.dto.MovieDTO;
import com.lab4.demo.order.dto.OrderDTO;
import com.lab4.demo.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab4.demo.UrlMapping.*;

@RestController
@RequestMapping(API_PATH+ORDER)
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderDTO> findAll() {
        return orderService.findAll();
    }

    @PostMapping()
    public OrderDTO create(@RequestBody OrderDTO orderDTO) {
        return orderService.create(orderDTO);
    }

    @GetMapping(ENTITY)
    public OrderDTO getOrder(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @PutMapping(ENTITY)
    public OrderDTO edit(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        return orderService.edit(id, orderDTO);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id){ orderService.delete(id);}

    @DeleteMapping()
    public void deleteAll(){ orderService.deleteAll();}

    @GetMapping("/cntOrdMovie/"+ENTITY)
    Long countOrdersByMovieID(@PathVariable Long id) {
        return orderService.countOrdersByMovieID(id);
    }

    @GetMapping("/cntOrdUser/"+ENTITY)
    Long countOrdersByUserID(@PathVariable Long id) {
        return orderService.countOrdersByUserID(id);
    }

}
