package com.lab4.demo.order.mapper;


import com.lab4.demo.order.Orders;
import com.lab4.demo.order.dto.OrderDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDTO toDTO(Orders order);

    Orders fromDTO(OrderDTO orderDTO);

}