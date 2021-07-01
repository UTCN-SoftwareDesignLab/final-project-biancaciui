package com.lab4.demo.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieOrderCount {
    public Long id;
    public Long count_orders;
}
