package com.contract.maven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.contract.maven.model.Order;
import com.contract.maven.repository.OrdersRepository;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/")
public class OrdersController {
    private final OrdersRepository ordersRepository;

    @Autowired
    public OrdersController(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @GetMapping("orders")
    public List<Order> getAllOrders() throws IOException {
    	System.out.println("ello");
        return ordersRepository.getOrders();
    }
}
