package com.example.kddgmn.controller;

import com.example.kddgmn.model.Orders;
import com.example.kddgmn.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class OrderController {
    @Autowired
    private OrdersService ordersService;

    @GetMapping("/allorder")
    public List<Orders> getAll(){
        return  ordersService.getAll();
    }
}
